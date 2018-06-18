package com.example.djsni.studievolg_1;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Loading Screen
    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final MyDBHandler myDBHandler = new MyDBHandler(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean check = myDBHandler.checkIfexistNaam();
        // DIT MOET NOG GEFIXT WORDEN
//       bo olean check = false;
        System.out.println("testaswijhfweaifjweifwe"+ check);
        if(check == false){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
                     startActivity(homeIntent);
                    finish();
                }
            }, SPLASH_TIME_OUT);

        }else{
            TextView textView = (TextView) findViewById(R.id.naamTextView);
            String naam = myDBHandler.getNaam();
            textView.setText("Welkom terug " + naam);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent homeIntent = new Intent(MainActivity.this, OverzichtActivity.class);
                    startActivity(homeIntent);
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }



        //Loading Screen

    }
}
