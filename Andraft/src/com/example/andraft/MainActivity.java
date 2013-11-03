
package com.example.andraft;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.andraft.MusicRetriever.Item;

public class MainActivity extends Activity {
      MusicRetriever mr;
	  ArrayList<Item> items;
	  BaseAdapt baseAdapt;
	  
	  String LOG_TAG="music logs";

	  /** Called when the activity is first created. */
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);
	    
         mr = new MusicRetriever(getContentResolver());
         mr.prepare();
         items = mr.getItem();
	    // создаем адаптер
	    baseAdapt = new BaseAdapt(this, items);

	    // настраиваем список
	    ListView lvMain = (ListView) findViewById(R.id.lvMain);
	    lvMain.setClickable(true);
	   
	   
	   
	   
	 

		   

	   lvMain.setAdapter(baseAdapt);
	  }

	  
	  

	  // выводим информацию о корзине
	  /*public void showResult(View v) {
	    String result = "Товары в корзине:";
	    for (Product p : boxAdapter.getBox()) {
	      if (p.box)
	        result += "\n" + p.name;
	    }
	    Toast.makeText(this, result, Toast.LENGTH_LONG).show();
	  }*/
	}