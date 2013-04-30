package com.rubikcubedroid;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class GraphicsActivity extends Activity {

	RubikCube rubikCube ;
	private GLSurfaceView glsurfaceView;
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
/*	
	@Override
	protected void onResume() {
		super.onResume();
		rubikCube.onResume();
	}

	/**
	 * Also pause our Lesson
	 */
/*	@Override
	protected void onPause() {
		super.onPause();
		rubikCube.onPause();
	}
*/
}
