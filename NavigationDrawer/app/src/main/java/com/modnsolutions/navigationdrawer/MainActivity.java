package com.modnsolutions.navigationdrawer;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open_navigation_label, R.string.close_navigation_label);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_import:
                        displayToast(menuItem.getTitle().toString());
                        return true;
                    case R.id.action_gallery:
                        displayToast(menuItem.getTitle().toString());
                        return true;
                    case R.id.action_slideshow:
                        displayToast(menuItem.getTitle().toString());
                        return true;
                    case R.id.action_tools:
                        displayToast(menuItem.getTitle().toString());
                        return true;
                    case R.id.action_share:
                        displayToast(menuItem.getTitle().toString());
                        return true;
                    case R.id.action_send:
                        displayToast(menuItem.getTitle().toString());
                        return true;
                    default: return false;
                }

            }
        });
    }

    private void displayToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
