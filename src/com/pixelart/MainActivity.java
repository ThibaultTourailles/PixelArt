package com.pixelart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	EditText pixelSize;
	Button start;
	TextView enter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.enter = (TextView) this.findViewById(R.id.enter);
		Typeface font2 = Typeface.createFromAsset(getAssets(), "font/FEASFBRG.TTF");
//		this.enter.setTypeface(font2);
		this.pixelSize = (EditText) this.findViewById(R.id.pixelSize);
		this.start = (Button) this.findViewById(R.id.start);
		
		this.start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String stringSize = MainActivity.this.pixelSize.getText().toString();
				int size = stringSize.equals("") ? 30 : Integer.parseInt(stringSize);
				Intent i = new Intent(MainActivity.this, StartActivity.class);
				i.putExtra("size", size);
				MainActivity.this.startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}

}
