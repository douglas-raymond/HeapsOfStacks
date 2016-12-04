package com.example.michaeldonally.realityquestv3;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class eventActivity extends AppCompatActivity {
    User user;
    Event currentEvent;
    Option option1;
    Option option2;
    Option option3;
    Option option4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Globals globals = ((Globals)getApplicationContext());
        user = globals.getUser();

        TextView username = (TextView)findViewById(R.id.usernameText);
        username.setText(user.getUsername());

        //Gets the event globally
        currentEvent = globals.getEvent();

        //Sets the name
        TextView eventName = (TextView)findViewById(R.id.eventName);
        eventName.setText(currentEvent.getName());

        //Sets the image
        ImageView image = (ImageView)findViewById(R.id.image);
        if(currentEvent.getImageLoc() == "slime") {
            image.setImageResource(R.drawable.slime);
        } else if (currentEvent.getImageLoc() == "goblin"){
            image.setImageResource(R.drawable.goblin);
        }

        //Sets flavour text
        TextView flav = (TextView)findViewById(R.id.flav);
        flav.setText(currentEvent.getMessage());

        //Gets the name of the option from the event and gets the option object from globals
        option1 = globals.getOption(currentEvent.getOption(0));
        option2 = globals.getOption(currentEvent.getOption(1));
        option3 = globals.getOption(currentEvent.getOption(2));
        option4 = globals.getOption(currentEvent.getOption(3));

        Button firstOption = (Button)findViewById(R.id.opt1);
        firstOption.setText(option1.getText());

        Button secondOption = (Button)findViewById(R.id.opt2);
        secondOption.setText(option2.getText());

        Button thirdOption = (Button)findViewById(R.id.opt3);
        thirdOption.setText(option3.getText());

        Button fourthOption = (Button)findViewById(R.id.opt4);
        fourthOption.setText(option4.getText());
    }

    public int determineOutcome(Option o){
        double result = Math.random() * 10;
        if(result < o.getChance()){
            return 1;
        } else {
            return 2;
        }
    }

    public void onOption1(View view) {
        int result = determineOutcome(option1);

        if(result == 1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("You were successful!").setMessage("You now gain " + option1.reward + " levels.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //Level the player by the specified amount and increments the number of completed events
                    user.getCharacter().setLevel(option1.getReward());
                    user.getCharacter().incrementCompletedEvents();

                    if(user.getCharacter().getCompletedEvents() >= 10){
                        //User has completed 10 events and so goes to the victory screen
                        Intent intent = new Intent(eventActivity.this, questEnd.class);
                    } else {
                        //Havent completed ten yet return to the travel screen
                        Intent intent = new Intent(eventActivity.this, TravelActivity.class);
                        startActivity(intent);
                    }


                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            //Fail
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("You failed!").setMessage("You now lose " + option1.punishment + " hitpoints.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //Lower the players health by specified amount of points
                    user.getCharacter().setHitPoints(option1.punishment);
                    user.getCharacter().incrementCompletedEvents();

                    if(user.getCharacter().getCompletedEvents() >= 10){
                        //User has completed 10 events and so goes to the victory screen
                        Intent intent = new Intent(eventActivity.this, questEnd.class);
                    } else {
                        //Havent completed ten yet return to the travel screen
                        Intent intent = new Intent(eventActivity.this, TravelActivity.class);
                        startActivity(intent);
                    }
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public void onOption2(View view) {
        int result = determineOutcome(option2);

        if(result == 1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("You were successful!").setMessage("You now gain " + option2.reward + " levels.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //Level the player by the specified amount and increments the number of completed events
                    user.getCharacter().setLevel(option2.getReward());
                    user.getCharacter().incrementCompletedEvents();

                    if(user.getCharacter().getCompletedEvents() >= 10){
                        //User has completed 10 events and so goes to the victory screen
                        Intent intent = new Intent(eventActivity.this, questEnd.class);
                    } else {
                        //Havent completed ten yet return to the travel screen
                        Intent intent = new Intent(eventActivity.this, TravelActivity.class);
                        startActivity(intent);
                    }


                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            //Fail
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("You failed!").setMessage("You now lose " + option2.punishment + " hitpoints.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //Lower the players health by specified amount of points
                    user.getCharacter().setHitPoints(option2.punishment);
                    user.getCharacter().incrementCompletedEvents();

                    if(user.getCharacter().getCompletedEvents() >= 10){
                        //User has completed 10 events and so goes to the victory screen
                        Intent intent = new Intent(eventActivity.this, questEnd.class);
                    } else {
                        //Havent completed ten yet return to the travel screen
                        Intent intent = new Intent(eventActivity.this, TravelActivity.class);
                        startActivity(intent);
                    }
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public void onOption3(View view) {
        int result = determineOutcome(option3);

        if(result == 1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("You were successful!").setMessage("You now gain " + option3.reward + " levels.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //Level the player by the specified amount and increments the number of completed events
                    user.getCharacter().setLevel(option3.getReward());
                    user.getCharacter().incrementCompletedEvents();

                    if(user.getCharacter().getCompletedEvents() >= 10){
                        //User has completed 10 events and so goes to the victory screen
                        Intent intent = new Intent(eventActivity.this, questEnd.class);
                    } else {
                        //Havent completed ten yet return to the travel screen
                        Intent intent = new Intent(eventActivity.this, TravelActivity.class);
                        startActivity(intent);
                    }


                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            //Fail
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("You failed!").setMessage("You now lose " + option3.punishment + " hitpoints.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //Lower the players health by specified amount of points
                    user.getCharacter().setHitPoints(option3.punishment);
                    user.getCharacter().incrementCompletedEvents();

                    if(user.getCharacter().getCompletedEvents() >= 10){
                        //User has completed 10 events and so goes to the victory screen
                        Intent intent = new Intent(eventActivity.this, questEnd.class);
                    } else {
                        //Havent completed ten yet return to the travel screen
                        Intent intent = new Intent(eventActivity.this, TravelActivity.class);
                        startActivity(intent);
                    }
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public void onOption4(View view) {
        int result = determineOutcome(option1);

        if(result == 1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("You were successful!").setMessage("You now gain " + option4.reward + " levels.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //Level the player by the specified amount and increments the number of completed events
                    user.getCharacter().setLevel(option4.getReward());
                    user.getCharacter().incrementCompletedEvents();

                    if(user.getCharacter().getCompletedEvents() >= 10){
                        //User has completed 10 events and so goes to the victory screen
                        Intent intent = new Intent(eventActivity.this, questEnd.class);
                    } else {
                        //Havent completed ten yet return to the travel screen
                        Intent intent = new Intent(eventActivity.this, TravelActivity.class);
                        startActivity(intent);
                    }


                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            //Fail
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("You failed!").setMessage("You now lose " + option4.punishment + " hitpoints.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //Lower the players health by specified amount of points
                    user.getCharacter().setHitPoints(option4.punishment);
                    user.getCharacter().incrementCompletedEvents();

                    if(user.getCharacter().getCompletedEvents() >= 10){
                        //User has completed 10 events and so goes to the victory screen
                        Intent intent = new Intent(eventActivity.this, questEnd.class);
                    } else {
                        //Havent completed ten yet return to the travel screen
                        Intent intent = new Intent(eventActivity.this, TravelActivity.class);
                        startActivity(intent);
                    }
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}
