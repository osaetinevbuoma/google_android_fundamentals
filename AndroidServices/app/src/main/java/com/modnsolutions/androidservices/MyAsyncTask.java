package com.modnsolutions.androidservices;

import android.os.AsyncTask;
import android.util.Log;

import static com.modnsolutions.androidservices.MyService.LOG_TAG;

public class MyAsyncTask extends AsyncTask<Integer, Integer, Integer> {
    @Override
    protected Integer doInBackground(Integer... integers) {
        int startId = integers[0];

        int i = 0;
        while (i <= 3) {
            try {
                publishProgress(i);
                Thread.sleep(10000);
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return startId;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        Log.d(LOG_TAG, "MyService processing iteration: " + values[0]);
    }

    @Override
    protected void onPostExecute(Integer i) {
        super.onPostExecute(i);
        Log.d(LOG_TAG, "MyService completed process: " + i);
    }
}
