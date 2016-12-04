package com.example.michaeldonally.realityquestv3;

/**
 * Created by Michael Donally on 11/8/2016.
 */

public class Event {
    String name;
    String message;
    String options[];
    String imageLoc;

    public Event(String n, String m, String[] o, String imageLoc){
        this.name = n;
        this.message = m;
        this.options = o;
        this.imageLoc = imageLoc;
    }

    public Event() {

    }

    public void setImageLoc(String l) {
        imageLoc = l;
    }

    public String getImageLoc() {
        return imageLoc;
    }

    public void setName(String n) {
        name = n;
    }

    public void setMessage(String m) {
        message = m;
    }

    //public void setOption(Option o, int i) {
    //    this.options[i] = o;
    //}

    public String getName(){
        return this.name;
    }

    public String getMessage() {return this.message; }

    public String getOption(int i){
        return options[i];
    }
}