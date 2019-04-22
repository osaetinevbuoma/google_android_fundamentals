package com.modnsolutions.dialogforalert;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Display a alert dialog
     *
     * @param view button view
     */
    public void onClickShowAlert(View view) {
        AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(MainActivity.this);

        // Ser the dialog title and message.
        myAlertBuilder.setTitle(getString(R.string.alert_title));
        myAlertBuilder.setMessage(getString(R.string.alert_message));

        // Add the dialog buttons.
        myAlertBuilder.setPositiveButton(getString(R.string.ok_button),
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User clicked OK button.
                displayToast("Pressed OK");
            }
        });

        myAlertBuilder.setNegativeButton(getString(R.string.cancel_button),
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User canceled the dialog.
                displayToast("Pressed Cancel");
            }
        });

        // Create and show the AlertDialog.
        myAlertBuilder.show();
    }

    /**
     * Display Toast message
     * @param message Toast message to display
     */
    private void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
