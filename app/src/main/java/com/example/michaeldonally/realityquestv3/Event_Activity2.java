package com.example.michaeldonally.realityquestv3;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Event_Activity2 extends AppCompatActivity {
    MediaPlayer mediaplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_2);
        mediaplayer = MediaPlayer.create(Event_Activity2.this, R.raw.fightmusic2);
        mediaplayer.start();

        GameData g = GameData.getInstance();

        TextView playername = (TextView) findViewById(R.id.textView3);
        playername.setText(g.getPlayer().characterName);
    }

    public void onSubmit(View view){
        Intent intent = new Intent(this, Event_Activity3.class);
        mediaplayer.stop();
        startActivity(intent);
    }
}