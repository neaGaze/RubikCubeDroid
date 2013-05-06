package com.rubikcubedroid;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.util.Log;

public class RubikCube extends GLSurfaceView implements GLSurfaceView.Renderer{

	private CubeBuild[] cube = new CubeBuild[27];
	public RubikCube(Context context) {
		super(context);
		for(int i =0; i<27; i++)
		{
			cube[i] = new CubeBuild();
		}
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		// Log.v("ERROR ON RUBIKCUBE","Cube.draw()");
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glScalef(0.8f, 0.8f, 0.8f);  
		
		gl.glLoadIdentity();
		gl.glTranslatef(0.0f, 0.0f, -7.0f);
		gl.glRotatef(30.0f, 30.0f, 30.0f, 30.0f);
	//	gl.glRotatef(210.0f, 210.0f, 210.0f, 150.0f);
		cube[0].draw(gl);
	/*	
		int k=0;

		for(int l=0; l<3; l++)
		{
			for(int i=0; i<3; i++)
			{
				for(int j=0; j<3; j++)
				{
					gl.glLoadIdentity();
					gl.glTranslatef(-2.1f+(2.1f*i), -2.1f+(2.1f*j), -23.1f+(2.1f*l));
	//				gl.glRotatef(50.0f, 20.0f, 30.0f, 40.0f);
					gl.glRotatef(200.0f, 200.0f, 30.0f, 40.0f);
					cube[k++].draw(gl);
				}
			}
		}   */
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluPerspective(gl, 45.0f, (float)width/(float)height, 0.1f, 100.0f);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
	//	Log.v("ERROR ON SurfaceChanged", "error cha huna sakcha");
		gl.glShadeModel(GL10.GL_FLAT);
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST); // (viewing angle, aspect ratio, near, further)
		
	}

	/**
	 * @param args
	 */
	
}
