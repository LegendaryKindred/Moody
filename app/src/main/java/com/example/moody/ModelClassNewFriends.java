package com.example.moody;

public class ModelClassNewFriends {
    private int newFriendImage;
    private String newFriendName;
    private int newFriendAdd;

    ModelClassNewFriends(int newFriendImage, String newFriendName, int newFriendAdd){
        this.newFriendImage = newFriendImage;
        this.newFriendName = newFriendName;
        this.newFriendAdd = newFriendAdd;
    }
    public int getNewFriendImage(){
        return newFriendImage;
    }
    public String getNewFriendName(){
        return newFriendName;
    }
    public int getNewFriendAdd(){return newFriendAdd;}


}
