package com.example.andraft;

import java.util.ArrayList;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.andraft.MusicRetriever.Item;

public class BaseAdapt extends BaseAdapter {
	  Context ctx;
	  LayoutInflater lInflater;
	  ArrayList<Item> objects;

	  BaseAdapt(Context context, ArrayList<Item> items) {
	    ctx = context;
	    objects = items;
	    lInflater = (LayoutInflater) ctx
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	  }

	  // кол-во элементов
	  @Override
	  public int getCount() {
	    return objects.size();
	  }

	  // элемент по позиции
	  @Override
	  public Object getItem(int position) {
	    return objects.get(position);
	  }
	  
	  //Название песни по позиции
	  public String getTitle(int position){
		  return objects.get(position).getTitle();
	  }
	   
	  
	  // uri по позиции
	  public Uri getUri(int position){
		  return objects.get(position).getURI();
	  }

	  // id по позиции
	  @Override
	  public long getItemId(int position) {
	    return position;
	  }

	  // пункт списка
	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    // используем созданные, но не используемые view
	    View view = convertView;
	    if (view == null) {
	      view = lInflater.inflate(R.layout.items, parent, false);
	    }
        

	    Item p = getItemPos(position);

	    // заполняем View в пункте списка данными из товаров: наименование, альбом
	    // и длительность
	    ((TextView) view.findViewById(R.id.title)).setText(p.getTitle());
	    ((TextView) view.findViewById(R.id.album)).setText(p.getAlbum());
	    ((TextView) view.findViewById(R.id.duration)).setText(p.getStringDuration());
	    //((ImageView) view.findViewById(R.id.ivImage)).setImageBitmap(p.getAlbumart());
	    //CheckBox cbBuy = (CheckBox) view.findViewById(R.id.cbBox);
	
		//cbBuy.setOnCheckedChangeListener(myCheckChangList);
	    // пишем позицию
	    //cbBuy.setTag(position);
	    // заполняем данными из товаров: в корзине или нет
	   // cbBuy.setChecked(p.);
	    
	   
	    
	    return view;
	  }

	  // товар по позиции
	  Item getItemPos(int position) {
	    return ((Item) getItem(position));
	  }

	  // содержимое корзины
	 /* ArrayList<Item> getBox() {
	    ArrayList<Item> box = new ArrayList<Item>();
	    for (Item p : objects) {
	      // если в корзине
	      if (p.box)
	        box.add(p);
	    }
	    return box;
	  }
*/
	 
	 
	}