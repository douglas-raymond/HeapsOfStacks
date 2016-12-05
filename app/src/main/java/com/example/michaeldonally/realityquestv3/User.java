package com.example.michaeldonally.realityquestv3;

import java.net.Authenticator;
import java.util.ArrayList;

/**
 * Created by Michael Donally on 11/8/2016.
 */

public class User {
    String username;
    String password;
    int markerIndex;

    PlayerCharacter character;

    public User(String u, String p) {
        this.username = u;
        this.password = p;
        this.markerIndex = 0;
        //SHOULD CALL UPDATE USERLIST HERE
    }

    public User() {

    }

    public void createEvent(Event e) {
        //Sends e to be saved on server
        RequestManager.getInstance().uploadEvent(e);
    }

    public void createMap(Map m) {
        //Sends m to be saved on server
        RequestManager.getInstance().uploadMap(m);
    }

    public String[] getMapList() {
        //Receives map list from server
        return null;
    }

    public void getEvent(ResponseListener<Event> listener) {
        //Should receive random event from the server
        RequestManager.getInstance().getRandomEvent(listener);
    }

    public void updateUserList(User u) {
        //Sends the user to the server to overwrite itself in the userlist if it exists or add it if it doesnt
    }

    public User getUserData(String username, String password) {
        //Sends user name and password to the server that returns the apropriate user object

        // User user = RequestManager.getInstance().getUserDetails(username, password);

//        if(user == null) {
//            return null;
//        } else {
//            return user;
//        }

        return new User();
    }

    public Map getMap(String title) {
        //Sends title to server and receives the respective map object
        return null;
    }

    public void updateRating(int r, String n) {
        //Sends the rating to the server and find the map named n and updates its rating
    }

    public String getUsername() {
        return this.username;
    }
    public PlayerCharacter getCharacter() { return this.character; }
    public void setCharacter(PlayerCharacter c) {
        this.character = c;
    }
}
