package com.example.breakout1;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;



public class SampleView extends View {

	private	Paint paint = new Paint();
	private int		color = Color.RED;
	private int		ballx = 500;     //玉の初期位置
	private int		bally = 500;     //
	private	int 		dx = -2;   //玉の向きを表す
	private	int 		dy = -2;   //
	private static int margin = 20; //玉の半径
	private int blocrecord = 2; //ブロックの行
	private int blocfield = 4;//ブロックの列
	private int map[][] = new int[blocrecord][blocfield]  ;    //ブロックに対する配列
	private Bitmap item;          //画像 item
	private int speed = 10;       //玉の速さを設定
	private float ex = 0;         //タッチされた場所の座標
	private float ey = 0;         //
	private int bar = 50;       //バーの幅

	public SampleView(Context context) //コンストラクタ
	{
		super(context);
		setBackgroundColor(Color.WHITE);
		Resources res = context.getResources();
		item = BitmapFactory.decodeResource(res, R.drawable.ic_launcher); //アンドロイドの絵
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) //タッチされた時
	{
		int	action = event.getAction();
		if((action & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_DOWN){

			ex = event.getX();    
			ey = event.getY();
			
			
				

			}
		
		return true;
	}
	
	@Override
	public void onDraw(Canvas canvas){   //描画に関する
		
		paint.setColor(color); //色を指定？
		canvas.drawCircle(ballx, bally, margin, paint);//玉を表示
		
		//canvas.drawColor(Color.WHITE);//背景の色を指定
		
		//ブロックを表示する
		for(int l = 0; l < blocrecord; l++){
		for(int i = 0 ; i <  blocfield; i++){
			if(map[l][i] == 0){
		
		canvas.drawRect(i*(getWidth()/blocfield),100+(60*l),(1+i)*(getWidth()/blocfield),160+(60*l),paint);
		//ブロックと玉が衝突したとき
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
					+(bally - (100+(60*l)))*(bally - (100+(60*l))) <= margin*margin) //ブロック上側から衝突
			{
				dy = -2;
				map[l][i] = 1;
			}
		}

		for(int j = 0 ; j <= 60; j++){
			if ((ballx - ((i+1)*(getWidth()/blocfield)))*(ballx - ((i+1)*(getWidth()/blocfield)))
				+(bally - (100+(60*l)+j))*(bally - (100+(60*l)+j)) <= margin*margin)//ブロック右側から衝突
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
		}
		}
	
	
        //   バーを表示させる
		canvas.drawRect(ex-bar,getHeight()-200,ex+bar,getHeight()-160, paint);

		//バーに当たった時の処理
        for(int k = 0; k<=100 ; k++){
        	if ((ballx - (ex-50+k))*(ballx - (ex-50+k))
        			+(bally - (getHeight()-200))*(bally - (getHeight()-200)) <= margin*margin)
            	{ dy = -2;}
            }
		/* 左端、右端、上端、下端に来たときの処理 */
        /* 左端に来たら反転 */
        if (ballx < 0 + margin ) {
            dx = 2;
        }
        /* 右端に来たら反転 */
        if (ballx > getWidth() - margin) {
            dx = -2;
        }
        /* 上端に来たら反転 */
        if (bally < 0 + margin) {
            dy = 2;
        }		
        /* 下端に来たら */
        if (bally  > getHeight() - margin) {
        	paint.setTextSize(80);
        	paint.setColor(Color.BLUE);
        	canvas.drawText("Game Over",getWidth()/4,getHeight()/4,paint);
        	ballx = -margin;
        	bally = +margin + getHeight();
        	speed = 0; 
        	
        }      
        
        
        ballx = ballx + speed * dx;
        bally = bally + speed * dy;
	}
}
