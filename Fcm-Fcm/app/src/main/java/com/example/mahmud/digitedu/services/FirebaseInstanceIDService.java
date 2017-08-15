package com.example.mahmud.digitedu.services;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    //Constants for success and errors
    public static final String REGISTRATION_SUCCESS = "RegistrationSuccess";
    public static final String REGISTRATION_ERROR = "RegistrationError";

    @Override
    public void onTokenRefresh() {

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.w("GCMRegIntentService", "token:" + token);

        registerToken(token);

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
        //Toast.makeText(getApplicationContext(), "Uploaded token to the server successfull.", Toast.LENGTH_LONG).show();
    }

    private void registerToken(String token) {

        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("Token",token)
                .build();

        Request request = new Request.Builder()
                .url("http://pti.mahmud.vubon.com/register.php")
                .post(body)
                .build();

        try {
            client.newCall(request).execute();


        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
