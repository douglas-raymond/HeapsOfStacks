package com.example.michaeldonally.realityquestv3;

/**
 * Created by Michael Donally on 11/8/2016.
 */

public class Note {
    String title;
    String text;

    public Note(String t, String b) {
        this.title = t;
        this.text = b;
    }

    public String noteToString() { return this.text;}

    public void setTitle(String newT) {
        this.title = newT;
    }

    public void setText(String newText) {
        this.text = newText;
    }

    public String getTitle() {
        return this.title;
    }

    public String getText() {
        return this.text;
    }
}
