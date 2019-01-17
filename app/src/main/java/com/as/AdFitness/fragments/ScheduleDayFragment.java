package com.as.AdFitness.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.as.AdFitness.DashboardActivity;
import com.as.AdFitness.R;
import com.as.AdFitness.entities.Session;
import com.as.AdFitness.adapters.SessionAdapter;

import java.util.ArrayList;


public class ScheduleDayFragment extends Fragment {

    //The current position of the menu title
    private int position = 0;
    private RecyclerView recyclerView;
    private SessionAdapter adapter;
    private SharedPreferences sharedPreferences;

    private DashboardActivity dashboardActivity;

    public ScheduleDayFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =inflater.inflate(R.layout.fragment_schedule_day, container, false);


        ArrayList<Session> ListSession = getArguments().getParcelableArrayList("session");
       // ArrayList<Session> mySubs = getArguments().getParcelableArrayList("subs");

     /*   for(Session S : mySubs)
        {
            Log.e("Holaa Senior",S.toString());
        }*/

        sharedPreferences = getActivity().getSharedPreferences("AdFitness",Context.MODE_PRIVATE);
        int userid=sharedPreferences.getInt("id",0);
        if(ListSession!=null)
        {
            recyclerView = (RecyclerView) view.findViewById(R.id.recycleView_schedule);
            adapter = new SessionAdapter(getContext(),ListSession,userid);
            LinearLayoutManager llm = new LinearLayoutManager(getContext());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(llm);
            recyclerView.setAdapter(adapter);
        }

        return view;
    }

    /**
     * This gets the current position the user tap on
     * @param position
     */
    public void getPosition(int position) {
        this.position = position;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dashboardActivity = (DashboardActivity) context;
    }

}
