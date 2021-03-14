package applecare.com.applecare;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;


import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import applecare.com.applecare.Activity.Splash;
import applecare.com.applecare.Utils.Utilities;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "firebaseService";
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        sendFirebaseTokenToServer(token);
    }


    private void sendFirebaseTokenToServer(String token) {
        Log.d(TAG, "sendFirebaseTokenToServer: "+token);
    }
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG, "Refreshed token: " + "notification");
        //put code here to navigate based on click_action
        if (remoteMessage.getData().containsKey("af-uinstall-tracking")) {
            return;
        } else {
            // handleNotification(remoteMessage);
            if (remoteMessage.getData().size() > 0) {
                sendNotification(remoteMessage.getData().get("title"), remoteMessage.getData().get("body"));

            }
        }




        //  Toast.makeText(this, "Notification", Toast.LENGTH_SHORT).show();
    }

    private void sendNotification(String title, String message) {
        Intent intent = new Intent(getApplicationContext(), Splash.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("pushnotification", "yes");
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        String CHANNEL_ID = "my_channel_01";// The id of the channel.

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = null;
        try {
            notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(Utilities.getDrawableId(this, "logo"))
                    .setContentTitle(title)
                    .setColor(getResources().getColor(R.color.colorPrimary))
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setChannelId(CHANNEL_ID)
                    .setSound(defaultSoundUri)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                    .setContentIntent(pendingIntent);
        } catch (Exception e) {
            e.printStackTrace();

        }
        try {
            if (notificationBuilder != null) {
                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    CharSequence name = getString(R.string.appname);
                    int importance = NotificationManager.IMPORTANCE_HIGH;
                    NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
                    notificationManager.createNotificationChannel(mChannel);
                }
                notificationManager.notify(1 /* ID of notification */, notificationBuilder.build());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }





}
