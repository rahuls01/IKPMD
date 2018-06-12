package com.example.djsni.studievolg_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ToevoegenVakActivity extends AppCompatActivity {
    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Vakken");
    BaseAdapter la;
    ArrayList<vak> vakken = new ArrayList<vak>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        setContentView(R.layout.activity_toevoegen_vak);
        final String studentennummer = b.getString("key");

        // Grab the ListView element
        ListView lv = (ListView)findViewById(R.id.LVSIMPLE);

        // make a list of elements we want to show.

        getData();

        la = new ArrayAdapter<vak>(this, android.R.layout.simple_list_item_1, vakken);
        lv.setAdapter(la);
        final MyDBHandler myDBHandler = new MyDBHandler(this);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Click ListItem Number " + position, Toast.LENGTH_SHORT)
                        .show();
                        myDBHandler.addVak(vakken.get(position), studentennummer);


            }
        });


    }

    // Read from the database
    public void getData() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    vak vak = ds.getValue(vak.class);
                    vakken.add(vak);
                }

                la.notifyDataSetChanged();

                for (vak vak : vakken) {
                    System.out.println(vak.toString());
                }




            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Failed to read value.", error.toException());
            }
        });

//        vakken.add(new vak("IKPMD TEST","6","Dingetjes",5));



    }

}