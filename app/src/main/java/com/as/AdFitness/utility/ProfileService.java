package com.as.AdFitness.utility;

import com.as.AdFitness.pojo.Profile;
import com.as.AdFitness.pojo.User;

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
}
