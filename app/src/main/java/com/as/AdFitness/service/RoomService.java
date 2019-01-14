package com.as.AdFitness.service;

import com.as.AdFitness.entities.Room;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RoomService {


    //Get Room
    @GET("room/{id}")
    Call<Room> getRoom(@Path("id") int id);
    //Get Rooms
    @GET("room")
    Call<ArrayList<Room>> getRooms();
}
