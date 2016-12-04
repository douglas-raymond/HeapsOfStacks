package com.example.michaeldonally.realityquestv3;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael Donally on 11/28/2016.
 */

public class Globals extends Application {
    private User currentUser;
    String[] testString = new String[]{"Attack", "Flee", "Talk", "Catch"};
    private Event event = new Event("Test Event", "Attacked by a test!", testString, "slime");
    //Need to create a global event for testing

    Option opt1 = new Option("ATTACK", 5, -5, 5);
    Option opt2 = new Option("Flee", 0, -5, 7);
    Option opt3 = new Option("Talk To", 4,-5, 6);
    Option opt4 = new Option("Smell", 8, -6, 8);
    Option opt5 = new Option("Catch", 7, -4, 5);
    Option opt6 = new Option("Dodge", 5, -5, 2);
    Option opt7 = new Option("Defend", 3, -7, 1);
    Option opt8 = new Option("Intimidate", 7, -4, 6);

    private Option[] options = new Option[]{new Option("Attack", 5, -5, 5), new Option("Flee", 0, -5, 7), new Option("Talk", 4,-5, 6), new Option("Smell", 8, -6, 8), new Option("Catch", 7, -4, 5)};

    public User getUser() {
        return currentUser;
    }

    public void setUser(User u) {
        currentUser = u;
    }

    public Event getEvent() {return event;}

    public void setEvent(Event e) {
        event = e;
    }

    public Option[] getOptionList() {return options; }

    //Returns the options associated with the given title
    public Option getOption(String o) {
        for(int i = 0; options.length > i; i++) {
            if(options[i].getText().toString() == o) {
                return options[i];
            }
        }
        return null;
    }
    //We may need setters and getters here for attributes to have them actually update in global space
}
