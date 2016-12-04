package com.example.michaeldonally.realityquestv3;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class playerCreationActivity extends AppCompatActivity {
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playercreation);

        Globals globals = ((Globals)getApplicationContext());
        user = globals.getUser();
    }

    public void onSubmit(View view) {
        final EditText nameEdit = (EditText)findViewById(R.id.editText2);
        final EditText bioEdit = (EditText)findViewById(R.id.editText4);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(nameEdit.getText().toString()).setMessage(bioEdit.getText().toString());
        builder.setPositiveButton("Confirm?", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(playerCreationActivity.this, SelectMapActivity.class);

                PlayerCharacter character = new PlayerCharacter(nameEdit.getText().toString(), bioEdit.getText().toString());

                user.setCharacter(character);

                //Adds the character tto the server too
               // user.updateUserList(user);

                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
