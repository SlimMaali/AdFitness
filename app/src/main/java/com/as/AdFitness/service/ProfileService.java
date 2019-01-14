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
    /*@Multipart
    @POST("profile/{user_id}/{gender}/{weight}/{height}")
    Call<ResponseBody> AddProfile(
            @Part("user_id") RequestBody user_id,
            @Part("gender") RequestBody gender,
            @Part("weight") RequestBody weight,
            @Part("height") RequestBody height,
            @Part MultipartBody.Part image
    );*/
}
