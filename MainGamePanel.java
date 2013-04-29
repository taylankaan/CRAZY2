package com.example.crazy;





import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;

/**
* @author impaler
* This is the main surface that handles the ontouch events and draws
* the image to the screen.
*/
public class MainGamePanel extends SurfaceView implements
   SurfaceHolder.Callback {
   ImageButton myImageButton = (ImageButton) findViewById(R.id.play_butt);
private static final String TAG = MainGamePanel.class.getSimpleName();



private MainThread thread;
private ElaineAnimated elaine;

// the fps to be displayed
private String avgFps;
public void setAvgFps(String avgFps) {
this.avgFps = avgFps;
}

public MainGamePanel(Context context) {
super(context);
// adding the callback (this) to the surface holder to intercept events
getHolder().addCallback(this);

// create Elaine and load bitmap
elaine = new ElaineAnimated(
BitmapFactory.decodeResource(getResources(), R.drawable.walk_elaine)
, 30, 200	// initial position
, 68, 68	// width and height of sprite
,20, 8);	// FPS and number of frames in the animation

// create the game loop thread
 
    	
thread = new MainThread(getHolder(), this);
	
// make the GamePanel focusable so it can handle events
setFocusable(true);
}

@Override
public void surfaceChanged(SurfaceHolder holder, int format, int width,
int height) {
}

@Override
public void surfaceCreated(SurfaceHolder holder) {
// at this point the surface is created and
// we can safely start the game loop
	
	    	thread.setRunning(true);
	    	
	                      
	            
	     
	    	
 

thread.start();
	    	    }  
	

@Override
public void surfaceDestroyed(SurfaceHolder holder) {
Log.d(TAG, "Surface is being destroyed");
// tell the thread to shut down and wait for it to finish
// this is a clean shutdown
boolean retry = true;
while (retry) {
try {
thread.join();
retry = false;
} catch (InterruptedException e) {
// try again shutting down the thread
}
}
Log.d(TAG, "Thread was shut down cleanly");
}


public void render(Canvas canvas) {
	canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), 
            R.drawable.back), 0, 0, null);
elaine.draw(canvas);
// display fps
displayFps(canvas, avgFps);
}
@Override
public boolean onTouchEvent(MotionEvent event) {
	if (event.getAction() == MotionEvent.ACTION_DOWN) {
		// delegating event handling to the droid
		elaine.handleActionDown((int)event.getX(), (int)event.getY());
		
		// check if in the lower part of the screen we exit
		if (event.getY() > getHeight() - 50) {
			thread.setRunning(false);
			((Activity)getContext()).finish();
		} else {
			Log.d(TAG, "Coords: x=" + event.getX() + ",y=" + event.getY());
		}
	} if (event.getAction() == MotionEvent.ACTION_MOVE) {
		// the gestures
		if (elaine.isTouched()) {
			// the droid was picked up and is being dragged
			elaine.setX((int)event.getX());
			elaine.setY((int)event.getY());
		}
	} if (event.getAction() == MotionEvent.ACTION_UP) {
		// touch was released
		if (elaine.isTouched()) {
			elaine.setTouched(false);
			
		}
	}
	return true;
}


/**
* This is the game update method. It iterates through all the objects
* and calls their update method if they have one or calls specific
* engine's update method.
*/
public void update() {
elaine.update(System.currentTimeMillis());

	// check collision with right wall if heading right
	if (elaine.getSpeed().getxDirection() == Speed.DIRECTION_RIGHT
			&& elaine.getX() + elaine.getBitmap().getWidth() / 2 >= getWidth()) {
		elaine.getSpeed().toggleXDirection();
	}
	// check collision with left wall if heading left
	if (elaine.getSpeed().getxDirection() == Speed.DIRECTION_LEFT
			&& elaine.getX() - elaine.getBitmap().getWidth() / 2 <= 0) {
		elaine.getSpeed().toggleXDirection();
	}
	// check collision with bottom wall if heading down
	if (elaine.getSpeed().getyDirection() == Speed.DIRECTION_DOWN
			&& elaine.getY() + elaine.getBitmap().getHeight() / 2 >= getHeight()) {
		elaine.getSpeed().toggleYDirection();
	}
	// check collision with top wall if heading up
	if (elaine.getSpeed().getyDirection() == Speed.DIRECTION_UP
			&& elaine.getY() - elaine.getBitmap().getHeight() / 2 <= 0) {
		elaine.getSpeed().toggleYDirection();
	}
	// Update the lone droid
	elaine.update();
}





private void displayFps(Canvas canvas, String fps) {
if (canvas != null && fps != null) {
Paint paint = new Paint();
paint.setColor(Color.BLACK);
canvas.drawText(fps, this.getWidth() - 50, 20, paint);
}
}

}



