package com.example.moody;

public class ModelClassMyFriends {
    private int imageview;
    private String textviewName;

    ModelClassMyFriends(int imageview, String textviewName){
        this.imageview = imageview;
        this.textviewName = textviewName;
    }
    public int getImageview(){
        return imageview;
    }
    public String getTextviewName(){
        return textviewName;
    }
}
