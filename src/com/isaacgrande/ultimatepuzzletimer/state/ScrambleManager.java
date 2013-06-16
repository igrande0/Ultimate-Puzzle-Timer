package com.isaacgrande.ultimatepuzzletimer.state;

import com.isaacgrande.ultimatepuzzletimer.MainActivity;
import com.isaacgrande.ultimatepuzzletimer.models.Scramble;
import com.isaacgrande.ultimatepuzzletimer.scramblers.Scrambler;
import com.isaacgrande.ultimatepuzzletimer.scramblers.ScramblerProvider;

import android.os.AsyncTask;

public class ScrambleManager extends AsyncTask<Void, Void, Scramble>{
	private ScramblerProvider mScramblerProvider = new ScramblerProvider();
    private Scrambler CurrentScrambler = mScramblerProvider.get("RUBIKS-CUBE-RANDOM");
    private MainActivity mContext;
    
    public ScrambleManager (MainActivity activityContext) {
    	mContext = activityContext;
    }
    
    public void setCategory(String Category) {
    	CurrentScrambler = mScramblerProvider.get(Category);
    }
    
    @Override
    protected void onPreExecute() {
    	mContext.setScramble("generating scramble...");
    }
    
	@Override
	protected Scramble doInBackground(Void... params) {
		return CurrentScrambler.getNextScramble();
	}
	
	@Override
	protected void onPostExecute(Scramble scramble) {
		mContext.setScramble(scramble.getRawSequence());
	}
}
