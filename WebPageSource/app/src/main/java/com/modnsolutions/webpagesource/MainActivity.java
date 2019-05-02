package com.modnsolutions.webpagesource;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>,
        AdapterView.OnItemSelectedListener {
    private Spinner mSpinner;
    private EditText mURl;
    private TextView mSourceDisplay;

    private String mSpinnerLabel;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpinner = findViewById(R.id.spinner);
        mURl = findViewById(R.id.urlEditText);
        mSourceDisplay = findViewById(R.id.sourceTextView);

        if (mSpinner != null) {
            mSpinner.setOnItemSelectedListener(this);
        }

        // Create Adapter using the string array and default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.http_connection, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        if (mSpinner != null) {
            mSpinner.setAdapter(adapter);
        }

        if (getSupportLoaderManager().getLoader(0) != null) {
            getSupportLoaderManager().initLoader(0, null, this);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mSpinnerLabel = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Do Nothing
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        String url = "";
        if (bundle != null) {
            url = bundle.getString("url");
        }

        return new SourcePageLoader(this, url);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {
        mSourceDisplay.setText(s);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    public void getPageSource(View view) {
        // Concatenate selected spinner item with entered url
        String url = mSpinnerLabel + mURl.getText();
        Log.d(LOG_TAG, url);

        // Hide the keyboard when the user taps the search button.
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.
                INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.
                    HIDE_NOT_ALWAYS);
        }

        // Get connection status.
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.
                CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected() && mURl.length() != 0) {
            Bundle bundle = new Bundle();
            bundle.putString("url", url);
            getSupportLoaderManager().restartLoader(0, bundle, this);

            mSourceDisplay.setText(R.string.loading);
        } else {
            if (mURl.length() == 0) {
                mSourceDisplay.setText(R.string.valid_website_warning);
            } else {
                Toast.makeText(this, R.string.check_connection, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
