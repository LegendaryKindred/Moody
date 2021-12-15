package com.example.moody;

public class ModelClassMyFriends {
    private int imageview;
    private String textviewName;
    private String textViewMood;
    private int friendRemove;

    ModelClassMyFriends(int imageview, String textviewName, int friendRemove,  String textviewMood){
        this.imageview = imageview;
        this.textviewName = textviewName;
        this.textViewMood = textviewMood;
        this.friendRemove = friendRemove;
    }
    public int getImageview(){
        return imageview;
    }
    public String getTextviewName(){
        return textviewName;
    }
    public String getTextViewMood(){return textViewMood;}
    public int getFriendRemove() { return friendRemove; }
}
