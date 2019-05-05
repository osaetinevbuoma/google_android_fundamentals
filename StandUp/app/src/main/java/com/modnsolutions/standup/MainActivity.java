package com.modnsolutions.standup;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private NotificationManager mNotificationManager;
    private AlarmManager mAlarmManager;

    private static final int NOTIFICATION_ID = 0;
    private static final String PRIMARY_CHANNEL_ID = "PRIMARY_NOTIFICATION_CHANNEL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToggleButton alarmToggle = findViewById(R.id.alarmToggle);

        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        createNotificationChannel();

        // Notify intent that is used with pending intent
        Intent notifyIntent = new Intent(this, AlarmReceiver.class);

        // Checking the state of PendingIntent to toggle alarm button off or on as expected.
        // The flag determines what happens if a PendingIntent whose intent matches the intent you
        // are trying to create already exists. The NO_CREATE flag returns null unless a
        // PendingIntent with a matching Intent exists.
        boolean alarmUp = (PendingIntent.getBroadcast(this, NOTIFICATION_ID, notifyIntent,
                PendingIntent.FLAG_NO_CREATE) != null);
        alarmToggle.setChecked(alarmUp);

        final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast(this,
                NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // Alarm Toggle listener
        alarmToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String toastMessage;
                if (isChecked) {
                    long repeatInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES;
//                    long triggerTime = SystemClock.elapsedRealtime() + repeatInterval;
                    long triggerTime = SystemClock.elapsedRealtime(); // start alarm immediately for testing

                    // If the Toggle is turned on, set the repeating alarm with a 15 minutes
                    // interval.
                    if (mAlarmManager != null) {
                        mAlarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                                triggerTime, repeatInterval, notifyPendingIntent);
                    }

                    // Set the toast message for the "on" case.
                    toastMessage = getString(R.string.toast_alarm_on);
                } else {
                    // Cancel notification  if the alarm is turned off.
                    mNotificationManager.cancelAll();

                    // Cancel alarm
                    if (mAlarmManager != null) mAlarmManager.cancel(notifyPendingIntent);

                    // Set the toast message for the "off" case.
                    toastMessage = getString(R.string.toast_alarm_off);
                }

                Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
            }
        });


    }

    /**
     * Create a Notification channel, for OREO and higher.
     */
    public void createNotificationChannel() {
        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            // Create the NotificationChannel with all the parameters.
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                    "Stand up notification", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notifies every 15 minutes to stand up and walk.");

            mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public void getNextAlarm(View view) {
        String toastMessage;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AlarmManager.AlarmClockInfo alarmClockInfo = mAlarmManager.getNextAlarmClock();
            if (alarmClockInfo != null) {
                toastMessage = "Next alarm time: " + alarmClockInfo.getTriggerTime();
                makeToast(toastMessage);
            } else {
                toastMessage = "No other alarm clock set.";
                makeToast(toastMessage);
            }
        } else {
            toastMessage = "Your android version does not support this function.";
            makeToast(toastMessage);
        }
    }

    private void makeToast(String toastMessage) {
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
    }
}
