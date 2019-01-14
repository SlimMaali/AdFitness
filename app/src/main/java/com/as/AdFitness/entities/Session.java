package com.as.AdFitness.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Session implements Parcelable {
    private int id;
    private String name;
    private String date;
    private String duration;
    private Room room;
    private int currentNb;
    private int maxNb;

    public Session()  {
    }

    public Session(String name, String date, String duration, Room room, int currentNb, int maxNb) {
        this.name = name;
        this.date = date;
        this.duration = duration;
        this.room = room;
        this.currentNb = currentNb;
        this.maxNb = maxNb;
    }

    public Session(int id, String name, String date, String duration, Room room, int currentNb, int maxNb) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.duration = duration;
        this.room = room;
        this.currentNb = currentNb;
        this.maxNb = maxNb;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Room getroom() {
        return room;
    }

    public void setroom(Room room) {
        this.room = room;
    }

    public int getCurrentNb() {
        return currentNb;
    }

    public void setCurrentNb(int currentNb) {
        this.currentNb = currentNb;
    }

    public int getMaxNb() {
        return maxNb;
    }

    public void setMaxNb(int maxNb) {
        this.maxNb = maxNb;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", duration='" + duration + '\'' +
                ", room=" + room +
                ", currentNb=" + currentNb +
                ", maxNb=" + maxNb +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Session session = (Session) o;

        if (getId() != session.getId()) return false;
        if (getroom() != session.getroom()) return false;
        if (getCurrentNb() != session.getCurrentNb()) return false;
        if (getMaxNb() != session.getMaxNb()) return false;
        if (getName() != null ? !getName().equals(session.getName()) : session.getName() != null)
            return false;
        if (getDate() != null ? !getDate().equals(session.getDate()) : session.getDate() != null)
            return false;
        return getDuration() != null ? getDuration().equals(session.getDuration()) : session.getDuration() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        result = 31 * result + (getDuration() != null ? getDuration().hashCode() : 0);
        result = 31 * result + getCurrentNb();
        result = 31 * result + getMaxNb();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(date);
        parcel.writeString(duration);
        parcel.writeParcelable(room,i);
        parcel.writeInt(currentNb);
        parcel.writeInt(maxNb);
    }
    public void readFromParcel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        date = in.readString();
        duration = in.readString();
        room = in.readParcelable(ClassLoader.getSystemClassLoader());
        currentNb = in.readInt();
        maxNb = in.readInt();
    }

    public Session(Parcel in) {
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
