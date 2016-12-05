package com.example.michaeldonally.realityquestv3;

import java.util.*;

/**
 * Created by Michael Donally on 10/27/2016.
 */

public class PlayerCharacter {
    String characterName;
    double disToNextMarker;
    String playerBio;
    Marker currMarker;  //Marker being headed for, gets updated when event at it is done
    int healthPoints;
    int level;

    int completedEvents;

    Map gameMap;

    public void setBio(String n) {
        this.playerBio = n;
    }
    public void setCName(String newName) {
        this.characterName = newName;
    }
    public String getCName() {return this.characterName;}

    public PlayerCharacter(String n, String b) {
        //All other attributes dont get assigned right away
        this.characterName = n;
        this.playerBio = b;
        this.completedEvents = 0;
        this.level = 1;
        this.healthPoints = 10;
    }

    public void setGameMap(Map m){
        this.gameMap = m;
        this.currMarker = m.getFirstMarker();
    }

    public void adjustHealth(int num) {
        this.healthPoints += num;
    }

    public void gainLevel() {
        this.level++;
    }
    public int getLevel() {return this.level;}

    public void setLevel(int i) { this.level = this.level + i;}

    public void incrementCompletedEvents() {
        this.completedEvents++;
    }

    public int getCompletedEvents() {return completedEvents;}

    public void setHitPoints(int i) { this.healthPoints = this.healthPoints - i;}

    public Marker getCurrMarker() {
        return this.currMarker;
    }

    public void setCurrMarker(){
        this.currMarker = gameMap.markers.get(this.completedEvents);
    }
}

