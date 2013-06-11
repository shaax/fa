package com.pj.familyactivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class druzina extends ListActivity {
    SharedPreferences pref;
    String dIDS;
    int dID;
    private ProgressDialog pDialog;
    JSONParser jParser = new JSONParser();
    ArrayList<HashMap<String, String>> seznamUpo;
    private static String druzinaPHP = "http://10.0.2.2:8080/android_connect/druzina.php";
    //private static String druzinaPHP = "http://164.8.219.49:8080/android_connect/druzina.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_ELEMENTI = "elementi";
    JSONArray elementi = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.druzina);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        dIDS = pref.getString("druzinaID","0");
        dID=Integer.parseInt(dIDS);
        seznamUpo = new ArrayList<HashMap<String, String>>();
        new nalozi().execute();
        ListView lv = getListView();
    }

    class nalozi extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(druzina.this);
            pDialog.setMessage("Obdelujem..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        protected String doInBackground(String... args) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("dID", ""+dID));
            JSONObject json = jParser.makeHttpRequest(druzinaPHP, "GET", params);

            try {
                int success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    elementi = json.getJSONArray(TAG_ELEMENTI);

                    for (int i = 0; i < elementi.length(); i++) {
                        JSONObject c = elementi.getJSONObject(i);
                        String upo = c.getString("user");
                        String koraki=c.getString("koraki");
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("user", upo);
                        map.put("koraki", koraki);
                        seznamUpo.add(map);
                    }
                } else {
                    // prazno
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
            runOnUiThread(new Runnable() {
                public void run() {
                    ListAdapter adapter = new SimpleAdapter(druzina.this, seznamUpo, R.layout.list_item, new String[] { "user", "koraki"},
                            new int[] { R.id.name, R.id.koraki });
                    setListAdapter(adapter);
                }
            });

        }

    }
}