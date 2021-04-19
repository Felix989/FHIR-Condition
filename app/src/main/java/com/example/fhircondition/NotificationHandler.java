package com.example.fhircondition;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationHandler {
    private  static final String CHANNELID = "chid";

    private NotificationManager myManager;
    private Context myContext;
    private final int NotificationID = 0;

    public NotificationHandler(Context context){
        this.myContext = context;
        this.myManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        createChannel();
    }

    private void createChannel(){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O){
            return;
        }

        NotificationChannel channel = new NotificationChannel(
                CHANNELID,
                "FHIR - Condition",
                NotificationManager.IMPORTANCE_DEFAULT
        );
        channel.enableLights(true);
        channel.enableVibration(true);
        channel.setLightColor(android.R.color.holo_green_light);
        channel.setDescription("Notifies");
        this.myManager.createNotificationChannel(channel);
    }

    public void send (String message){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(myContext, CHANNELID)
                .setContentTitle("FHIR - Condition")
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_visibility_black_24dp);

        this.myManager.notify(NotificationID, builder.build());

    }

}
