package com.isaacgrande.ultimatepuzzletimer;

import com.isaacgrande.ultimatepuzzletimer.models.Timer;
import com.isaacgrande.ultimatepuzzletimer.state.ScrambleManager;

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

public class MainActivity extends Activity{
    private Button mTimerButton;
    private TextView mTimeLabel;
    private TextView mScrambleLabel;
    private MainActivity mContext = this;
    
    private Timer mTimer = new Timer();

    private Handler mHandler = new Handler();

    private Runnable mUpdateTimeTask = new Runnable() {
        @Override
        public void run() {
            mTimeLabel.setText(mTimer.getFormattedElapsedTime());
            mHandler.postDelayed(this,30);
        }
    };

    private View.OnClickListener mStartStopListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mTimer.isRunning()) {
            	mTimer.stop();
            	new ScrambleManager(mContext).execute();
            	
                mHandler.removeCallbacks(mUpdateTimeTask);
            }
            else {
            	mTimer.start();
                
                mHandler.removeCallbacks(mUpdateTimeTask);
                mHandler.post(mUpdateTimeTask);
            }
        }
    };
    
    public void setScramble(String scramble) {
    	mScrambleLabel.setText(scramble);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_timer, menu);
        return true;
    }
    
    @Override
	public void onBackPressed() {
    	if (mTimer.isRunning()) {
    		mTimer.stop();
    		mHandler.removeCallbacks(mUpdateTimeTask);
            mTimeLabel.setText(getResources().getString(R.string.starting_time));
    	}
    	else {
    		new AlertDialog.Builder(this)
	        .setTitle(getResources().getString(R.string.exit))
	        .setMessage(getResources().getString(R.string.exit_message))
	        .setNegativeButton(getString(android.R.string.no), null)
	        .setPositiveButton(getString(android.R.string.yes), 
        	new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface arg0, int arg1) {
	                MainActivity.super.onBackPressed();
	            }
	        }).create().show();
    	}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_main);
        mTimerButton = (Button) findViewById(R.id.timer_button);
        mTimeLabel = (TextView) findViewById(R.id.time_label);
        mScrambleLabel = (TextView) findViewById(R.id.scramble_label);
        
        new ScrambleManager(this).execute();

        mTimerButton.setOnClickListener(mStartStopListener);
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        
        if (mTimer.isRunning()) {
        	mTimer.stop();
    		mHandler.removeCallbacks(mUpdateTimeTask);
            mTimeLabel.setText(getResources().getString(R.string.starting_time));
        }
    }
}


   
