package com.example.michaeldonally.realityquestv3;

import android.app.Application;

/**
 * Created by Michael Donally on 11/15/2016.
 */

public class GameData {
    private Event event1;
    private Event event2;
    private Event event3;

    private int eventTracker = 0;

    private GameData() {}

    public void setPlayer(PlayerCharacter p) {
        this.player = p;
    }

    public void incrementEventTracker() { this.eventTracker++; }

    public Event getEvent1() { return this.event1; }
    private static GameData gameData;

    private PlayerCharacter player;

    public Event getEvent2() { return this.event2; }
    public Event getEvent3() { return this.event3; }

    public void setEvent1(Event e) { this.event1 = e; }
    public void setEvent2(Event e) { this.event2 = e; }
    public void setEvent3(Event e) { this.event3 = e; }

    public PlayerCharacter getPlayer() { return this.player; }

    public static synchronized GameData getInstance() {
        if(gameData==null){
            gameData=new GameData();
        }
        return gameData;
    }
}
