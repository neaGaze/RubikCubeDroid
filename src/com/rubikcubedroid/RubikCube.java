package com.rubikcubedroid;

import com.rubikcubedroid.*;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

public class RubikCube extends GLSurfaceView implements GLSurfaceView.Renderer{

	private CubeBuild[] cube = new CubeBuild[27];
	private float rot = 0.0f, cubeRotX= 0.0f, cubeRotY = 0.0f;
	private float xRot =  0.0f, yRot = 0.0f;
	private float oldX, oldY;
	private static final float TOUCH_SCALE = 0.2f;
	
	public RubikCube(Context context) {
		super(context);

		//Request focus, otherwise buttons won't react
		this.requestFocus();
		this.setFocusableInTouchMode(true);
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
		gl.glScalef(0.4f, 0.4f, 0.4f);  
	/*	
	 * 
	 * This is just to work with the Colors
	 * 
		gl.glLoadIdentity();
		gl.glTranslatef(0.0f, 0.0f, -7.0f);
		gl.glRotatef(30.0f, 30.0f, 30.0f, 30.0f);
	//	gl.glRotatef(210.0f, 210.0f, 210.0f, 150.0f);
	//	cube[0].draw(gl);
		*/
		int k=0;

		gl.glPushMatrix();
		gl.glTranslatef(0.0f, 0.0f, -21.0f);
		gl.glRotatef(cubeRotX, 1.0f, 0.0f , 0.0f);
		gl.glRotatef(cubeRotY, 0.0f, 1.0f , 0.0f);
		gl.glTranslatef(0.0f, 0.0f, 21.0f);
		
		for(int l=0; l<3; l++)
		{
			if(l == 2)  //To rotate only the first front polygon in 1.7f angle
			{
				gl.glPushMatrix();
				gl.glRotatef(rot, 0.0f, 0.0f, 1.0f);
			}
			for(int i=0; i<3; i++)
			{				
				for(int j=0; j<3; j++)
				{
					gl.glPushMatrix();
					gl.glTranslatef(-2.1f+(2.1f*i), -2.1f+(2.1f*j), -23.1f+(2.1f*l));
					cube[k++].draw(gl);
					gl.glPopMatrix();
				}
				
			}
			if(l ==2)
			{
				gl.glPopMatrix();
				if(rot >= 90.0f)
					rot = 90.0f;
				else
					rot += 4.0f;  
			}
		}
		
		cubeRotX -= 5.0f;
		cubeRotY -= 0.0f;
		gl.glPopMatrix();
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
			
			cubeRotX += dy * TOUCH_SCALE;
			cubeRotY += dx * TOUCH_SCALE;
		}
		else if(event.getAction() == MotionEvent.ACTION_UP)
		{
			//need to do this for detection of cube pressed
		}
		return true;
	}
}
