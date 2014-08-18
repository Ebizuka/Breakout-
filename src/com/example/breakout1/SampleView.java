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
	private int		ballx = 500;     //�ʂ̏����ʒu
	private int		bally = 500;     //
	private	int 		dx = -2;   //�ʂ̌�����\��
	private	int 		dy = -2;   //
	private static int margin = 20; //�ʂ̔��a
	private int blocrecord = 2; //�u���b�N�̍s
	private int blocfield = 4;//�u���b�N�̗�
	private int map[][] = new int[blocrecord][blocfield]  ;    //�u���b�N�ɑ΂���z��
	private Bitmap item;          //�摜 item
	private int speed = 10;       //�ʂ̑�����ݒ�
	private float ex = 0;         //�^�b�`���ꂽ�ꏊ�̍��W
	private float ey = 0;         //
	private int bar = 50;       //�o�[�̕�

	public SampleView(Context context) //�R���X�g���N�^
	{
		super(context);
		setBackgroundColor(Color.WHITE);
		Resources res = context.getResources();
		item = BitmapFactory.decodeResource(res, R.drawable.ic_launcher); //�A���h���C�h�̊G
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) //�^�b�`���ꂽ��
	{
		int	action = event.getAction();
		if((action & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_DOWN){

			ex = event.getX();    
			ey = event.getY();
			
			
				

			}
		
		return true;
	}
	
	@Override
	public void onDraw(Canvas canvas){   //�`��Ɋւ���
		
		paint.setColor(color); //�F���w��H
		canvas.drawCircle(ballx, bally, margin, paint);//�ʂ�\��
		
		//canvas.drawColor(Color.WHITE);//�w�i�̐F���w��
		
		//�u���b�N��\������
		for(int l = 0; l < blocrecord; l++){
		for(int i = 0 ; i <  blocfield; i++){
			if(map[l][i] == 0){
		
		canvas.drawRect(i*(getWidth()/blocfield),100+(60*l),(1+i)*(getWidth()/blocfield),160+(60*l),paint);
		//�u���b�N�Ƌʂ��Փ˂����Ƃ�
		for(int j = 0 ; j <= (getWidth()/blocfield); j++){
			if ((ballx - ((i*(getWidth()/blocfield))+j))*(ballx - ((i*(getWidth()/blocfield))+j))
				+(bally - (160+(60*l)))*(bally - (160+(60*l))) <= margin*margin) //�u���b�N�̉�������Փ� 
			{
				dy = 2;
				map[l][i] = 1;
			}
		}
		for(int j = 0 ; j <= (getWidth()/blocfield); j++){
			if ((ballx - ((i*(getWidth()/blocfield))+j))*(ballx - ((i*(getWidth()/blocfield))+j))
					+(bally - (100+(60*l)))*(bally - (100+(60*l))) <= margin*margin) //�u���b�N�㑤����Փ�
			{
				dy = -2;
				map[l][i] = 1;
			}
		}

		for(int j = 0 ; j <= 60; j++){
			if ((ballx - ((i+1)*(getWidth()/blocfield)))*(ballx - ((i+1)*(getWidth()/blocfield)))
				+(bally - (100+(60*l)+j))*(bally - (100+(60*l)+j)) <= margin*margin)//�u���b�N�E������Փ�
			{
				dx = 2;
				map[l][i] = 1;
			}
		}
		for(int j = 0 ; j <= 60; j++){
			if ((ballx - (i*(getWidth()/blocfield)))*(ballx - (i*(getWidth()/blocfield)))
					+(bally - (100+(60*l)+j))*(bally - (100+(60*l)+j)) <= margin*margin)//�u���b�N��������Փ�
			{
				dx = -2;
				map[l][i] = 1;
			}
		}
		
	}
		}
		}
	
	
        //   �o�[��\��������
		canvas.drawRect(ex-bar,getHeight()-200,ex+bar,getHeight()-160, paint);

		//�o�[�ɓ����������̏���
        for(int k = 0; k<=100 ; k++){
        	if ((ballx - (ex-50+k))*(ballx - (ex-50+k))
        			+(bally - (getHeight()-200))*(bally - (getHeight()-200)) <= margin*margin)
            	{ dy = -2;}
            }
		/* ���[�A�E�[�A��[�A���[�ɗ����Ƃ��̏��� */
        /* ���[�ɗ����甽�] */
        if (ballx < 0 + margin ) {
            dx = 2;
        }
        /* �E�[�ɗ����甽�] */
        if (ballx > getWidth() - margin) {
            dx = -2;
        }
        /* ��[�ɗ����甽�] */
        if (bally < 0 + margin) {
            dy = 2;
        }		
        /* ���[�ɗ����� */
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
