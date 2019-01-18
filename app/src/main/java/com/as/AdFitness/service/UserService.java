package com.as.AdFitness.service;

import com.as.AdFitness.entities.User;

import java.util.ArrayList;

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
    @GET("register/{username}/{password}/{email}/{birthday}/user/{firstName}/{lastName}/{phone}")
    Call<User> registerUser(@Path("username") String username, @Path("password") String password,
                            @Path("email") String email,@Path("birthday") String birthday,
                            @Path("firstName") String firstName,@Path("lastName") String lastName,
                            @Path("phone") String phone
                            );

    @GET("edit/{id}/{username}/{password}/{email}/{birthday}/{role}/{firstName}/{lastName}/{phone}")
    Call<User> editUser(@Path("id") int id,@Path("username") String username, @Path("password") String password,
                            @Path("email") String email,@Path("birthday") String birthday,@Path("role") String role,
                            @Path("firstName") String firstName,@Path("lastName") String lastName,
                            @Path("phone") String phone
    );
    @GET("coach")
    Call<ArrayList<User>> getCoachs();
    @GET("user")
    Call<ArrayList<User>> getUsers();
}
