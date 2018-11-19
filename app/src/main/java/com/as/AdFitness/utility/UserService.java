package com.as.AdFitness.utility;

import com.as.AdFitness.pojo.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserService {

    //Login
    @GET("login/{username}/{password}")
    Call<User> logUser(@Path("username") String username, @Path("password") String password);
    //Get User
    @GET("user/{id}")
    Call<User> getUserData(@Path("id") int id);
    //Look For username if exist
    @GET("usernameExist/{username}")
    Call<String> usernameExist(@Path("username") String username);
    //Look For email if exist
    @GET("emailExist/{email}")
    Call<String> emailExist(@Path("email") String email);
    //Register
    @GET("register/{username}/{password}/{email}/{birthday}/client/{firstName}/{lastName}/{phone}")
    Call<User> registerUser(@Path("username") String username, @Path("password") String password,
                            @Path("email") String email,@Path("birthday") String birthday,
                            @Path("firstName") String firstName,@Path("lastName") String lastName,
                            @Path("phone") String phone
                            );
}
