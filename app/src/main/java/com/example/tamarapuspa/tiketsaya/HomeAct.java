package com.example.tamarapuspa.tiketsaya;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.florent37.shapeofview.shapes.CircleView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HomeAct extends AppCompatActivity {

    LinearLayout btn_ticket_pisa, btn_ticket_tori
            , btn_ticket_pagoda, btn_ticket_candi
            , btn_ticket_sphinx, btn_ticket_monas;
    CircleView btn_to_profile;

    ImageView photo_home_user;
    TextView nama_lengkap, user_balance, bio;

    DatabaseReference reference;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getUsernameLocal();

        btn_ticket_pisa = findViewById(R.id.btn_ticket_pisa);
        btn_ticket_tori = findViewById(R.id.btn_ticket_tori);
        btn_ticket_pagoda = findViewById(R.id.btn_ticket_pagoda);
        btn_ticket_sphinx = findViewById(R.id.btn_ticket_sphinx);
        btn_ticket_candi = findViewById(R.id.btn_ticket_candi);
        btn_ticket_monas = findViewById(R.id.btn_ticket_monas);

        btn_to_profile = findViewById(R.id.btn_to_profile);

        nama_lengkap = findViewById(R.id.nama_lengkap);
        user_balance = findViewById(R.id.user_balance);
        bio = findViewById(R.id.bio);
        photo_home_user = findViewById(R.id.photo_home_user);

        reference = FirebaseDatabase.getInstance().getReference().child("Users")
                .child(username_key_new);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nama_lengkap.setText(dataSnapshot.child("nama_lengkap").getValue().toString());
                bio.setText(dataSnapshot.child("bio").getValue().toString());
                user_balance.setText("US$ " + dataSnapshot.child("user_balance").getValue().toString());
                Picasso.with(HomeAct.this)
                        .load(dataSnapshot.child("url_photo_profile")
                                .getValue().toString()).centerCrop().fit().into(photo_home_user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_to_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoprofile = new Intent(HomeAct.this, MyProfileAct.class);
                startActivity(gotoprofile);
            }
        });

        btn_ticket_pisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotopisatiket = new Intent(HomeAct.this, TicketDetailAct.class);
                //meletakkan data pada intent
                gotopisatiket.putExtra("jenis_tiket", "Pisa");
                startActivity(gotopisatiket);
            }
        });

        btn_ticket_tori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotopisatiket = new Intent(HomeAct.this, TicketDetailAct.class);
                gotopisatiket.putExtra("jenis_tiket", "Tori");
                startActivity(gotopisatiket);
            }
        });

        btn_ticket_pagoda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotopisatiket = new Intent(HomeAct.this, TicketDetailAct.class);
                gotopisatiket.putExtra("jenis_tiket", "Pagoda");
                startActivity(gotopisatiket);
            }
        });

        btn_ticket_candi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotopisatiket = new Intent(HomeAct.this, TicketDetailAct.class);
                gotopisatiket.putExtra("jenis_tiket", "Candi");
                startActivity(gotopisatiket);
            }
        });

        btn_ticket_sphinx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotopisatiket = new Intent(HomeAct.this, TicketDetailAct.class);
                gotopisatiket.putExtra("jenis_tiket", "Sphinx");
                startActivity(gotopisatiket);
            }
        });

        btn_ticket_monas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotopisatiket = new Intent(HomeAct.this, TicketDetailAct.class);
                gotopisatiket.putExtra("jenis_tiket", "Monas");
                startActivity(gotopisatiket);
            }
        });
    }

    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}
