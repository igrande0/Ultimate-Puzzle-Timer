package com.isaacgrande.ultimatepuzzletimer;

import com.isaacgrande.ultimatepuzzletimer.scramblers.RubiksCubeRandomScrambler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Timer extends Activity {
    private Button mTimerButton;
    private TextView mTimeLabel;
    private TextView mScrambleLabel;
    
    private RubiksCubeRandomScrambler mScrambler;

    private long mStartTime = 0L;
    private boolean TimerRunning = false;

    private Handler mHandler = new Handler();

    private Runnable mUpdateTimeTask = new Runnable() {
        @Override
        public void run() {
            long millis = System.currentTimeMillis() - mStartTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            int hours	= minutes / 60;
            minutes		= minutes % 60;
            seconds     = seconds % 60;
            int centis	= (int)(millis/10) % 100;
            
            if(minutes == 0 && hours == 0)
            	mTimeLabel.setText(String.format("%02d.%02d", seconds, centis));
            else if(hours == 0)
            	mTimeLabel.setText(String.format("%02d:%02d.%02d", minutes, seconds, centis));
            else
            	mTimeLabel.setText(String.format("%02d:%02d:%02d.%02d", hours, minutes, seconds, centis));

            mHandler.postDelayed(this,30);
        }
    };

    private View.OnClickListener mStartStopListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!TimerRunning) {
                mStartTime = System.currentTimeMillis();
                mHandler.removeCallbacks(mUpdateTimeTask);
                mHandler.post(mUpdateTimeTask);

                TimerRunning = true;
            }
            else {
                mHandler.removeCallbacks(mUpdateTimeTask);

                TimerRunning = false;
            }
        }
    };
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_timer, menu);
        return true;
    }
    
    @Override
	public void onBackPressed() {
    	if(TimerRunning) {
    		mHandler.removeCallbacks(mUpdateTimeTask);
            mTimeLabel.setText(getResources().getString(R.string.starting_time));
            TimerRunning = false;
    	}
    	else {
    		new AlertDialog.Builder(this)
	        .setTitle(getResources().getString(R.string.exit))
	        .setMessage(getResources().getString(R.string.exit_message))
	        .setNegativeButton(getString(android.R.string.no), null)
	        .setPositiveButton(getString(android.R.string.yes), new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface arg0, int arg1) {
	                Timer.super.onBackPressed();
	            }
	        }).create().show();
    	}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_timer);
        mTimerButton = (Button) findViewById(R.id.timer_button);
        mTimeLabel = (TextView) findViewById(R.id.time_label);
        mScrambleLabel = (TextView) findViewById(R.id.scramble_label);
        mTimerButton.setOnClickListener(mStartStopListener);
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        
        if(TimerRunning) {
		    mHandler.removeCallbacks(mUpdateTimeTask);
		    mTimeLabel.setText(getResources().getString(R.string.starting_time));
		    TimerRunning = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}


   
