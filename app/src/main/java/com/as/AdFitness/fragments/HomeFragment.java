package com.as.AdFitness.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.as.AdFitness.R;
import com.as.AdFitness.adapters.PostAdapter;
import com.as.AdFitness.entities.Post;
import com.as.AdFitness.service.PostService;
import com.as.AdFitness.utility.Api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private PostAdapter adapter;
    private ListView listView;
    private ArrayList<Post>  posts = new ArrayList<>();


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        listView=(ListView)view.findViewById(R.id.listView_Posts);

        PostService postService = Api.getInstance().getPostService();
        Call<ArrayList<Post>> call = postService.getPosts();
        call.enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                posts = response.body();

                adapter = new PostAdapter(getContext(),posts);

                listView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                Log.d("Failure", t.getLocalizedMessage());

            }
        });


        return view;
    }

}
