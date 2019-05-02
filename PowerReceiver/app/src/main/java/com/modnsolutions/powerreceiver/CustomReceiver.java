package com.modnsolutions.powerreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {
    private static final String ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID +
            ".ACTION_CUSTOM_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();

        if (intentAction != null) {
            String toastMessage = "Unknown intent action";
            switch (intentAction) {
                case Intent.ACTION_POWER_CONNECTED:
                    toastMessage = "Power connected";
                    break;

                case Intent.ACTION_POWER_DISCONNECTED:
                    toastMessage = "Power disconnected";
                    break;

                case Intent.ACTION_HEADSET_PLUG:
                    Log.d("CustomReceiver", String.valueOf(intent.getIntExtra("state", 0)));
                    if (intent.getIntExtra("state", 0) == 0) toastMessage =
                            "Headset disconnected";
                    else toastMessage = "Headset connected";
                    break;

                case ACTION_CUSTOM_BROADCAST:
                    toastMessage = "Custom Broadcast Received";
                    if (intent.hasExtra("number")) {
                        int number = intent.getIntExtra("number", 0);
                        toastMessage += "\nSquare of the random number: " + Math.pow(number, 2);
                    }
                    break;
            }

            // Display the toast.
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
        }
    }
}
