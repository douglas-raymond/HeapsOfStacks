package com.example.michaeldonally.realityquestv3;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class QuestEnd extends AppCompatActivity {
    MediaPlayer mediaplayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playerquestend);

        mediaplayer = MediaPlayer.create(this, R.raw.realityquesttheme);
        mediaplayer.start();
    }
}
