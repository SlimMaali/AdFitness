package com.as.AdFitness.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Room implements Parcelable {
    private int id;
    private String name;
    private String description;
    private String image;

    public Room()  {
    }

    public Room(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public Room(int id, String name, String description, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image=" + image +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }
    public Room(Parcel in) {
        super();
        readFromParcel(in);
    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(image);
    }

    public void readFromParcel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        image = in.readString();
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
