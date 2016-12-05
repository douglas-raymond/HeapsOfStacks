package com.example.michaeldonally.realityquestv3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class questEnd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playerquestend);
    }

    public void onRate(View view) {
        //Sends rating to the server

        Intent intent = new Intent(questEnd.this, SelectMapActivity.class);
        startActivity(intent);
    }
}
