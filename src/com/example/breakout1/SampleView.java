package com.example.breakout1;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.text.method.Touch;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;



public class SampleView extends View {

	private	Paint paint = new Paint();
	private int		color = Color.RED;
	private int		ballx;     //玉の初期位置
	private int		bally;     //
	private	int 		dx = -2;   //玉の向き
	private	int 		dy = -2;   //
	private static int margin = 20; //玉の半径
	private int blocrecord =5; //ブロックの行
	private int blocfield = 5;//ブロックの列
	private int map[][] = new int [10] [10];    //ブロックの配列
//	private Bitmap item;          //画像　
	private int speed = 15;       //玉の速さ
	private float ex = 0;         //タッチされた場所の座標
	private float ey = 0;         //
	private int bar = 100;       //バーの幅
	private boolean start = false; // ゲームを始める時判定
	private boolean restart = false; //　リスタートの判定
	private boolean clear = false;  //クリアーの判定
	private boolean stage[] = new boolean[3]; // stageの種類
	private int count = 0;      //ブロックの数

	SampleView(Context context) //コンストラクタ
	{
		super(context);
		setBackgroundColor(Color.WHITE);
		Resources res = context.getResources();
//		item = BitmapFactory.decodeResource(res, R.drawable.ic_launcher); //アンドロイドの絵	
	}

