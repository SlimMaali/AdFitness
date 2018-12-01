package com.as.AdFitness.utility;

import com.as.AdFitness.pojo.Room;
import com.as.AdFitness.pojo.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RoomService {


    //Get Room
    @GET("room/{id}")
    Call<Room> getRoom(@Path("id") int id);
    //Get Rooms
    @GET("room")
    Call<List<Room>> getRooms();
}
