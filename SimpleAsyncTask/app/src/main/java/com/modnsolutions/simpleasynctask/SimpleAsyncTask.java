package com.modnsolutions.simpleasynctask;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {
    private WeakReference<TextView> mTextView;
    private WeakReference<ProgressBar> mProgressBar;

    SimpleAsyncTask(TextView tv, ProgressBar pb) {
        mTextView = new WeakReference<>(tv);
        mProgressBar = new WeakReference<>(pb);
    }

    @Override
    protected String doInBackground(Void... voids) {
        // Generate a random number between 0 and 10
        Random r = new Random();
        int n = r.nextInt(11);

        // Make the task take long enough that we have time to rotate the phone
        // while it is running
        int s = n * 2000;

        // Sleep for the random amount of time
        for (int i = 0; i < s; i++) {
            double progress = (double) i/s * 100;
            publishProgress((int) Math.round(progress));
        }
        /*try {
            Thread.sleep(s);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        // Return a String result
        return "Awake at last after sleeping for " + s + " milliseconds!";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        mProgressBar.get().setVisibility(View.VISIBLE);
        mProgressBar.get().setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        mTextView.get().setText(s);
    }
}
