package com.example.gshare.ModelClasses.Location;

public class LocationG {
    private double latitude;
    private double longitude;

    public LocationG(){
        latitude = 2;//MainActivity.lat;
        longitude = 2;//MainActivity.lon;
    }
    public double getLatitude(){
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatitude( double latitude ) {
        this.latitude = latitude;
    }

    public void setLongitude( double longitude ) {
        this.longitude = longitude;
    }

    public double distanceFrom(LocationG location ){//NOT SURE BUT I GUESS IT WORKS
        return Math.abs( distance( latitude, longitude, location.getLatitude() , location.getLongitude(), 'K' ) );
    }
    public boolean isInRange(){ return true; }

    /*
    https://dzone.com/articles/distance-calculation-using-3
     */
    private double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K') {
            dist = dist * 1.609344;
        } else if (unit == 'N') {
            dist = dist * 0.8684;
        }
        return (dist);
    }
    public String toString(){
        return latitude + " " + longitude;
    }
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts decimal degrees to radians             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts radians to decimal degrees             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

}