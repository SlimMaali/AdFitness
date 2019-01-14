package com.as.AdFitness.service;

import com.as.AdFitness.entities.Session;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SessionService {

    //Get Room
    @GET("session/{id}")
    Call<Session> getSession(@Path("id") int id);
    //Get Rooms
    @GET("session")
    Call<ArrayList<Session>> getSessions();
}
