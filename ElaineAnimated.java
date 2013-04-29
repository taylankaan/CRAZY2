package com.example.crazy;




import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
* @author impaler
*
*/
public class ElaineAnimated {
  
private static final String TAG = ElaineAnimated.class.getSimpleName();
private boolean touched;
private Bitmap bitmap;  // the animation sequence
private Rect sourceRect;	// the rectangle to be drawn from the animation bitmap
private int frameNr;	// number of frames in animation
private int currentFrame;	// the current frame
private long frameTicker;	// the time of the last frame update
private int framePeriod;	// milliseconds between each frame (1000/fps)
private MainThread tread;
private int spriteWidth;	// the width of the sprite to calculate the cut out rectangle
private int spriteHeight;	// the height of the sprite
private Speed speed;
private int x;	// the X coordinate of the object (top left of the image)
private int y;	// the Y coordinate of the object (top left of the image)

public ElaineAnimated(Bitmap bitmap, int x, int y, int width, int height, int fps, int frameCount) {
this.bitmap = bitmap;
this.speed = new Speed();
this.x = x;
this.y = y;
currentFrame = 0;
frameNr = frameCount;
spriteWidth = bitmap.getWidth() / frameCount;
spriteHeight = bitmap.getHeight();
sourceRect = new Rect(0, 0, spriteWidth, spriteHeight);
framePeriod = 1000 / fps;
frameTicker = 0l;
}
public void setTouched(boolean touched) {
	this.touched = touched;
}

public Bitmap getBitmap() {
return bitmap;
}
public void setBitmap(Bitmap bitmap) {
this.bitmap = bitmap;
}
public Rect getSourceRect() {
return sourceRect;
}
public void setSourceRect(Rect sourceRect) {
this.sourceRect = sourceRect;
}
public int getFrameNr() {
return frameNr;
}
public void setFrameNr(int frameNr) {
this.frameNr = frameNr;
}
public int getCurrentFrame() {
return currentFrame;
}
public void setCurrentFrame(int currentFrame) {
this.currentFrame = currentFrame;
}
public int getFramePeriod() {
return framePeriod;
}
public void setFramePeriod(int framePeriod) {
this.framePeriod = framePeriod;
}
public int getSpriteWidth() {
return spriteWidth;
}
public void setSpriteWidth(int spriteWidth) {
this.spriteWidth = spriteWidth;
}
public int getSpriteHeight() {
return spriteHeight;
}
public void setSpriteHeight(int spriteHeight) {
this.spriteHeight = spriteHeight;
}
public int getX() {
return x;
}
public void setX(int x) {

this.x = x;
}
public int getY() {
return y;
}
public void setY(int y) {
this.y = y;
}
public boolean isTouched() {
	return touched;
}
public void setSpeed(Speed speed) {
	this.speed = speed;
}
public Speed getSpeed() {
	return speed;
}



// the update method for Elaine
public void update(long gameTime) {
if (gameTime > frameTicker + framePeriod) {
frameTicker = gameTime;
// increment the frame
currentFrame++;
if (currentFrame >= frameNr) {
currentFrame = 0;
}
}
// define the rectangle to cut out sprite
this.sourceRect.left = currentFrame * spriteWidth;
this.sourceRect.right = this.sourceRect.left + spriteWidth;

}
public void update() {
	if(isTouched()){

		
		x += (speed.getXv() * speed.getxDirection()); 
		
		//y += (speed.getYv() * speed.getyDirection());
	}
}

public void handleActionDown(int eventX, int eventY) {
	if (eventX >= (x - bitmap.getWidth() / 2) && (eventX <= (x + bitmap.getWidth()/2))) {
		if (eventY >= (y - bitmap.getHeight() / 2) && (y <= (y + bitmap.getHeight() / 2))) {
			// droid touched
			setTouched(true);
			
			
		} else {
			setTouched(false);
		}
	} else {
		setTouched(false);
	}

}



// TODO Auto-generated method stub

// the draw method which draws the corresponding frame
public void draw(Canvas canvas) {
// where to draw the sprite

Rect destRect = new Rect(getX(), getY(), getX() + spriteWidth, getY() + spriteHeight);
canvas.drawBitmap(bitmap, sourceRect, destRect, null);
//canvas.drawBitmap(bitmap,20, 150, null);


//canvas.drawRect(20 + (currentFrame * destRect.width()), 150, 20 + (currentFrame * destRect.width()) + destRect.width(), 150 + destRect.height(), paint);
}





}
