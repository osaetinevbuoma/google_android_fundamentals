package com.modnsolutions.hellotoast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int mCount = 0;
    private TextView mShowCount;
    private Button mReset;
    private Button mButtonCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = (TextView) findViewById(R.id.show_count);
        mButtonCount = (Button) findViewById(R.id.button_count);
        mReset = (Button) findViewById(R.id.button_reset);
        mReset.setActivated(false);
        mReset.setBackgroundColor(getResources().getColor(R.color.deactivated));
    }

    public void showToast(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void countUp(View view) {
        mCount++;
        mReset.setActivated(true);
        mReset.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        if (mShowCount != null) {
            mShowCount.setText(Integer.toString(mCount));

            if (mCount % 2 == 0) mButtonCount.setBackgroundColor(getResources()
                    .getColor(R.color.colorPrimaryDark));
            else mButtonCount.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }
    }

    public void resetCount(View view) {
        if (mShowCount != null) {
            mCount = 0;
            mShowCount.setText("0");
            mReset.setActivated(false);
            mReset.setBackgroundColor(getResources().getColor(R.color.deactivated));
            mButtonCount.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
    }
}
