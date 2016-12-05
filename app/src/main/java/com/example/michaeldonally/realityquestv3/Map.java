package com.example.michaeldonally.realityquestv3;

import java.util.List;

/**
 * Created by Michael Donally on 11/8/2016.
 */

public class Map {
    //Every map has exactly 10 markers
    List<Marker> markers;

    //Every map has a title
    String title;

    //Every map has a rating between 1 and 5 that is an average of all its ratings
    double rating;

    //Keeps track of the person that created it
    String creator;

    //Tracks the number of times that it has been played
    int plays;

    //Tracks the number of markers that have been completed so we can tell when the map is done
    int completedMarkers;

    public Map(String n, List<Marker> m) {
        this.title = n;
        this.markers = m;

        //The rating and the plays will only ever be chaged on the server, here we set the defaults
        this.rating = 2.5;
        this.plays = 0;
        this.completedMarkers = 0;
    }

    public Map() {

    }

    public void setCreator(String name) {
        this.creator = name;
    }

    public Marker getFirstMarker() {
        return markers.get(0);
    }

    //Will be called whenever an event is completed to check if the game has finished
    public boolean gameFinished() {
        completedMarkers++;
        if(completedMarkers == 10){
            return true;
        } else {
            return false;
        }
    }
}