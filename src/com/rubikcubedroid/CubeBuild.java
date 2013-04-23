package com.rubikcubedroid;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;

public class CubeBuild {
	
	private ByteBuffer byteBuffer;
	private FloatBuffer vertexBuffer;
	private FloatBuffer colorBuffer;
	
	private float[] vertices= {
			
			 -1.0f, -1.0f, -1.0f,	//lower back left (0)
	            1.0f, -1.0f, -1.0f,		//lower back right (1)
	            1.0f,  1.0f, -1.0f,		//upper back right (2)
	            -1.0f, 1.0f, -1.0f,		//upper back left (3)
	            -1.0f, -1.0f,  1.0f,	//lower front left (4)
	            1.0f, -1.0f,  1.0f,		//lower front right (5)
	            1.0f,  1.0f,  1.0f,		//upper front right (6)
	            -1.0f,  1.0f,  1.0f		//upper front left (7)   
			
	};
	
	private byte indices[] = {
			0, 4, 5,    0, 5, 1,
            1, 5, 6,    1, 6, 2,
            2, 6, 7,    2, 7, 3,
            3, 7, 4,    3, 4, 0,
            4, 7, 6,    4, 6, 5,
            3, 0, 1,    3, 1, 2    
	};
	
	
	 /** The initial color definition */	
		private float colors[] = {
				            0.0f,  1.0f,  0.0f,  1.0f,  //Position 0 : compatible with Position 4 
				            0.0f,  0.0f,  1.0f,  1.0f,	
				            0.0f,  0.0f,  1.0f,  1.0f,
				            1.0f,  0.0f,  0.0f,  1.0f,  //Position 3 : compatible with Position 7 
				            0.0f,  1.0f,  0.0f,  1.0f,	//Position 4 : compatible with Position 0
				            1.0f,  1.0f,  0.0f,  1.0f,
				            1.0f,  1.0f,  0.0f,  1.0f,
				            1.0f,  0.0f,  0.0f,  1.0f	//Position 7 : compatible with Position 3 
				    								};
	   
	public CubeBuild()
			{
				ByteBuffer tmpBuffer = ByteBuffer.allocateDirect(vertices.length*4);
				tmpBuffer.order(ByteOrder.nativeOrder());
				vertexBuffer = tmpBuffer.asFloatBuffer();
				vertexBuffer.put(vertices);
				vertexBuffer.position(0);
				
				byteBuffer = ByteBuffer.allocateDirect(indices.length); 	
				byteBuffer.put(indices);  
				byteBuffer.position(0); 
				
				//
				tmpBuffer = ByteBuffer.allocateDirect(colors.length * 4);
				tmpBuffer.order(ByteOrder.nativeOrder());
				colorBuffer = tmpBuffer.asFloatBuffer();
				colorBuffer.put(colors);
				colorBuffer.position(0);
			}

	
	public void draw(GL10 gl10)
	{	
		gl10.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl10.glEnableClientState(GL10.GL_COLOR_ARRAY);
		
		gl10.glFrontFace(GL10.GL_CCW);		//set the face rotation
		gl10.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		gl10.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
		
		gl10.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_BYTE, byteBuffer);
		
		gl10.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl10.glDisableClientState(GL10.GL_COLOR_ARRAY);
		//Log.v("ERROR ON CUBEBUILD","draw Error");
	}
}
