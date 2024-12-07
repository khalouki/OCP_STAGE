package com.example.finale2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Your code here
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Your code here
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Not needed for this example
        return null;
    }
}

