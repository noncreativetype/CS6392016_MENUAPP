package com.example.fabianpatino.menuapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.telephony.SmsManager;
import android.net.Uri;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;
import android.widget.Toolbar;
import android.view.ContextMenu;

public class MainActivity extends AppCompatActivity {
    Button sendBtn;
    Button button2,button3,button4,button5,button6;

    public String txtphoneNo = "9142991173";
    String txtMessage = "hello";


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendBtn = (Button) findViewById(R.id.btnSendSMS);
        button2 = (Button) findViewById(R.id.button2);//Phone – When we click on Phone, the default Android caller app is displayed. The phone number is populated with a phone number of your choice.
        button3 = (Button) findViewById(R.id.button3);//Web – When we click on Web, the browser opens up with a URL of your choice.
        button4 = (Button) findViewById(R.id.button4);//Map – When we click on Map, the Google Maps app opens and it is centered on your birth place.
        button5 = (Button) findViewById(R.id.button5);//Share – When we click on Share, the chooser Activity opens with “Share the love” as a title.
        button6 = (Button) findViewById(R.id.button6);//New Activity – A new activity (NewACtivity.java) opens up when we click on the New Activity button. The textview in the activity will be: Hello <your first name, last name>.


        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendSMSMessage();
            }
        }
        );

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                call();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                web();
            }
            });

        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                map();
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                share();
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                newAct();
            }
        });
    }

        private void call() {
            final Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:914-299-1173"));
            startActivity(intent);
        }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_help){
            startActivity(new Intent(MainActivity.this, HelpActivity.class));

        }
        return super.onOptionsItemSelected(item);
    }
///////////////////////////////
    protected void sendSMSMessage() {
        Log.i("Send SMS", "");
        String phoneNo = txtphoneNo;
        String message = txtMessage;
        SmsManager smsManager = SmsManager.getDefault();
//            smsManager.sendTextMessage(phoneNo, null, message, null, null);
        Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
        final Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto: " + Uri.encode(phoneNo)));
        intent.putExtra("sms_body", message);
        startActivity(intent);

    }
    private void web() {

        String url = "http://www.artbrut.org.uk";
        Intent setWeb = new Intent(Intent.ACTION_VIEW);
        setWeb.setData(Uri.parse(url));
        startActivity(setWeb);
    }
    private void map() {
        String geoLocation= "geo:-2.894997,-79.0301574";
        Intent locate = new Intent(Intent.ACTION_VIEW);
        locate.setData(Uri.parse(geoLocation));
        startActivity(locate);
    }
    private void share() {
        Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
        shareIntent.setType("url");
        startActivity(Intent.createChooser(shareIntent,"Share the Love"));

    }
    private void newAct() {
       startActivity( new Intent(MainActivity.this,New_activity.class));

    }
}
