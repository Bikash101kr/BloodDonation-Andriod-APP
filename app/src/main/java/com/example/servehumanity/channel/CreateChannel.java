package com.example.servehumanity.channel;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class CreateChannel {
  Context context;
  public final static String CHANNEL_1 = "channel1";
  public final static String CHANNEL_2 = "channel2";

  public CreateChannel(Context context) {
    this.context = context;
  }

  public void createChannel() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      NotificationChannel channel1 = new NotificationChannel(CHANNEL_1, "channel1", NotificationManager.IMPORTANCE_DEFAULT);
      channel1.setDescription("This is Channel1");

      NotificationChannel channel2 = new NotificationChannel(CHANNEL_2, "channel2", NotificationManager.IMPORTANCE_HIGH);
      channel1.setDescription("This is Channel2");

      NotificationManager manager = context.getSystemService(NotificationManager.class);

      manager.createNotificationChannel(channel1);
      manager.createNotificationChannel(channel2);

    }
  }

}