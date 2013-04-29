package com.example.crazy;

import com.example.crazy.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SecondActivity extends Activity {

  @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		Button finishButton = (Button) findViewById(R.id.finish_button);
		finishButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent returnIntent = new Intent();
				
				setResult(RESULT_OK, returnIntent);
				finish();
			}
		});
		
		ImageButton Settings = (ImageButton) findViewById(R.id.settings_button);
		ImageButton Stats = (ImageButton) findViewById(R.id.stats_button);
		ImageButton upgrades = (ImageButton) findViewById(R.id.upgrades_button);
		  
		Stats.setOnClickListener(new View.OnClickListener() {  
		    public void onClick(View v) {  
		                    Toast.makeText(SecondActivity.this, "No Stats Available!", Toast.LENGTH_SHORT).show();  
		            }  
		
	});  
		upgrades.setOnClickListener(new View.OnClickListener() {  
		    public void onClick(View v) {  
		                    Toast.makeText(SecondActivity.this, "Coming Soon!", Toast.LENGTH_SHORT).show();  
		            }  
		
	});  
		

		
		
		Settings.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SecondActivity.this,
						Settings.class);
				startActivity(intent);
			//	intent.putExtra("name", nameEdit.getText().toString());
				//startActivityForResult(intent, 100);
			}
		});

		
		

	}
}
