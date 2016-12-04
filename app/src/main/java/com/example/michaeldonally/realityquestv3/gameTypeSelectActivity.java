package com.example.michaeldonally.realityquestv3;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class gameTypeSelectActivity extends Activity {
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_create_selection);

        TextView username = (TextView)findViewById(R.id.usernameText);

        //to get the current user
        Globals globals = ((Globals)getApplicationContext());
        user = globals.getUser();

        //Sets text box to users username
        username.setText(user.getUsername());
    }

    public void onPlay(View view) {
        Globals globals = ((Globals)getApplicationContext());
        User currentUser = globals.getUser();

        if(currentUser.getCharacter() == null) {
            Intent intent = new Intent(gameTypeSelectActivity.this, playerCreationActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(gameTypeSelectActivity.this, SelectMapActivity.class);
            startActivity(intent);
        }
    }

    public void onCreate(View view) {
        Intent intent = new Intent(gameTypeSelectActivity.this, selectContentToCreateAcitivity.class);
        startActivity(intent);
    }
}
