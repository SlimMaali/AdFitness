package com.as.AdFitness.pojo;

import com.as.AdFitness.entities.Session;

import java.util.ArrayList;

public class DataHolder {
    private static DataHolder dataHolder = null;

    private DataHolder() {
    }

    public static DataHolder getInstance() {
        if (dataHolder == null)
            dataHolder = new DataHolder();
        return dataHolder;
    }
    private ArrayList<Session> allSession;
    private ArrayList<Session> mySubList;

    public ArrayList<Session> getAllSession() { return allSession; }

    public void setAllSession(ArrayList<Session> allSession) { this.allSession = allSession; }

    public ArrayList<Session> getMySubList() {
        return mySubList;
    }

    public void setMySubList(ArrayList<Session> mySubList) {
        this.mySubList = mySubList;
    }
}
