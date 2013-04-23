package com.rubikcubedroid;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;

public class CubeBuild {
	
	private ByteBuffer byteBuffer;
	private FloatBuffer vertexBuffer;
	/** The buffer holding the normals */
	private FloatBuffer normalBuffer;
	private FloatBuffer colorBuffer;
	
	private float[] vertices= {
		/*	// Vertices according to faces
			-1.0f, -1.0f, 1.0f, //v0
			1.0f, -1.0f, 1.0f, 	//v1
			-1.0f, 1.0f, 1.0f, 	//v2
			1.0f, 1.0f, 1.0f, 	//v3

			1.0f, -1.0f, 1.0f, 	//...
			1.0f, -1.0f, -1.0f, 
			1.0f, 1.0f, 1.0f, 
			1.0f, 1.0f, -1.0f,

			1.0f, -1.0f, -1.0f, 
			-1.0f, -1.0f, -1.0f, 
			1.0f, 1.0f, -1.0f, 
			-1.0f, 1.0f, -1.0f,

			-1.0f, -1.0f, -1.0f, 
			-1.0f, -1.0f, 1.0f, 
			-1.0f, 1.0f, -1.0f, 
			-1.0f, 1.0f, 1.0f,

			-1.0f, -1.0f, -1.0f, 
			1.0f, -1.0f, -1.0f, 
			-1.0f, -1.0f, 1.0f, 
			1.0f, -1.0f, 1.0f,

			-1.0f, 1.0f, 1.0f, 
			1.0f, 1.0f, 1.0f, 
			-1.0f, 1.0f, -1.0f, 
			1.0f, 1.0f, -1.0f, 
			*/
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
			
			// Faces definition
		/*	0, 1, 3, 0, 3, 2, 		// Face front
			4, 5, 7, 4, 7, 6, 		// Face right
			8, 9, 11, 8, 11, 10, 	// ...
			12, 13, 15, 12, 15, 14, 
			16, 17, 19, 16, 19, 18, 
			20, 21, 23, 20, 23, 22, 
			0, 4, 5,    0, 5, 1,
            //and so on...  */
            1, 5, 6,    1, 6, 2,
            2, 6, 7,    2, 7, 3,
            3, 7, 4,    3, 4, 0,
            4, 7, 6,    4, 6, 5,
            3, 0, 1,    3, 1, 2    
	};
	
	private float normals[] = {
			//Normals
			0.0f, 0.0f, 1.0f, 						
			0.0f, 0.0f, -1.0f, 
			0.0f, 1.0f, 0.0f, 
			0.0f, -1.0f, 0.0f, 
			
			0.0f, 0.0f, 1.0f, 
			0.0f, 0.0f, -1.0f, 
			0.0f, 1.0f, 0.0f, 
			0.0f, -1.0f, 0.0f,
			
			0.0f, 0.0f, 1.0f, 
			0.0f, 0.0f, -1.0f, 
			0.0f, 1.0f, 0.0f, 
			0.0f, -1.0f, 0.0f,
			
			0.0f, 0.0f, 1.0f, 
			0.0f, 0.0f, -1.0f, 
			0.0f, 1.0f, 0.0f, 
			0.0f, -1.0f, 0.0f,
			
			0.0f, 0.0f, 1.0f, 
			0.0f, 0.0f, -1.0f, 
			0.0f, 1.0f, 0.0f, 
			0.0f, -1.0f, 0.0f,
			
			0.0f, 0.0f, 1.0f, 
			0.0f, 0.0f, -1.0f, 
			0.0f, 1.0f, 0.0f, 
			0.0f, -1.0f, 0.0f,
								};
	
	 /** The initial color definition */	
		private float colors[] = {
				            0.0f,  1.0f,  0.0f,  1.0f,
				            0.0f,  1.0f,  0.0f,  1.0f,
				            1.0f,  0.5f,  0.0f,  1.0f,
				            1.0f,  0.5f,  0.0f,  1.0f,
				            1.0f,  0.0f,  0.0f,  1.0f,
				            1.0f,  0.0f,  0.0f,  1.0f,
				            0.0f,  0.0f,  1.0f,  1.0f,
				            1.0f,  0.0f,  1.0f,  1.0f
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
				
				tmpBuffer = ByteBuffer.allocateDirect(normals.length * 4);
				tmpBuffer.order(ByteOrder.nativeOrder());
				normalBuffer = tmpBuffer.asFloatBuffer();
				normalBuffer.put(normals);
				normalBuffer.position(0);
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
		gl10.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		gl10.glEnableClientState(GL10.GL_COLOR_ARRAY);
		
		gl10.glFrontFace(GL10.GL_CCW);		//set the face rotation
		gl10.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		gl10.glNormalPointer(GL10.GL_FLOAT, 0, normalBuffer);
		
		gl10.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_BYTE, byteBuffer);
		gl10.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl10.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		gl10.glDisableClientState(GL10.GL_COLOR_ARRAY);
		//Log.v("ERROR ON CUBEBUILD","draw Error");
	}
}
