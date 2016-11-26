package com.example.michaeldonally.realityquestv3;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        mediaplayer = MediaPlayer.create(MainActivity.this, R.raw.realityquesttheme);
        mediaplayer.start();
    }

    //Takes us to the playCreation screen
    public void playerCreateInit(View view){
        Intent intent = new Intent(this, playerCreationActivity.class);
        mediaplayer.stop();
        startActivity(intent);
    }
}
