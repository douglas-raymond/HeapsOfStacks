package com.example.michaeldonally.realityquestv3;

/**
 * Created by Michael Donally on 11/27/2016.
 */

public class Option {
    String text;
    int reward;
    int punishment;
    int chance;

    public Option() {}

    public Option(String t, int r, int c, int p) {
        this.reward = r;
        this.punishment = p;
        this.chance = c;
        this.text = t;
    }

    public String getText() { return text; }

    public int getReward() {return reward;}

    public int getPunishment() {return punishment;}

    public int getChance() {return chance;}

    public void setReward(int i) {
        this.reward = i;
    }

    public void setPunishment(int i) {
        this.punishment = i;
    }

    public void setChance(int i) {
        this.chance = i;
    }
}
