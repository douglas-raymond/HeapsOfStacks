package com.example.michaeldonally.realityquestv3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class questEndLose extends AppCompatActivity {
    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playerfail);

        TextView username = (TextView)findViewById(R.id.usernameText);

        //to get the current user
        Globals globals = ((Globals)getApplicationContext());
        currentUser = globals.getUser();

        //Sets text box to users username
        username.setText(currentUser.getUsername());
    }

    public void onRate(View view) {
        //Sends rating to the server

        Intent intent = new Intent(questEndLose.this, SelectMapActivity.class);
        startActivity(intent);
    }
}
