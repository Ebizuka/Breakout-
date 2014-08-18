package com.example.breakout1;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class MainActivity extends Activity {

	
	
	private View view;

	// �n���h�����쐬
	private Handler handler = new Handler();
	// �r���[�̍ĕ`��Ԋu(�~���b)
	private final static long INTERVAL_TIME = 30;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new SampleView(this);
		setContentView(view);

		// �r���[�ĕ`��^�C�}�[
		// �^�C�}�[���쐬
		Timer timer = new Timer(false);
		// �uINTERVAL_TIME�v�~���b�����Ƀ^�X�N(TimerTask)�����s
	    timer.schedule(new TimerTask(){
	    	public void run(){
	    		handler.post(new Runnable(){
	    			public void run(){
	    				// �r���[���ĕ`��
	    				view.invalidate();
	    			}});
	    	}
	    }, 0, INTERVAL_TIME);
		
//		setContentView(null);
//		setContentView(R.layout.activity_main);

//		if (savedInstanceState == null) {
//			getFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
//		}
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}

//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}

//	/**
//	 * A placeholder fragment containing a simple view.
//	 */
//	public static class PlaceholderFragment extends Fragment {
//
//		public PlaceholderFragment() {
//		}
//
//		@Override
//		public View onCreateView(LayoutInflater inflater, ViewGroup container,
//				Bundle savedInstanceState) {
//			View rootView = inflater.inflate(R.layout.fragment_main, container,
//					false);
//			return rootView;
//		}
//	}

}
