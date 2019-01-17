package com.as.AdFitness.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Post implements Parcelable {

    private int id;
    private String date;
    private String title;
    private String content;
    private String image;

    public Post()
    {

    }

    public Post(int id, String date, String title, String content, String image) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.content = content;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(date);
        parcel.writeString(title);
        parcel.writeString(content);
        parcel.writeString(image);
    }
    public void readFromParcel(Parcel in) {
        id = in.readInt();
        date = in.readString();
        title = in.readString();
        content = in.readString();
        image = in.readString();
    }

    public Post(Parcel in) {
        super();
        readFromParcel(in);
    }

    public static final Parcelable.Creator<Session> CREATOR = new Parcelable.Creator<Session>() {
        public Session createFromParcel(Parcel in) {
            return new Session(in);
        }

        public Session[] newArray(int size) {

            return new Session[size];
        }

    };
}
