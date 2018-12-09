package com.as.AdFitness.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.as.AdFitness.DashboardActivity;
import com.as.AdFitness.R;

public class CustomSessionFragment extends Fragment {
    private DashboardActivity dashboardActivity;

    public CustomSessionFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_custom_session, container, false);

        return v;
    }




}