	//タッチされた時
	@Override
	public boolean onTouchEvent(MotionEvent event) 
	{   
		ex = event.getX();    
		ey = event.getY();

		int	action = event.getAction();
		//１回タッチされた時
		if(start == false){
		if((action & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_DOWN){
			for(int z = 0; z < 3; z++)
			if(z*(getWidth()/3)<=ex && ex <= (z+1)*(getWidth()/3) && (getHeight()/3) <= ey && ey <= 2*(getHeight()/3)){
				ballx = getWidth()/2;
				bally = getHeight()/2;
				blocrecord = 5 + 2*z;
				blocfield  = 5 + 2*z;
				speed = 10 +(3*z);
				start = true;
				}
		}
		}	
		if(restart == true){
			if((action & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_DOWN){ //１回タッチされた時
				for(int m = 0; m < blocrecord; m++){
					for(int n =0; n < blocfield; n++){
						map[m][n] = 0;
						
					}
				}
				start = false;
				clear = false;
				restart = false;
				ballx = 500;
				bally = getHeight()/2;
			}
		}			
		return true;
	}
	 //描画に関する
	@Override
	public void onDraw(Canvas canvas){   
//		スタート画面
		paint.setColor(color); 
		if(start == false){
			paint.setTextSize(50);
        	paint.setColor(Color.BLUE);
			canvas.drawText("はじめてのブロック崩しづくり", getWidth()/10, 200, paint);
			paint.setColor(0xFF000000);
			canvas.drawText("四角ををタッチすると始まるよ", getWidth()/10, getHeight()-200, paint);
			for(int  e= 0; e < 3; e++){
				paint.setStyle(Style.STROKE);
				canvas.drawRect(e*(getWidth()/3),getHeight()/3,(e+1)*(getWidth()),2*(getHeight()/3),paint);
				int z;
				z = e +5;
				String Z = Integer.toString(z) ;
				canvas.drawText(Z +"×" +Z, ((4*e)+1)*(getWidth()/12),(getHeight()/2), paint);
			}
			
			
			
			
//		玉の表示
		}else if(start == true){
		canvas.drawCircle(ballx, bally, margin, paint);
		
		
//		 ブロックを表示する
		for(int l = 0; l < blocrecord; l++){
			for(int i = 0 ; i <  blocfield; i++){
				if(map[l][i] == 0){	
					paint.setStyle(Style.FILL);
					paint.setColor(color);
					canvas.drawRect(i*(getWidth()/blocfield),100+(60*l),(1+i)*(getWidth()/blocfield),160+(60*l),paint);
//		ブロックの枠
					paint.setStyle(Style.STROKE);
					paint.setColor(0xFF000000);
				    canvas.drawRect(i*(getWidth()/blocfield),100+(60*l),(1+i)*(getWidth()/blocfield),160+(60*l),paint);
				  
//		ブロックと玉が衝突したとき			    
		for(int j = 0 ; j <= (getWidth()/blocfield); j++){
			if ((ballx - ((i*(getWidth()/blocfield))+j))*(ballx - ((i*(getWidth()/blocfield))+j))
				+(bally - (160+(60*l)))*(bally - (160+(60*l))) <= margin*margin) //ブロックの下側から衝突
			{
				dy = 2;
				map[l][i] = 1;
			}
			
		}
		for(int j = 0 ; j <= (getWidth()/blocfield); j++){
			if ((ballx - ((i*(getWidth()/blocfield))+j))*(ballx - ((i*(getWidth()/blocfield))+j))
					+(bally - (100+(60*l)))*(bally - (100+(60*l))) <= margin*margin) //ブロックの上側から衝突
			{
				dy = -2;
				map[l][i] = 1;
			}
			
			}
		

		for(int j = 0 ; j <= 60; j++){
			if ((ballx - ((i+1)*(getWidth()/blocfield)))*(ballx - ((i+1)*(getWidth()/blocfield)))
				+(bally - (100+(60*l)+j))*(bally - (100+(60*l)+j)) <= margin*margin)//ブロックの右側から衝突
			{
				dx = 2;
				map[l][i] = 1;
			}
		}
		for(int j = 0 ; j <= 60; j++){
			if ((ballx - (i*(getWidth()/blocfield)))*(ballx - (i*(getWidth()/blocfield)))
					+(bally - (100+(60*l)+j))*(bally - (100+(60*l)+j)) <= margin*margin)//ブロック左側から衝突
			{
				dx = -2;
				map[l][i] = 1;
			}
		
		}
				}
//				ブロックの残り数
				count = count + map[l][i];
				if(count == (blocfield * blocrecord)){
					clear = true;
				}
					
				
		}
			
		}
		count = 0;
		
        //   バーを表示させる
		paint.setColor(0xFFFF00FF);
		paint.setStyle(Style.FILL);

		canvas.drawRect(ex-bar,getHeight()-200,ex+bar,getHeight()-160, paint);

		//　バーに当たった時の処理
        for(int k = 0; k<=2*bar ; k++){
        	if ((ballx - (ex-bar+k))*(ballx - (ex-bar+k))
        			+(bally - (getHeight()-200))*(bally - (getHeight()-200)) <= margin*margin)
            	{ dy = -2;}
            }
		/* 左端　右端　上端　下端　に来た時の処理 */
        /* 左端に来たら反転 */
        if (ballx < 0 + margin ) {
            dx = 2;
        }
        /* 右端に来たら反転 */
        if (ballx > getWidth() - margin) {
            dx = -2;
        }
        /* 右端に来たら反転 */
        if (bally < 0 + margin) {
            dy = 2;
        }		
        /* 下端に来た時の処理 */
        if (bally  > getHeight() - margin) {
        	paint.setTextSize(80);
        	paint.setColor(Color.BLUE);
        	canvas.drawText("Game Over",getWidth()/4,getHeight()/4,paint);
        	paint.setTextSize(50);
        	canvas.drawText("タッチするとスタート画面に戻る",getWidth()/4,(getHeight()/4)+200,paint);
        	ballx = -margin;
        	bally = +margin + getHeight();
        	speed = 0; 
        	restart = true;
        	
        }
//      ブロックをすべて壊したときの処理
        if(clear == true){
        	paint.setTextSize(50);
        	canvas.drawText("Game Clear",getWidth()/4,getHeight()/4,paint);
        	canvas.drawText("タッチするとスタート画面に戻る",getWidth()/4,(getHeight()/4)+200,paint);
        	ballx = -margin;
        	bally = -margin;
        	speed = 0; 
        	restart = true;
        	
        }   
        ballx = ballx + speed * dx;
        bally = bally + speed * dy;
	}
	
	}

}
	


