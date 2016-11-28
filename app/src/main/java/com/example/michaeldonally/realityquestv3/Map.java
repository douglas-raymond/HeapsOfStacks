package com.example.michaeldonally.realityquestv3;

import android.location.Address;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by Michael Donally on 11/8/2016.
 */

public class Map {
    //Since every subsequent marker knows its next marker, the map only needs to know the first to set players on the right direction
    List<LatLng> coorList;
    Marker firstMarker;
    String title;
    int rating;
    User user;
    int plays;

    //We will have a full map view not showing your location and one that is just you to the next marker
    boolean viewType;

    public Map(String n) {
        //You only need a title to create a map the rest will be carried out in user prompts after title is entered
        this.title = n;
    }

    public void displayMap() {
        //Displays the current map (set or markers) scaled to fit together like a map
        //Idk how we're gonna do this yet
        //Displays diferently based on map type
    }

    public void updateMap() {
        //Will update based on user cordinates
    }

    public void updateList(LatLng latLng) {
        coorList.add(latLng);
    }

    public List<LatLng> getcoorList(){return this.coorList;}

    public void delMarker(LatLng latLng) {coorList.remove(latLng);}

    public void setMarker(Marker m){
        this.firstMarker = m;
    }

    public void setTitle(String t) {
        this.title = t;
    }


}