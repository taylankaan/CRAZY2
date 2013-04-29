package com.example.crazy;



import com.example.crazy.MainActivity;
import com.example.crazy.R;
import com.example.crazy.SecondActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.view.*;

public class MainActivity extends Activity {
public MainThread tread;
  @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
	ImageButton menu = (ImageButton) findViewById(R.id.menu_but);
	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    // set our MainGamePanel as the View
	setContentView(new MainGamePanel(this));
	

	//menu.setOnClickListener(new OnClickListener() {

		
//@Override
//public void onClick(View v) {
			//Intent intent = new Intent(MainActivity.this,
					//SecondActivity.class);
			//startActivity(intent);
		//intent.putExtra("name", nameEdit.getText().toString());
	//startActivityForResult(intent, 100);
		//}
	//});
	/*ImageButton myImageButton = (ImageButton) findViewById(R.id.play_butt);  
	myImageButton.setOnClickListener(new View.OnClickListener() {  
	    public void onClick(View v) {  
	    	
	    	 
	    	
	                      
	            }  
	
});  

*/
	
	}}
