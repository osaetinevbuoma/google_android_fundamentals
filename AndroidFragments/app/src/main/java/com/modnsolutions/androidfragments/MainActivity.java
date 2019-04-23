package com.modnsolutions.androidfragments;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements MainFragment.OnButtonClickedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add MainFragment to MainActivity
        MainFragment mainFragment = new MainFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.first_fragment_container, mainFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onButtonClicked(String message) {
        // If second fragment exist in activity layout i.e. tablets, send message to second fragment
        // else send intent to second activity that holds second fragment (i.e. phone)
        if (findViewById(R.id.second_fragment_container) != null) {
            // Create a bundle to put the message to be sent
            Bundle bundle = new Bundle();
            bundle.putString(SecondFragment.MESSAGE, message);

            // Create the second fragment and attach the bundle containing message
            SecondFragment secondFragment = new SecondFragment();
            secondFragment.setArguments(bundle);

            // Create fragment transaction and replace second layout with SecondFragment
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.second_fragment_container, secondFragment);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();
        } else {
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra(SecondFragment.MESSAGE, message);
            startActivity(intent);
        }
    }
}
