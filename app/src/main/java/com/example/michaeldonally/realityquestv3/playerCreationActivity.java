package com.example.michaeldonally.realityquestv3;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class playerCreationActivity extends AppCompatActivity {
    PlayerCharacter newCharacter = new PlayerCharacter();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.playercreation);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //mediaplayer.start();
    }

    public void onSubmit(View view) {


        EditText nameEdit = (EditText)findViewById(R.id.editText2);
        EditText bioEdit = (EditText)findViewById(R.id.editText4);
        newCharacter.setCName(nameEdit.getText().toString());

        Note bio = new Note("Bio", bioEdit.getText().toString());
        newCharacter.addToBio(bio);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(newCharacter.characterName).setMessage(newCharacter.playerBio.noteToString());

        builder.setPositiveButton("Confirm?", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(getApplicationContext(), TravelActivity.class);

                GameData g = GameData.getInstance();
                g.setPlayer(newCharacter);
                //intent.putExtra(newCharacter);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
