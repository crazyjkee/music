package com.example.andraft;

import java.io.FileDescriptor;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;

public class MusicRetriever {
    final String TAG = "MusicRetriever";

    static ContentResolver mContentResolver;

    // the items (songs) we have queried
    ArrayList<Item> mItems = new ArrayList<Item>();

  
    public MusicRetriever(ContentResolver cr) {
        mContentResolver = cr;
    }

  //пробегаемся курсором по sql lite
    public void prepare() {
        Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Log.i(TAG, "ищем музыку по URI");
        Log.i(TAG, "URI: " + uri.toString());

        Cursor cur = mContentResolver.query(uri, null,
                MediaStore.Audio.Media.IS_MUSIC + " = 1", null, null);
        Log.i(TAG, "Пробег закончился. " + (cur == null ? "Returned NULL." : "Returned a cursor."));

        if (cur == null) {
            //ошибка
            Log.e(TAG, "фейл,курсор null(не будет)");
            return;
        }
        if (!cur.moveToFirst()) {
            Log.e(TAG, "Курсор по какой-то причине не может начать с начала таблицы");
            return;
        }

        Log.i(TAG, "Listing...");

        // retrieve the indices of the columns where the ID, title, etc. of the song are
        int artistColumn = cur.getColumnIndex(MediaStore.Audio.Media.ARTIST);
        int titleColumn = cur.getColumnIndex(MediaStore.Audio.Media.TITLE);
        int albumColumn = cur.getColumnIndex(MediaStore.Audio.Media.ALBUM);
        int durationColumn = cur.getColumnIndex(MediaStore.Audio.Media.DURATION);
        int idColumn = cur.getColumnIndex(MediaStore.Audio.Media._ID);

        Log.i(TAG, "Title column index: " + String.valueOf(titleColumn));
        Log.i(TAG, "ID column index: " + String.valueOf(titleColumn));

        // заносим найденые песни в коллекцию
        do {
            Log.i(TAG, "ID: " + cur.getString(idColumn) + " Title: " + cur.getString(titleColumn));
            mItems.add(new Item(
                    cur.getLong(idColumn),
                    cur.getString(artistColumn),
                    cur.getString(titleColumn),
                    cur.getString(albumColumn),
                    cur.getLong(durationColumn)));
        } while (cur.moveToNext());

        Log.i(TAG, "Готово");
    }

    public ContentResolver getContentResolver() {
        return mContentResolver;
    }

    public ArrayList<Item> getItem() {
        if (mItems.size() <= 0) return null;
        return mItems;
    }

    public static class Item {
        long id;
        String artist;
        String title;
        String album;
        long duration;
        Date date;
       
        
        public Item(long id, String artist, String title, String album, long duration) {
            this.id = id;
            this.artist = artist;
            this.title = title;
            this.album = album;
            this.duration = duration;
            
            date = new Date(duration);
        }

        public long getId() {
            return id;
        }

        public String getArtist() {
            return artist;
        }

        public String getTitle() {
            return title;
        }

        public String getAlbum() {
            return album;
        }

        public long getDuration() {
            return duration;
        }
        
        public String getStringDuration(){
        	return new SimpleDateFormat("mm:ss").format(date);
        }
       

        public Uri getURI() {
            return ContentUris.withAppendedId(
                    android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id);
        }
        public Bitmap getAlbumart() 
        {
           Bitmap bm = null;
           try 
           {
               final Uri sArtworkUri = Uri
                   .parse("content://media/external/audio/albumart");

               Uri uri = ContentUris.withAppendedId(sArtworkUri, id);

               ParcelFileDescriptor pfd = mContentResolver
                   .openFileDescriptor(uri, "r");

               if (pfd != null) 
               {
                   FileDescriptor fd = pfd.getFileDescriptor();
                   bm = BitmapFactory.decodeFileDescriptor(fd);
               }
       } catch (Exception e) {
       }
       return bm;
       }
    }
}