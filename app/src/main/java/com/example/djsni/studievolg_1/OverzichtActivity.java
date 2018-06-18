package com.example.djsni.studievolg_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OverzichtActivity extends AppCompatActivity {
    BaseAdapter la;
    ArrayList<vak> vakken = new ArrayList<vak>();
    public void voegVakToe(View view) {
        Bundle b = getIntent().getExtras();
        setContentView(R.layout.activity_overzicht);


        String studentennummer = myDBHandler.getNaam();
        Intent intent = new Intent(OverzichtActivity.this, ToevoegenVakActivity.class);
        Bundle c = new Bundle();


        intent.putExtras(c);
        startActivity(intent);
    }

    public void onClick(View view) {
        Intent intent = new Intent(OverzichtActivity.this , MainActivity.class);

        myDBHandler.deleteAll();

        startActivity(intent);

    }
    final MyDBHandler myDBHandler = new MyDBHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        setContentView(R.layout.activity_overzicht);
        vakken = myDBHandler.getAll();

        Button graphButton = (Button) findViewById(R.id.graph_button);
        graphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("graph", "graph button zetten");
                startActivity(new Intent(OverzichtActivity.this, PieChartActivity.class));
            }
        });




        String studentennummer = myDBHandler.getNaam();
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
            final MyDBHandler myDBHandler = new MyDBHandler(this);


            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Toast.makeText(getApplicationContext(),
                            "Click ListItem Number " + position, Toast.LENGTH_SHORT)
                            .show();
                    // studiepunt
                    int studiepunt = Integer.parseInt(vakken.get(position).Studiepunten);
                    String modulecode = vakken.get(position).Module;
                    Intent intent = new Intent(OverzichtActivity.this, PieChartActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("Studiepunt",studiepunt);


                    myDBHandler.deleteEC(modulecode);
                    myDBHandler.addEC(studiepunt);
                    startActivity(intent);

                }
            });
            lv.setAdapter(la);
        }

    }


}
