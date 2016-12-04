package com.example.michaeldonally.realityquestv3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class selectContentToCreateAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_create);

        TextView username = (TextView)findViewById(R.id.usernameText);

        //to get the current user
        Globals globals = ((Globals)getApplicationContext());
        User currentUser = globals.getUser();

        //Sets text box to users username
        username.setText(currentUser.getUsername());
    }

    public void onMap(View view) {
        Intent intent = new Intent(selectContentToCreateAcitivity.this, createMapActivity.class);
        startActivity(intent);
    }

    public void onEvent(View view) {
        Intent intent = new Intent(selectContentToCreateAcitivity.this, create_event.class);
        startActivity(intent);
    }
}
