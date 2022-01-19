package com.example.danielmachadojustodasilva_comp304sec003_test02;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class StockBR extends BroadcastReceiver {

    public static final String NEW_COMPANY_ACTION
            = "EXTRA_COMPANY_NAME";
    public static final String EXTRA_COMPANY_NAME
            = "EXTRA_COMPANY_NAME";
    public static final String EXTRA_STOCK_QUOTE
            = "EXTRA_COMPANY_QUOTE";



    private static final int NOTIFICATION_ID = 0;
    private static final String MESSAGES_CHANNEL = "messages";


    @Override
    public void onReceive(Context context, Intent intent) {

        String type="true";
        String stockName = intent.getStringExtra(EXTRA_COMPANY_NAME);
        String stockQuote=intent.getStringExtra(EXTRA_STOCK_QUOTE);

        createMessagesNotificationChannel(context);
        if (type.equals("true")) {

            NotificationManagerCompat notificationManagerCompat =
                    NotificationManagerCompat.from(context);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(
                    context, MESSAGES_CHANNEL);

            CharSequence notificationTitle = context.getString(R.string.notification_title);

            builder.setSmallIcon(R.drawable.ic_launcher_background);
            builder.setContentTitle(notificationTitle);
            builder.setColor(Color.GREEN);
            StringBuilder msg=new StringBuilder();
            msg.append(stockName);
            msg.append("\n");
            msg.append(stockQuote);
            builder.setContentText(msg);
            Notification notification = builder.build();
            notificationManagerCompat.notify(NOTIFICATION_ID, notification);
        }
    }

    public void createMessagesNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = context.getString(R.string.notification_title);

            NotificationChannel channel = new NotificationChannel(
                    MESSAGES_CHANNEL,
                    name,
                    NotificationManager.IMPORTANCE_HIGH);

            channel.setLightColor(Color.BLUE);
            NotificationManager notificationManager =
                    context.getSystemService(NotificationManager.class);

            notificationManager.createNotificationChannel(channel);
        }
    }

}
