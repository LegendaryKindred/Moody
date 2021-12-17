package com.example.moody;
public class Report {
    private String description;
    private int mood;
    private double lat, lng;

    public Report(){}

    public Report(int mood, double lat, double lng , String description){
        this.mood = mood;
        this.lat = lat;
        this.lng = lng;
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

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Report{" +
                "description='" + description + '\'' +
                ", mood=" + mood +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
