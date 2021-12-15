package com.example.moody;

import com.google.android.gms.maps.model.LatLng;

public class Report {
    private String description;
    private int mood;
    private LatLng location;

    public Report(){}

    public Report(int mood, LatLng location, String description){
        this.mood = mood;
        this.location = location;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Report{" +
                "description='" + description + '\'' +
                ", mood=" + mood +
                ", location=" + location +
                '}';
    }
}
