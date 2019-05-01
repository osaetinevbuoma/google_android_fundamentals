package com.modnsolutions.simpleasynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TEXT_STATE = "currentText";
    private static final String PROGRESS_STATE = "progressState";
    private TextView mTextView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView1);
        mProgressBar = findViewById(R.id.progressBar);

        // Restore TextView if there is a savedInstanceState
        if (savedInstanceState != null) {
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
            mProgressBar.setProgress(savedInstanceState.getInt(PROGRESS_STATE));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the state of the TextView
        outState.putString(TEXT_STATE, mTextView.getText().toString());
        outState.putInt(PROGRESS_STATE, mProgressBar.getProgress());
    }

    public void startTask(View view) {
        mTextView.setText(getString(R.string.napping));
        new SimpleAsyncTask(mTextView, mProgressBar).execute();
    }
}
