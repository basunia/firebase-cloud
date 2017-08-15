package com.example.mahmud.digitedu.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.text.Html;
import android.util.Log;

import com.example.mahmud.digitedu.data.DatabaseHelper;
import com.example.mahmud.digitedu.R;
import com.example.mahmud.digitedu.activities.ResultActivity;
import com.google.firebase.messaging.RemoteMessage;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;


public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService{

    private String TAG = FirebaseMessagingService.class.getSimpleName();
    Ringtone r;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        String message = remoteMessage.getData().get("message").toString().trim();
        Log.d(TAG, message);
        //Charset.forName("UTF-8").encode(message);
        String result = Html.fromHtml(message).toString();


        Log.d(TAG, result);
        storeInDB(result);
        showNotification(result);
    }

    private void storeInDB(String message) {
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        db.insertMsg(message);
        db.close();
    }

    private void showNotification(String message) {

        Intent i = new Intent(this, ResultActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setContentTitle("PTI Gaibandha")
                .setTicker("An impotant notice from your Institute")
                .setContentText(message)
                .setSmallIcon(R.drawable.ptilogo)
                .setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }

        manager.notify(0,builder.build());
    }

    /**
     * Called by the system to notify a Service that it is no longer used and is being removed.  The
     * service should clean up any resources it holds (threads, registered
     * receivers, etc) at this point.  Upon return, there will be no more calls
     * in to this Service object and it is effectively dead.  Do not call this method directly.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        //Toast.makeText(getApplicationContext(), " Escaped FirebaseMessagingService class", Toast.LENGTH_SHORT).show();
    }
}
