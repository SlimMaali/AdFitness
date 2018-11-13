package com.as.AdFitness.utility;

import com.as.AdFitness.pojo.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserService {
    @GET("{username}/{password}")
    Call<User> logUser(@Path("username") String username, @Path("password") String password);
    @GET("user/{id}")
    Call<User> getUserData(@Path("id") int id);
}
