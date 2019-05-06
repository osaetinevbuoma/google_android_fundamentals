package com.example.android.hellosharedprefs;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Settings extends AppCompatActivity {
    private SharedPreferences mPreferences;
    private int mColor;
    private RadioGroup mColorGroup;
    private EditText mSettingsCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mColorGroup = findViewById(R.id.settings_color_group);
        mSettingsCount = findViewById(R.id.settings_count);
        mColor = ContextCompat.getColor(this, R.color.default_background);

        // Restore preferences.
        mPreferences = getSharedPreferences(MainActivity.sharedPrefFile, MODE_PRIVATE);

        int count = mPreferences.getInt(MainActivity.COUNT_KEY, 0);
        mSettingsCount.setText(String.format("%s", count));

        mColor = mPreferences.getInt(MainActivity.COLOR_KEY, mColor);

        if (mColor == Color.BLACK) {
            RadioButton blackRadioButton = findViewById(R.id.button_black);
            blackRadioButton.setChecked(true);
        }

        if (mColor == getResources().getColor(R.color.red_background)) {
            RadioButton redRadioButton = findViewById(R.id.button_red);
            redRadioButton.setChecked(true);
        }

        if (mColor == getResources().getColor(R.color.blue_background)) {
            RadioButton blueRadioButton = findViewById(R.id.button_blue);
            blueRadioButton.setChecked(true);
        }

        if (mColor == getResources().getColor(R.color.green_background)) {
            RadioButton greenRadioButton = findViewById(R.id.button_green);
            greenRadioButton.setChecked(true);
        }
    }

    public void saveSettings(View view) {
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();

        switch (mColorGroup.getCheckedRadioButtonId()) {
            case R.id.button_black:
                mColor = Color.BLACK;
                break;

            case R.id.button_red:
                mColor = getResources().getColor(R.color.red_background);
                break;

            case R.id.button_blue:
                mColor = getResources().getColor(R.color.blue_background);
                break;

            case R.id.button_green:
                mColor = getResources().getColor(R.color.green_background);
                break;
        }

        preferencesEditor.putInt(MainActivity.COLOR_KEY, mColor);
        preferencesEditor.putInt(MainActivity.COUNT_KEY, Integer.valueOf(mSettingsCount.getText()
                .toString()));
        preferencesEditor.apply();

        // Hide keyboard
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(
                INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager
                    .HIDE_NOT_ALWAYS);
        }

        Toast.makeText(this, "Preferences saved.", Toast.LENGTH_SHORT).show();
    }

    public void resetSettings(View view) {
        mSettingsCount.setText(null);
        mColorGroup.clearCheck();
        SharedPreferences.Editor preferenceEditor = mPreferences.edit();
        preferenceEditor.clear();
        preferenceEditor.apply();
    }
}
