package com.modnsolutions.androidfragments;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Get intent send from MainActivity
        Intent intent = getIntent();
        String message = intent.getStringExtra(SecondFragment.MESSAGE);

        // Bundle intent message to be sent to SecondFragment
        Bundle bundle = new Bundle();
        bundle.putString(SecondFragment.MESSAGE, message);

        // Attach bundled message to SecondFragment
        SecondFragment secondFragment = new SecondFragment();
        secondFragment.setArguments(bundle);

        // Perform transaction on SecondFragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.second_fragment_container, secondFragment);
        transaction.commit();
    }
}
