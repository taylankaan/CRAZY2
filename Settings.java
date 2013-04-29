package com.example.crazy;

import com.example.crazy.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;


public class Settings extends Activity implements OnSeekBarChangeListener{
  SeekBar sb;
	MediaPlayer mp;
	AudioManager am;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.settings);
		sb =(SeekBar)findViewById(R.id.sbVolume);
		am=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
		int maxV=am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		int curvV=am.getStreamVolume(AudioManager.STREAM_MUSIC);
	sb.setOnSeekBarChangeListener(this);
	
		Button finishButton = (Button) findViewById(R.id.finish);
		finishButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent returnIntent = new Intent();
				
				setResult(RESULT_OK, returnIntent);
				finish();
			}
		});
	
		
	
		
		

	}

	@Override
	public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStartTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
