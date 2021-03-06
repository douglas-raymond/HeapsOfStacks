package com.example.michaeldonally.realityquestv3;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SelectMapActivity extends Activity {
    User currentUser;
    Map demoMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_map);

        Globals globals = ((Globals)getApplicationContext());
        currentUser = globals.getUser();

        TextView username = (TextView)findViewById(R.id.usernameText);
        username.setText(currentUser.getUsername());

        demoMap = globals.getMap();
    }

    public void onNext(View view) {
        currentUser.character.setGameMap(demoMap);

        Intent intent = new Intent(SelectMapActivity.this, TravelActivity.class);
        startActivity(intent);
    }
}
