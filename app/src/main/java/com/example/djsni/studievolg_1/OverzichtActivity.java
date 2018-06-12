package com.example.djsni.studievolg_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class OverzichtActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        setContentView(R.layout.activity_overzicht);
        String studentennummer = b.getString("key");
        Log.i("geert", studentennummer);
        TextView textView = (TextView) findViewById(R.id.naamTextView);
        textView.setText("Welkom, " + studentennummer);
    }

}
