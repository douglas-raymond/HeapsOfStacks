package com.example.michaeldonally.realityquestv3;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class loginActivity extends Activity {
    public void onLogin(View view) {
        EditText user = (EditText) findViewById(R.id.username);
        EditText pass = (EditText) findViewById(R.id.password);

        String username = user.getText().toString();
        String password = pass.getText().toString();

        //Creates a user before updating the global one, must check validity before this, work will Tyler
        User currentUser = new User(username, password);

       // User  tempUser = currentUser.getUserData(username, password);

        //Get the globals object
        Globals globals = ((Globals)getApplicationContext());

        //Updates the global user variable
        globals.setUser(currentUser);

        Intent intent = new Intent(loginActivity.this, gameTypeSelectActivity.class);
        startActivity(intent);
    }

    public void onSignUp(View view){
        EditText user = (EditText) findViewById(R.id.username);
        EditText pass = (EditText) findViewById(R.id.password);

        final String username = user.getText().toString();
        final String password = pass.getText().toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure you want to create a new user?").setMessage("Username: " + username + "\n Password: " + password);

        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                User newUser = new User(username, password);

                //CALL TO ADD TO SERVER GOES HERE

                Globals globals = ((Globals)getApplicationContext());

                //Updates the global user variable
                globals.setUser(newUser);

                Intent intent = new Intent(loginActivity.this, gameTypeSelectActivity.class);
                startActivity(intent);

            }
        });

        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }
}
