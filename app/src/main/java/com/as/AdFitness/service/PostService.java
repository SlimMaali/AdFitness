package com.as.AdFitness.service;

import com.as.AdFitness.entities.Post;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostService {

    //Get Post
    @GET("Post/list")
    Call<ArrayList<Post>> getPosts();
    //Get Post
    @GET("post/{id}")
    Call<Post> getPost(@Path("id") int id);
    @GET("/Post/edit/{id}/{Title}/{Image}/{Content}")
    Call<Post> editPost(@Path("id") int id, @Path("Title") String Title, @Path("Image")String Image, @Path("Content") String Content);
    @GET("/Post/add/{Title}/{Image}/{Content}")
    Call<Post> newPost(@Path("Title") String Title, @Path("Image")String Image, @Path("Content") String Content);
}
