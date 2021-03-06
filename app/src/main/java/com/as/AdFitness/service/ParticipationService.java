package com.as.AdFitness.service;

import com.as.AdFitness.entities.Participation;
import com.as.AdFitness.entities.Session;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ParticipationService {

    @GET("Participation/add/{userId}/{sessionId}")
    Call<String> subToSession(@Path("userId") int userId, @Path("sessionId") int sessionId);
    @GET("Participation/rem/{userId}/{sessionId}")
    Call<String> unsubToSession(@Path("userId") int userId, @Path("sessionId") int sessionId);
    @GET("myParticipation/{userId}")
    Call<ArrayList<Session>> mySubs(@Path("userId") int userId);
    @GET("myParticipationCount/{userId}")
    Call<String> mySubsCount(@Path("userId") int userId);
    @GET("Participation/{id}")
    Call<ArrayList<Participation>> getUserSessions(@Path("id") int id);
}
