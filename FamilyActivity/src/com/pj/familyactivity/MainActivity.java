package com.pj.familyactivity;




import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void aktivnosti(View view){
		Intent startNewActivityOpen = new Intent(MainActivity.this, Aktivnosti.class);
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
