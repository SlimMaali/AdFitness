package com.as.AdFitness.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.as.AdFitness.DashboardActivity;
import com.as.AdFitness.LoginActivity;
import com.as.AdFitness.R;
import com.as.AdFitness.pojo.Session;
import com.as.AdFitness.pojo.User;
import com.as.AdFitness.utility.Api;
import com.as.AdFitness.utility.ParticipationService;
import com.as.AdFitness.utility.SessionAdapter;
import com.as.AdFitness.utility.SessionService;
import com.as.AdFitness.utility.SessionSubAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCoursesFragment extends Fragment {
    private RecyclerView recyclerView;
    private SessionSubAdapter adapter;
    private SharedPreferences sharedPreferences;
    private int userId;

    public MyCoursesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_courses, container, false);
        sharedPreferences = getActivity().getSharedPreferences("AdFitness",Context.MODE_PRIVATE);

        userId = sharedPreferences.getInt("id",0);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView_my_courses);

        ParticipationService SS = Api.getInstance().getParticipationService();
        Call<ArrayList<Session>> call = SS.mySubs(userId);
        call.enqueue(new Callback<ArrayList<Session>>() {
            @Override
            public void onResponse(Call<ArrayList<Session>> call, Response<ArrayList<Session>> response) {
                List<Session> sessions = response.body();
                adapter = new SessionSubAdapter(getContext(),sessions,userId);
                LinearLayoutManager llm = new LinearLayoutManager(getContext());
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(llm);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Session>> call, Throwable t) {
                Log.d("Anas", t.getMessage());

            }
        });


        return view;
    }

}
