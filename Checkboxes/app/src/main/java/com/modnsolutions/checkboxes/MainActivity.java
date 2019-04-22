package com.modnsolutions.checkboxes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showToast(View view) {
        String message = "Toppings: ";
        CheckBox chocolateSyrup = findViewById(R.id.chocolate_syrup);
        CheckBox sprinklers = findViewById(R.id.sprinklers);
        CheckBox crushedNuts = findViewById(R.id.crushed_nuts);
        CheckBox cherries = findViewById(R.id.cherries);
        CheckBox orioCookieCrumbles = findViewById(R.id.orio_cookie_crumbles);

        if (chocolateSyrup.isChecked()) message += getString(R.string.chocolate_syrup) + " ";
        if (sprinklers.isChecked()) message += getString(R.string.sprinklers) + " ";
        if (crushedNuts.isChecked()) message += getString(R.string.crushed_nuts) + " ";
        if (cherries.isChecked()) message += getString(R.string.cherries) + " ";
        if (orioCookieCrumbles.isChecked()) message +=
                getString(R.string.orio_cookie_crumbles) + " ";

        displayToast(message);
    }

    /**
     * Display Toast message
     *
     * @param message Toast message to display
     */
    private void displayToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
