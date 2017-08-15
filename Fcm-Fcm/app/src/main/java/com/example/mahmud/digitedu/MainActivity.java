package com.example.mahmud.digitedu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mahmud.digitedu.activities.ResultActivity;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().subscribeToTopic("test");
        String token = FirebaseInstanceId.getInstance().getToken();

        Log.w(TAG, "token:" + token);


        if(token == null) {
            token = "Please turn on Internet and wait few seconds to register";
            Toast.makeText(this, "Token:"+ token, Toast.LENGTH_LONG).show();
            //registerTokenToServer(token);
        }else {
            Toast.makeText(this, "Token:"+ token, Toast.LENGTH_LONG).show();
            //registerTokenToServer(token);
        }

        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ResultActivity.class ));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.about:
                TextView alert;
                alert = new TextView(this);
                alert.setText(Html.fromHtml("<big><b>Idea and Supervised by</b><br>" +
                        "    Md. Tarikul Islam<br>" +
                        "    Email: tarikuiu.eee@gmail.com<br>" +
                        "    <strong>Design and Developed by</strong><br>" +
                        "    Mahmudul Hasan Basunia<br>" +
                        "    Email: mahmud.basunia@gmail.com</big>"));


                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("About the app");
                builder.setIcon(R.drawable.ptilogo);
                //builder.setMessage(R.string.aboutMessage);
                builder.setView(alert, (int)15, 0, 0, 0);

                builder.setPositiveButton("Thanks", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.create();
                builder.show();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }


}
