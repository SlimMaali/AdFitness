package com.as.AdFitness.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Profile implements Parcelable {
    private int id;
    private User user;
    private String gender;
    private float weight;
    private float height;
    private String image;



    public Profile() {
    }

    public Profile(int id, User user, String gender, float weight, float height, String image) {
        this.id = id;
        this.user = user;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.image = image;
    }

    public Profile(User user, String gender, float weight, float height, String image) {
        this.user = user;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeParcelable(user,i);
        parcel.writeString(gender);
        parcel.writeFloat(weight);
        parcel.writeFloat(height);
        parcel.writeString(image);
    }
    public void readFromParcel(Parcel in) {
        id = in.readInt();
        user = in.readParcelable(getClass().getClassLoader());
        gender = in.readString();
        weight = in.readFloat();
        height = in.readFloat();
        image = in.readString();

    }

    public Profile(Parcel in) {
        super();
        readFromParcel(in);
    }

    public static final Parcelable.Creator<Profile> CREATOR = new Parcelable.Creator<Profile>() {
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        public Profile[] newArray(int size) {

            return new Profile[size];
        }

    };
    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", user=" + user.toString()+
                ", gender='" + gender + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                ", image='" + image + '\'' +
                '}';
    }
}
