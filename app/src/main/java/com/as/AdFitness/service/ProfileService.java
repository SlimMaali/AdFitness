package com.as.AdFitness.service;

import com.as.AdFitness.entities.Profile;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProfileService {

    @GET("profile")
    Call<ArrayList<Profile>> getProfiles();
    //Get Profile
    @GET("profile/{id}")
    Call<Profile> getProfile(@Path("id") int id);
    @GET("profileUpdate/{id}/{gender}/{weight}/{height}")
    Call<Profile> updateProfile(@Path("id") int id,@Path("gender") String gender,
                             @Path("weight") float weight,@Path("height") float height);
}
