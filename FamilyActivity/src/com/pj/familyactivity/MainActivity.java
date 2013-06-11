package com.pj.familyactivity;




import com.pj.familyactivity.R.string;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView txt=(TextView)findViewById(R.id.textViewUser);
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);		
		String usr = pref.getString("username", null);
		txt.setText(usr);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void zgodovina(View view){
		Intent startNewActivityOpen = new Intent(MainActivity.this, zgodovina.class);
    	startActivityForResult(startNewActivityOpen, 0);
	}
	public void druzina(View view){
		Intent startNewActivityOpen = new Intent(MainActivity.this, druzina.class);
    	startActivityForResult(startNewActivityOpen, 0);
	}
	public void nova(View view){
		Intent startNewActivityOpen = new Intent(MainActivity.this, nova_ak.class);
    	startActivityForResult(startNewActivityOpen, 0);
	}
}
