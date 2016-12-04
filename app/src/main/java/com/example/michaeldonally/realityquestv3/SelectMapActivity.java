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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_map);

        Globals globals = ((Globals)getApplicationContext());
        User currentUser = globals.getUser();

        //TextView username = (TextView)findViewById(R.id.username);
       //// username.setText(currentUser.getUsername());

        //ListView list = (ListView)findViewById(R.id.list);

        //TextView test = new TextView(this);
        //test.setText("POOOOOOOOOOOOOo");

        //list.addView(test);
    }

    public void onNext(View view) {
        Intent intent = new Intent(SelectMapActivity.this, eventActivity.class);
        startActivity(intent);
    }
}
