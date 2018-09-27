package com.example.lorca.roomshareapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.content.DialogInterface;
import android.widget.Button;
import android.widget.ListView;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

import java.util.Objects;

public class Roomies extends AppCompatActivity {
    private FirebaseAuth auth;

    FirebaseDatabase database;
    DatabaseReference ref;

    ArrayList<String> list;

    ArrayAdapter<String> adapter;

    ListView listView;

    Button connect;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roomies);
        auth = FirebaseAuth.getInstance();

        user = new User();

        connect = (Button) findViewById(R.id.connectButton);

        listView = (ListView) findViewById(R.id.display_listview);

        database = FirebaseDatabase.getInstance();

        ref = database.getReference("users");

        list = new ArrayList<>();

        adapter = new ArrayAdapter<String>(this, R.layout.list_row, R.id.userInfo, list);

        /*connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String shareBody = "Your body here";
                String shareSub = "YOur subject here";
                intent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                intent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(intent, "Share Using..."));
            }
        });*/

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    user = ds.getValue(User.class);
                    list.add(user.getName().toString() + " : " +user.getSex().toString() + " : " +user.getPhone().toString());
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}