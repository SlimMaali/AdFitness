package com.as.AdFitness.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Post implements Parcelable {
    private int id;
    private String Title;
    private String Description;
    private java.util.Date Date;

    public Post(int id, String title, String description, java.util.Date date) {
        this.id = id;
        Title = title;
        Description = description;
        Date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        // Write object
        out.writeInt(id);
        out.writeString(Title);
        out.writeString(Description);
        out.writeSerializable(Date);

    }

    private void readFromParcel(Parcel in) {
        // Read object
        id = in.readInt();
        Title = in.readString();
        Description = in.readString();
        Date = (java.util.Date) in.readSerializable();

    }

    public Post(Parcel in) {
        super();
        readFromParcel(in);
    }

    public static final Parcelable.Creator<Post> CREATOR = new Parcelable.Creator<Post>() {
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        public Post[] newArray(int size) {

            return new Post[size];
        }

    };
}
