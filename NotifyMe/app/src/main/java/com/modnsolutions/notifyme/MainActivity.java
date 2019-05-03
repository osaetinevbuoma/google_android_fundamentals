package com.modnsolutions.notifyme;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button_notify;
    private Button button_cancel;
    private Button button_update;
    private NotificationManager mNotifyManager;
    private NotificationReceiver mReceiver = new NotificationReceiver();

    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private static final String ACTION_UPDATE_NOTIFICATION =
            "com.modnsolutions.notifyme.ACTION_UPDATE_NOTIFICATION";
    private static final String CLEAR_NOTIFICATION = "com.modnsolutions.notifyme.CLEAR_NOTIFICATION";
    private static final int NOTIFICATION_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_notify = findViewById(R.id.notify);
        button_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });

        createNotificationChannel();

        button_update = findViewById(R.id.update);
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update the notification.
                updateNotification();
            }
        });

        button_cancel = findViewById(R.id.cancel);
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cancel the notification.
                cancelNotification();
            }
        });

        // Toggle notification buttons
        setNotificationButtonState(true, false, false);

        // To receive the ACTION_UPDATE_NOTIFICATION intent, register the broadcast receiver
        registerReceiver(mReceiver, new IntentFilter(ACTION_UPDATE_NOTIFICATION));

        // Register CLEAR_NOTIFICATION intent
        registerReceiver(mReceiver, new IntentFilter(CLEAR_NOTIFICATION));
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    public void sendNotification() {
        // Update action button process, if needed
        // @see <a href="https://codelabs.developers.google.com/codelabs/android-training-notifications/index.html?index=..%2F..%2Fandroid-training&authuser=1#4">More info</a>
        Intent updateIntent = new Intent(ACTION_UPDATE_NOTIFICATION);
        PendingIntent updatePendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID,
                updateIntent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();

        // Update action button process, if needed
        notifyBuilder.addAction(R.drawable.ic_update, "Update Notification",
                updatePendingIntent);

        // PendingIntent for when the user clears all or single notification in status bar.
        // App should be notified so that buttons can return to default state.
        Intent clearIntent = new Intent(CLEAR_NOTIFICATION);
        PendingIntent clearPendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID,
                clearIntent, PendingIntent.FLAG_ONE_SHOT);

        // Set delete intent to notify app when notification is cleared.
        notifyBuilder.setDeleteIntent(clearPendingIntent);

        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());
        setNotificationButtonState(false, true, true);
    }

    /**
     * Create a NotificationChannel
     */
    public void createNotificationChannel() {
        mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Notification channels are available in API 26 and higher so check API version before
        // setting up notification channel.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create a NotificationChannel
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                    "Mascot Notification", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification from Mascot");
            notificationChannel.setImportance(NotificationManager.IMPORTANCE_HIGH);

            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }

    /**
     * Building notification
     * @return notifyBuilder
     */
    private NotificationCompat.Builder getNotificationBuilder() {
        // Create a content intent for the notification
        // When users tap the notification, it should perform an action like launch
        // an activity (pass data to the activity if there's need for it).
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this,
                NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this,
                PRIMARY_CHANNEL_ID)
                .setContentTitle("You've been notified!")
                .setContentText("This is your notification text.")
                .setSmallIcon(R.drawable.ic_android)
                .setContentIntent(notificationPendingIntent)
                .setAutoCancel(true)
                // For Android 7.1 and lower, set notification priority in the builder.
                // For Android 8.0 and higher, set notification priority in the notification
                // channel
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                // You can set the sound, vibration and LED-color pattern to the default values.
                // The high-priority notification will not drop down in front of the active
                // screen unless both the priority and the defaults are set. Setting the priority
                // alone is not enough.
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        return notifyBuilder;
    }

    /**
     * Update notification
     */
    public void updateNotification() {
        Bitmap androidImage = BitmapFactory.decodeResource(getResources(), R.drawable.mascot_1);
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        notifyBuilder.setStyle(new NotificationCompat.BigPictureStyle()
                .bigPicture(androidImage)
                .setBigContentTitle("Notification Updated!"));

        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());

        setNotificationButtonState(false, false, true);
    }

    /**
     * Cancel a notification
     */
    public void cancelNotification() {
        mNotifyManager.cancel(NOTIFICATION_ID);
        setNotificationButtonState(true, false, false);
    }

    void setNotificationButtonState(Boolean isNotifyEnabled, Boolean isUpdateEnabled,
                                    Boolean isCancelEnabled) {
        button_notify.setEnabled(isNotifyEnabled);
        button_update.setEnabled(isUpdateEnabled);
        button_cancel.setEnabled(isCancelEnabled);
    }


    public class NotificationReceiver extends BroadcastReceiver {
        public NotificationReceiver() { }

        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case ACTION_UPDATE_NOTIFICATION:
                    updateNotification();
                    break;

                case CLEAR_NOTIFICATION:
                    setNotificationButtonState(true, false,
                            false);
                    break;
            }
        }
    }
}
