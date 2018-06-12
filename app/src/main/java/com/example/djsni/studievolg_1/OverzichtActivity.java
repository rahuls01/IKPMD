package com.example.djsni.studievolg_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class OverzichtActivity extends AppCompatActivity {
    BaseAdapter la;
    ArrayList<vak> vakken = new ArrayList<vak>();
    public void voegVakToe(View view) {
        Bundle b = getIntent().getExtras();
        setContentView(R.layout.activity_overzicht);
        String studentennummer = b.getString("key");
        Log.i("hond", studentennummer);

        Intent intent = new Intent(OverzichtActivity.this, ToevoegenVakActivity.class);
        Bundle c = new Bundle();
        c.putString("key", studentennummer);
        intent.putExtras(c);
        startActivity(intent);
    }
    final MyDBHandler myDBHandler = new MyDBHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        setContentView(R.layout.activity_overzicht);
        String studentennummer = b.getString("key");
        Log.i("geert", studentennummer);
        Boolean check =  myDBHandler.checkIfexists();



        if (check == false){

            TextView textViewniks = (TextView) findViewById(R.id.niksTextView);
            textViewniks.setText("Je hebt nog geen vakken geselecteerd!");

        }
        else {
            TextView textView = (TextView) findViewById(R.id.naamTextView);
            textView.setText("Welkom, " + studentennummer);

            ListView lv = (ListView)findViewById(R.id.LVSIMPLE);



            la = new ArrayAdapter<vak>(this, android.R.layout.simple_list_item_1, myDBHandler.getAll());

            lv.setAdapter(la);


        }





    }

}
