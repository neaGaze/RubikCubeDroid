package com.rubikcubedroid;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;

public class GraphicsActivity extends Activity {

	RubikCube rubikCube ;
	private GLSurfaceView glsurfaceView;
	private float oldX=0, oldY=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		rubikCube = new RubikCube(this);
		glsurfaceView = new GLSurfaceView(this);
		glsurfaceView.setRenderer(rubikCube);
		setContentView(glsurfaceView);
	//	setContentView(R.layout.jpt);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.graphics, menu);
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		float x = event.getX();
		float y = event.getY();
		if(event.getAction() == MotionEvent.ACTION_MOVE)
		{
			//For moving the cube portion according to the touch action
			float dx = x - oldX;
			float dy = y - oldY;
			Log.v("ABOUT TOUCH", "Movement at x: "+x+" and y: "+y);
			Log.v("DIFF","diff dx: "+dx+" dy: "+dy);
			//call the RotateMethod in RubikCubeDroid
			rubikCube.rotateMethod(dx, dy);
		}
		else if(event.getAction() == MotionEvent.ACTION_UP)
		{
			//need to do this for detection of cube pressed
			Log.v("SINGLE TOUCH", "kei hola jasto cha hai");
		}
		
		oldX = x;
		oldY = y;
		return true;
	}
}
