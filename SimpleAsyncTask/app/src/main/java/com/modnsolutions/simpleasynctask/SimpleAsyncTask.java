package com.modnsolutions.simpleasynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {
    private WeakReference<TextView> mTextView;

    SimpleAsyncTask(TextView tv) {
        mTextView = new WeakReference<>(tv);
    }

    @Override
    protected String doInBackground(Void... voids) {
        // Generate a random number between 0 and 10
        Random r = new Random();
        int n = r.nextInt(11);

        // Make the task take long enough that we have time to rotate the phone
        // while it is running
        int s = n * 2000;

        long startTime = System.currentTimeMillis();

        // Sleep for the random amount of time
        try {
            Thread.sleep(s);
            publishProgress((int) (System.currentTimeMillis() - startTime));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Return a String result
        return "Awake at last after sleeping for " + s + " milliseconds!";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        mTextView.get().setText("Sleep elapsed time: " + values[0] + " milliseconds.");
    }

    @Override
    protected void onPostExecute(String s) {
        mTextView.get().setText(s);
    }
}
