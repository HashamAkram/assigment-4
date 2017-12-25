package com.example.hp.assignment4;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.IntDef;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

public class MySimpleService extends Service {
    private static final String TAG = "HTAG";
    private MediaPlayer mp;
    public MySimpleService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");

        return null;
    }



    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
     //   return super.onStartCommand(intent, flags, startId);



        /*mp = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        mp.setLooping(true);
        mp.start();*/


        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                if(intent != null){

                    String time = intent.getStringExtra("time");
                    int intTime = Integer.parseInt(time);

                    for (int i = 0; i < intTime; i++) {
                        // Log.d(TAG, "onStartCommand: Loop : " + i);
                        try {
                            Thread.sleep(1000);
                            Log.d(TAG, "onStartCommand: Loop : " + i);

                            EventBus.getDefault().post(new MessageEvent(i));

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        t.start();




        Log.d(TAG, "onStartCommand:  ");
        return START_STICKY;
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
     //   mp.stop();

    }



}
