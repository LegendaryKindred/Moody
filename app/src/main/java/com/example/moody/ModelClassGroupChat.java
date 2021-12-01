package com.example.moody;

public class ModelClassGroupChat {
    private int GCImage;
    private String GCName;

    ModelClassGroupChat(int GCImage, String GCName){
        this.GCImage = GCImage;
        this.GCName = GCName;
    }
    public int getGCImage(){
        return GCImage;
    }
    public String getGCName(){
        return GCName;
    }
}
