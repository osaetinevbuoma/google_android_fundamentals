package com.modnsolutions.keyboarddialphone;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mPhoneText;
    private static final String TAG = MainActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPhoneText = findViewById(R.id.phone_text);
        if (mPhoneText != null) {
            mPhoneText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    boolean handled = false;
                    if (actionId == EditorInfo.IME_ACTION_SEND) {
                        dialNumber();
                        handled = true;
                    }

                    return handled;
                }
            });
        }
    }

    /**
     * Send implicit intent to phone dialing app to dial number
     */
    private void dialNumber() {
        String phoneNum = null;

        // If the editText field is not null,
        // concatenate "tel: " with the phone number string.
        if (mPhoneText != null) phoneNum = "tel:" + mPhoneText.getText().toString();

        // Optional: Log the concatenated phone number for dialing.
        Log.d(TAG, "dialNumber: " + phoneNum);

        // Specify the intent
        Intent intent = new Intent(Intent.ACTION_DIAL);

        // Set the data for the intent as the phone number
        intent.setData(Uri.parse(phoneNum));

        // If the intent resolves to a package (app), start the activity with the intent.
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d(TAG, "Cannot handle implicit intent");
        }
    }
}
