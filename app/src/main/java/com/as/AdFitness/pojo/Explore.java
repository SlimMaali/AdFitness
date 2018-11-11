package com.as.AdFitness.pojo;

/**
 * Created by dejiogunnubi on 9/25/17.
 */

public class Explore {
    private String name;
    private int image;//This should be replace with a url if pulling image from server
    private String minute;
    private int like;
    private int chat;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getChat() {
        return chat;
    }

    public void setChat(int chat) {
        this.chat = chat;
    }
}
