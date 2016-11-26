package com.example.michaeldonally.realityquestv3;

import java.util.*;

/**
 * Created by Michael Donally on 10/27/2016.
 */
public class PlayerCharacter extends User {
    String characterName;
    float disToNextMarker;
    Note playerBio;
    Marker currMarker;  //Marker being headed for, gets updated when event at it is done
    int healthPoints;

    public void calcDist() {
        //This is where we will calculat how far it is to the next marker based on myCoordinates and currMaker
    }

    public void addToBio(Note newNote){
        //On the client side we will have functions that ask the user specific questions to receive formatted strings that are like stats
        this.playerBio = newNote;
    }

    public void updateInventory() {
        //A fucntion to add a note to the inventory in the playerbio in a structured way
        //Will need to manipulate the string, shouldnt be too bad
    }

    public void setCName(String newName) {
        this.characterName = newName;
    }
    public String getCName() {return this.characterName;}

    public PlayerCharacter() {
        //All other attributes dont get assigned right away
        //this.characterName = cName;
    }

    public void updateCurrMarker() {
        //Called when event at the current marker is finished, but his is likely upated frm the client side
        this.currMarker = this.currMarker.nextMarker;
    }

}


