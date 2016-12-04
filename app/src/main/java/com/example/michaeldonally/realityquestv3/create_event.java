package com.example.michaeldonally.realityquestv3;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class create_event extends AppCompatActivity {
    User user;

    public void onOptionCreate(View view) {
        Intent intent = new Intent(create_event.this, createOptionActivity.class);
        startActivity(intent);
    }

    public void onSubmit(View view) {
        Spinner images = (Spinner)findViewById(R.id.images);
        Spinner opt1 = (Spinner)findViewById(R.id.spinner1);
        Spinner opt2 = (Spinner)findViewById(R.id.spinner2);
        Spinner opt3 = (Spinner)findViewById(R.id.spinner3);
        Spinner opt4 = (Spinner)findViewById(R.id.spinner4);
        Spinner level = (Spinner)findViewById(R.id.spinner5);
        EditText name = (EditText)findViewById(R.id.eventName);
        EditText flav = (EditText)findViewById(R.id.flav);

        String[] optArray = new String[]{opt1.getSelectedItem().toString(), opt2.getSelectedItem().toString(), opt3.getSelectedItem().toString(), opt4.getSelectedItem().toString()};

        Event newEvent = new Event(name.getText().toString(), flav.getText().toString(), optArray, images.getSelectedItem().toString());

        //Sends event to the server
        user.createEvent(newEvent);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Your new Event has been saved on the server!");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(create_event.this, selectContentToCreateAcitivity.class);
                startActivity(intent);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onCreateOption(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Set Option Name:");


        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_event);

        TextView username = (TextView)findViewById(R.id.usernameText);

        //to get the current user
        Globals globals = ((Globals)getApplicationContext());
        user = globals.getUser();

        //Sets text box to users username
        username.setText(user.getUsername());

        Spinner images = (Spinner) findViewById(R.id.images);

        String[] imageNames = new String[]{"Slime", "Goblin", "Bear", "Hoodlum", "Old Man", "Orc"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, imageNames);
        images.setAdapter(adapter);

        //Here we get our list of options from global data and use them to populate a list of their titles
        Option[] options = globals.getOptionList();;
        String[] optionNames = new String[options.length];

        for(int i = 0; options.length > i; i++){
            optionNames[i] = options[i].text;
        }

        Spinner opt1 = (Spinner)findViewById(R.id.spinner1);
        Spinner opt2 = (Spinner)findViewById(R.id.spinner2);
        Spinner opt3 = (Spinner)findViewById(R.id.spinner3);
        Spinner opt4 = (Spinner)findViewById(R.id.spinner4);

        ArrayAdapter<String> optAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, optionNames);
        opt1.setAdapter(optAdapter);
        opt2.setAdapter(optAdapter);
        opt3.setAdapter(optAdapter);
        opt4.setAdapter(optAdapter);

        String[] levels = new String[]{"1", "2", "3"};
        Spinner level = (Spinner)findViewById(R.id.spinner5);
        ArrayAdapter<String> levelAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, levels);
        level.setAdapter(levelAdapter);
    }
}
