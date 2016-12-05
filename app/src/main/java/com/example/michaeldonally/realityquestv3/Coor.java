package com.example.michaeldonally.realityquestv3;

/**
 * Created by Michael Donally on 11/8/2016.
 */

public class Coor {
    double longitude;
    double latitude;

    //Returns the distance in meters from the calling coor to the sent coor
    public float distanceTo(Coor c) {
        double earthRadius = 637100;
        double dLat = Math.toRadians(c.latitude - this.latitude);
        double dLong = Math.toRadians(c.longitude - this.longitude);

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(Math.toRadians(this.latitude)) * Math.sin(dLong/2) * Math.sin(dLong/2);

        double p = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        float dist = (float) (earthRadius * p);

        return dist;
    }

    public Coor() {}

    public Coor(double lat, double lon) {
        this.longitude = lon;
        this.latitude = lat;
    }
}
