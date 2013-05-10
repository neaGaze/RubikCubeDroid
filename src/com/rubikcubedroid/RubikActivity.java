package com.rubikcubedroid;

import com.rubikcubedroid.CubeBuild;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class RubikActivity extends Activity implements OnClickListener{

	private Button buttonPlay,buttonChallenge;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_rubik);
		initiate_buttons();
	}

	private void initiate_buttons() {
		// TODO Auto-generated method stub
		
		this.buttonPlay = (Button)this.findViewById(R.id.button1); 
		this.buttonPlay.setOnClickListener(this);
		
		this.buttonChallenge = (Button)this.findViewById(R.id.button2);
		this.buttonChallenge.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rubik, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		if(arg0 == this.findViewById(R.id.button1))
			{   
				//Toast.makeText(this, "Works", Toast.LENGTH_LONG).show();
				this.startActivity(new Intent(this,GraphicsActivity.class)); 
			}
		else if(arg0 == this.findViewById(R.id.button2))
			{
				Toast.makeText(this, "Also Works", Toast.LENGTH_SHORT).show();
			}
		else
			{
				Toast.makeText(this, "Doesn't work", Toast.LENGTH_SHORT).show();
			}
	}
	
}
