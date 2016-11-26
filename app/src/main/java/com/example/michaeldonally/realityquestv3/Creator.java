package com.example.michaeldonally.realityquestv3;

/**
 * Created by Michael Donally on 11/8/2016.
 */

public class Creator extends User {
    String myName;

    public Creator(String newName) {
        //Called after name is entered, once we've figured out how to store a name on a device we will instead call it after a detectName() function
        this.myName = newName;
    }

    public void setMap(String name){

    }
}
