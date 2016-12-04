package com.example.michaeldonally.realityquestv3;

import java.util.ArrayList;

/**
 * Created by Michael Donally on 10/27/2016.
 */

public class Marker {
    String name;
    Coor markerLoc;
    Event newEvent;

    public void setName(String n) {
        this.name = n;
    }

    public void setCoor(Coor c) {
        this.markerLoc = c;
    }

    public Marker(String n, Coor c){
        this.name = n;
        this.markerLoc = c;
        //The object is created first and then things are added to it
    }

    public void setEvent(Event e) {
        this.newEvent = e;
    }
}

