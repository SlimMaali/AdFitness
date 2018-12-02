package com.as.AdFitness.utility;

import com.as.AdFitness.pojo.Room;
import com.as.AdFitness.pojo.Session;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SessionService {

    //Get Room
    @GET("session/{id}")
    Call<Session> getSession(@Path("id") int id);
    //Get Rooms
    @GET("session")
    Call<List<Session>> getSessions();
}
