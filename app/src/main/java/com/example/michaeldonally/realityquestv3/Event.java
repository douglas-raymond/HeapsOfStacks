package com.example.michaeldonally.realityquestv3;

/**
 * Created by Michael Donally on 11/8/2016.
 */

public class Event {
    String name;
    boolean completed;
    String reward;
    String punishment;
    String flavourText;
    int exp;

    public void hasCompleted(){
        this.completed = true;
    }

    public Event(String n, String r, String p, int xp, String ft){
        this.name = n;
        this.completed = false;
        this.reward = r;
        this.punishment = p;
        this.exp = xp;
        this.flavourText = ft;
    }

    public String getFlavourText() { return this.flavourText; }

    public String getName(){
        return this.name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getReward() {
        return reward;
    }

    public String getPunishment() {
        return punishment;
    }

    public int getExp(){
        return exp;
    }
}
