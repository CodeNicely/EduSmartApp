package com.codenicely.edusmart.helper;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.codenicely.edusmart.R;
import com.codenicely.edusmart.message.view.MessageActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;



public class MyFirebaseService extends FirebaseMessagingService {
    int i;

    private static final String TAG = "MyFirebaseMsgService";
    public static final String INTENT_FILTER = "INTENT_FILTER";

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d("Response", "From: " + remoteMessage.getData());

        Intent intent = new Intent(INTENT_FILTER);
        sendBroadcast(intent);
        String jsonStr = remoteMessage.getData().toString();
        try {
            JSONObject jsonRootObject = new JSONObject(jsonStr);
//             i=Integer.parseInt(jsonRootObject.optString("intent_id"));
            if (remoteMessage.getData() != null) {
                Log.d("Message", String.valueOf(remoteMessage.getData()));
                if (remoteMessage.getData().containsKey("type")) {
                    i = Integer.parseInt(remoteMessage.getData().get("type"));



                    sendNotification("" + remoteMessage.getData().get("body"), "" +
                            remoteMessage.getData().get("title"));

                } else {
                    i = 9090;
                    sendNotification("" + remoteMessage.getData().get("body"), "" +
                            remoteMessage.getData().get("title"));

                }

            } else {
                Log.d("Message", "Failed");

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
    // [END receive_message]

    private void sendNotification(String messageBody, String title) {
        Intent intent;
        int flag = 0;
        boolean notification_display = true;
        if (i == 1) {
            intent = new Intent(this, MessageActivity.class);


        } else {
            flag = 1;
            intent = null;
        }

        /*
        else  if(i==2) {
        }
        else if(i==3) {
        }
        else if(i==4) {
        }
        else if(i==5) {
        }
        else if(i==6) {
        }
        else if(i==7) {
        }
       else if(i==13)
       {
           final String appPackageName = getPackageName();
           Log.d("Response",""+getPackageName());
           try {
              intent= new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName));
           }
           catch (android.content.ActivityNotFoundException anfe) {
               intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName));
           }
       }
       else if(i==113)
       {
           final String uri_app=messageBody;
           notification_display=false;
           try {
               intent= new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" +uri_app));
           }
           catch (android.content.ActivityNotFoundException anfe) {
               intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + uri_app));
           }
       }

        else {
             flag=1;
           intent=null;
        }

        */

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);

        if (notification_display) {
//            notificationBuilder.setSound(Uri.parse())
            notificationBuilder.setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.notification_sound));
            notificationBuilder.setSmallIcon(R.drawable.code_nicely_logo_small_colored)
                    .setContentTitle(title)
                    .setContentText(messageBody)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(messageBody))
                    .setAutoCancel(true);
            notificationBuilder.setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);
            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, notificationBuilder.build());
        }
        if (flag == 0) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                    PendingIntent.FLAG_ONE_SHOT);
            notificationBuilder.setContentIntent(pendingIntent);
            startActivity(intent);

        }



    }
}