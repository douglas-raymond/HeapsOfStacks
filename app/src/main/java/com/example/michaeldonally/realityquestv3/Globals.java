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
    private Event event = new Event("Slime Monster", "Attacked by a slimet!", testString, "slime");
    private Event event1 = new Event("Goblin Monster", "Attacked by a Goblin!", testString, "goblin");
    //Need to create a global event for testing

    List<Marker> markers = new ArrayList<>();

    Option opt1 = new Option("ATTACK", 5, -5, 5);
    Option opt2 = new Option("Flee", 0, -5, 7);
    Option opt3 = new Option("Talk To", 4,-5, 6);
    Option opt4 = new Option("Smell", 8, -6, 8);
    Option opt5 = new Option("Catch", 7, -4, 5);
    Option opt6 = new Option("Dodge", 5, -5, 2);
    Option opt7 = new Option("Defend", 3, -7, 1);
    Option opt8 = new Option("Intimidate", 7, -4, 6);

    Marker m1 = new Marker("one", new Coor(45.371317, -75.701476));
    Marker m2 = new Marker("two", new Coor(45.369876, -75.700508));
    Marker m3 = new Marker("three", new Coor(45.370288, -75.700508));
    Marker m4 = new Marker("four", new Coor(45.371263, -75.69988));
    Marker m5 = new Marker("five", new Coor(45.372603, -75.700642));
    Marker m6 = new Marker("six", new Coor(45.374155, -75.701676));
    Marker m7 = new Marker("seven", new Coor(45.376218, -75.701719));
    Marker m8 = new Marker("eight", new Coor(45.378342, -75.701591));
    Marker m9 = new Marker("nine", new Coor(45.379012, -75.701361));
    Marker m10 = new Marker("ten", new Coor(45.380507, -75.701361));

    public List<Marker> initializeMarkerList() {
        markers.add(m1);
        markers.add(m2);
        markers.add(m3);
        markers.add(m4);
        markers.add(m5);
        markers.add(m6);
        markers.add(m7);
        markers.add(m8);
        markers.add(m9);
        markers.add(m10);

        return markers;
    }

    //Our global map
    private Map testMap = new Map("Demo Map", initializeMarkerList());

    public Map getMap() {
        return this.testMap;
    }

    private Option[] options = new Option[]{new Option("Attack", 5, -5, 5), new Option("Flee", 0, -5, 7), new Option("Talk", 4,-5, 6), new Option("Smell", 8, -6, 8), new Option("Catch", 7, -4, 5),new Option("Dodge", 5, -5, 2),new Option("Defend", 3, -7, 1), new Option("Intimidate", 7, -4, 6)};

    public User getUser() {
        return currentUser;
    }

    public void setUser(User u) {
        currentUser = u;
    }

    public Event getEvent() {
        double chance = Math.random() * 10;

        if(chance > 5) {
            return event;
        } else {
            return event1;
        }
    }

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
