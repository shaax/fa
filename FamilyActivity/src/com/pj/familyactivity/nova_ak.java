package com.pj.familyactivity;

import android.app.Activity;
import android.content.Context;

import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;

public class nova_ak extends Activity implements SensorEventListener{
    SharedPreferences pref;
    String user;
    private static String uploadPHP = "http://10.0.2.2:8080/android_connect/dodaj.php";
    //private static String uploadPHP = "http://164.8.219.49:8080/android_connect/dodaj.php";
    private ProgressDialog pDialog;
    private static final String TAG_SUCCESS = "success";
    JSONParser jParser = new JSONParser();
    private float mLastX, mLastY, mLastZ;
	private boolean mInitialized;
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private final float NOISE = (float) 2.0;
	int stevec=0;
	int st2=0;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.nova_ak);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        user = pref.getString("username", null);

        mInitialized = false;
	    mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	    mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	    mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	protected void onResume() {
		super.onResume();
		//mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);			
	}
	protected void onPause() {
		super.onPause();
		//mSensorManager.unregisterListener(this);		
	}
	protected void onDestroy(){
		super.onDestroy();
		mSensorManager.unregisterListener(this);
	}
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
	}
	@Override
	public void onSensorChanged(SensorEvent event) {		
		TextView st= (TextView)findViewById(R.id.st);		
		float x = event.values[0];
		float y = event.values[1];
		float z = event.values[2];
		if (!mInitialized) {
			mLastX = x;
			mLastY = y;
			mLastZ = z;
			if(x+y+z>2){
				stevec++;				
				st.setText(Integer.toString(stevec));
			}			
			mInitialized = true;
		} else {
			float deltaX = Math.abs(mLastX - x);
			float deltaY = Math.abs(mLastY - y);
			float deltaZ = Math.abs(mLastZ - z);
			if (deltaX < NOISE) deltaX = (float)0.0;
			if (deltaY < NOISE) deltaY = (float)0.0;
			if (deltaZ < NOISE) deltaZ = (float)0.0;
			mLastX = x;
			mLastY = y;
			mLastZ = z;
			
			if((deltaX+deltaY+deltaZ)>4){				
				st2++;
				if(st2%3==0){
					stevec++;
					st.setText(Integer.toString(stevec));
					st2=0;
				}				
			}			
		}
	}

    public void upload(View view){
        new vstavi().execute();

    }
    class vstavi extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(nova_ak.this);
            pDialog.setMessage("Obdelujem...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        protected String doInBackground(String... args) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("koraki", ""+stevec));
            params.add(new BasicNameValuePair("user", user));

            JSONObject json = jParser.makeHttpRequest(uploadPHP, "POST", params);

            try {

                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(String file_url) {

            pDialog.dismiss();
        }
    }
}
