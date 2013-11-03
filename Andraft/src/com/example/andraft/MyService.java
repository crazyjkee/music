package com.example.andraft;

import java.util.concurrent.TimeUnit;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore.Audio;
import android.util.Log;

public class MyService extends Service {

	 NotificationManager nm;
	 public final String LOG_TAG="myLogs";
	  
	  @Override
	  public void onCreate() {
		  Log.d(LOG_TAG,"onCreate");
	    super.onCreate();
	    nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	  }

	  public int onStartCommand(Intent intent, int flags, int startId) {
	    try {
	    	Log.d(LOG_TAG,"onStart");
	      TimeUnit.SECONDS.sleep(5);
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    }
	    sendNotif();
	    return super.onStartCommand(intent, flags, startId);
	  }
	  
	  @SuppressWarnings("deprecation")
	void sendNotif() {
	    // 1-я часть
		  Log.d(LOG_TAG,"I state");
	    Notification notif = new Notification(R.drawable.ic_launcher, "Text in status bar", 
	      System.currentTimeMillis());
	     
	    // 3-я часть
	    Log.d(LOG_TAG,"III state");
	    Intent intent = new Intent(this, MainActivity.class);
	   // intent.putExtra(MainActivity.FILE_NAME, "somefile");
	    PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
	    
	    // 2-я часть
	    Log.d(LOG_TAG,"II state");
	    notif.setLatestEventInfo(this, "Notification's title", "Notification's text", pIntent);
	    // ставим флаг, чтобы уведомление пропало после нажатия
	    notif.flags |= Notification.FLAG_AUTO_CANCEL;
	    notif.defaults|=Notification.DEFAULT_VIBRATE|Notification.DEFAULT_SOUND;
	    // отправляем
	    nm.notify(1, notif);
	  }
	  
	  public IBinder onBind(Intent arg0) {
	    return null;
	  }
	}