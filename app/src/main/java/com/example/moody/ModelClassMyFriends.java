package com.example.moody;

public class ModelClassMyFriends {
    private int imageview;
    private String textviewName;
    private String textViewMood;

    ModelClassMyFriends(int imageview, String textviewName, String textviewMood){
        this.imageview = imageview;
        this.textviewName = textviewName;
        this.textViewMood = textviewMood;
    }
    public int getImageview(){
        return imageview;
    }
    public String getTextviewName(){
        return textviewName;
    }
    public String getTextViewMood(){return textViewMood;}
}
