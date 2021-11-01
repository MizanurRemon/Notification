package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText titleText, messageText;
    AppCompatButton sendButton;
    private static final String TAG = "YourNotification";
    private static final int NOTIFICATION_ID = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleText = (EditText) findViewById(R.id.titleTextID);
        messageText = (EditText) findViewById(R.id.messageTextID);
        sendButton = (AppCompatButton) findViewById(R.id.sendButtonID);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("my alert", "my alert", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);
        }

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "my alert");
                builder.setContentTitle(titleText.getText().toString().trim());
                builder.setContentText(messageText.getText().toString().trim());
                builder.setSmallIcon(R.drawable.bell_icon);
                builder.setDefaults(Notification.DEFAULT_SOUND);
                builder.setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.image));
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);
                managerCompat.notify(1, builder.build());

            }
        });
    }
}