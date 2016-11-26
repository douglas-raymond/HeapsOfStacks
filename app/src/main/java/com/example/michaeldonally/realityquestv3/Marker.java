package com.example.michaeldonally.realityquestv3;

import java.util.ArrayList;

/**
 * Created by Michael Donally on 10/27/2016.
 */

public class Marker {
    String title;
    Coor markerCoor;
    Marker nextMarker;
    Marker prevMarker;//Having tis here makes this a node ike a double linked list, I think this'll
    // make it easier to create the list in the proper order

    //We will structure this to have different data entry structures so we can present what they input nicely
    //and coherently
    ArrayList<Note> dungeonNotes;

    public void setTitle(String t) { this.title = t;}
    public void setCoor() { //Will use the googlemaps API to set the coordinates to the devices current coordinates, I may be able to abract this because I think it shows up somewhere else
    }

    //There will be a button that you press once you have arrived at the next marker to create that
    // creates a new marker and sets the currMarker s its preMarker, currMarker is set to it, and currMakers rev's net is set to currMarker
    public Marker(String t, Coor c){
        //The markers get set later
        this.title = t;
        this.markerCoor = c;
    }

    public void addDungeonNote(Note n){
        //Will be structured simiarily to playerbio's stats, asked for by client
        this.dungeonNotes.add(n);
    }
    //Event currEvent;

}

