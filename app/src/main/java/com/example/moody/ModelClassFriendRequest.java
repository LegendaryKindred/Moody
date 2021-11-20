package com.example.moody;

public class ModelClassFriendRequest {
    private int image;
    private String name;
    private int add;
    private int block;

    ModelClassFriendRequest(int image, String name, int add, int block){
        this.image = image;
        this.name = name;
        this.add = add;
        this.block = block;
    }
    public int getImage(){
        return image;
    }
    public String getName(){
        return name;
    }
    public int getAdd(){return add;}
    public int getBlock(){return block;}
}
