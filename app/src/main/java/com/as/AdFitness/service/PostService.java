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
}
