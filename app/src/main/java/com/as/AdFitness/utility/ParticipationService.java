package com.as.AdFitness.utility;

import com.as.AdFitness.pojo.Participation;
import com.as.AdFitness.pojo.Session;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ParticipationService {

    @GET("Participation/add/{userId}/{sessionId}")
    Call<String> subToSession(@Path("userId") int userId, @Path("sessionId") int sessionId);
    @GET("Participation/rem/{userId}/{sessionId}")
    Call<String> unsubToSession(@Path("userId") int userId, @Path("sessionId") int sessionId);
    @GET("Participation/{id}")
    Call<List<Participation>> getUserSessions(@Path("id") int id);
}
