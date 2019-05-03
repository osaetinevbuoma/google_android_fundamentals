package com.modnsolutions.androidservices;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyIntentService extends IntentService {
    private static final String LOG_TAG = MyIntentService.class.getCanonicalName();

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(LOG_TAG, "MyIntentService Started.");

        for (int i = 0; i < 10; i++) {
            Log.d(LOG_TAG, "MyIntentService processing " + i + " time(s).");
        }
    }
}
