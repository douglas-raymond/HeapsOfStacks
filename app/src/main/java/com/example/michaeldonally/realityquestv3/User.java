package com.example.michaeldonally.realityquestv3;

/**
 * Created by Michael Donally on 11/8/2016.
 */

public class User {

    String name;
    int level;//Be it player level or dungeon creator level, this may be implemented later
    //Coor myCoordinates;
    //Map currMap;

    //public Coor getCoor() {
        //This function will updates the Coor object myCoordinates using the google maps api
        //Updates it t devices current coordinates
    //    return null;
    //}

    public User() {


    }

    //public void setMap(Map selectedMap){
        //I think we should set it up from the client side such that when we're making a new map this is invoked just with an empty map
        //We will also have a series of update functions for creators only (most likely done in the c
    //    this.currMap = selectedMap;
    //}

    public void setLevel(int l) {level = l;};
    public void setName(String n) {
        name = n;
    }
}
