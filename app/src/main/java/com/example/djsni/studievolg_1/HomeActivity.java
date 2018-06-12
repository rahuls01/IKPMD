package com.example.djsni.studievolg_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HomeActivity extends AppCompatActivity {
    final MyDBHandler myDBHandler = new MyDBHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    public void onClick(View view) {
        EditText nameEditText = (EditText) findViewById(R.id.nameEditText);
        Log.i("Info", nameEditText.getText().toString());
        String naam = nameEditText.getText().toString();
        Intent intent = new Intent(HomeActivity.this , OverzichtActivity.class);

        myDBHandler.addNaam(naam);
        Bundle b = new Bundle();
        b.putString("key", naam);
        intent.putExtras(b);


        startActivity(intent);

        }





}
