package com.pj.familyactivity;

import java.util.Collection;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;

public class Aktivnosti extends Activity {

	/** Called when the activity is first created. */
	ListView lv;
	String[] datumi;
	Context context;
	@Override
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.aktivnosti);
	    context=this;
	    lv=(ListView)findViewById(R.id.listView1);	    
	    datumi=this.getResources().getStringArray(R.array.podatki);
	    
	    ArrayAdapter<String> aa=new ArrayAdapter<String>(this,R.layout.lv_vrstica,R.id.listText,datumi);
	    lv.setAdapter(aa);
	    lv.setOnItemClickListener(new listClick());
	    
	    Gson gson=new Gson();
	    String[] strings = {"abc", "def", "ghi"};
	    String json=gson.toJson(strings);
	    try{
	    	FileWriter fw=new FileWriter("/mnt/sdcard/Download/strings.json");
	    	fw.write(json);
	    	fw.close();
	    }
	    catch(IOException e){
	    	e.printStackTrace();
	    }
	}
	private class listClick implements OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> aa,View view,int position,long arg3){
			TextView listText = (TextView)findViewById(R.id.listText);
			String text=listText.getText().toString();
			Toast.makeText(context,text +" "+ position,Toast.LENGTH_SHORT).show();
		}
	}
}
