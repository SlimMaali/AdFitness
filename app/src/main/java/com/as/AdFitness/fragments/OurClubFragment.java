package com.as.AdFitness.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.as.AdFitness.DashboardActivity;
import com.as.AdFitness.R;
import com.as.AdFitness.pojo.Room;
import com.as.AdFitness.utility.Api;
import com.as.AdFitness.utility.ExploreSlidePagerAdapter;
import com.as.AdFitness.utility.RoomService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class OurClubFragment extends Fragment {
    private DashboardActivity dashboardActivity;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String[] tabTitle = new String[]{"RPM", "MusculaIon", "Cardio", "Salle des Cours", "Vestaires"};

    public OurClubFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_our_club, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.viewPager_Our_Club);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout_Our_Club);

        final ExploreSlidePagerAdapter adapter = new ExploreSlidePagerAdapter(dashboardActivity.getSupportFragmentManager());



        RoomService roomService = Api.getInstance().getRoomService();
        Call<ArrayList<Room>> call = roomService.getRooms();
        call.enqueue(new Callback<ArrayList<Room>> () {
            @Override
            public void onResponse(Call<ArrayList<Room>> call, Response<ArrayList<Room>> response) {
                List<Room> rooms = response.body();
                for (Room f: rooms) {
                    RoomExploreFragment RF = new RoomExploreFragment();
                    Bundle b = new Bundle();
                    b.putInt("Id",f.getId());
                    b.putString("Title",f.getName());
                    b.putString("Description",f.getDescription());
                    b.putString("Image",f.getImage());
                    RF.setArguments(b);
                    adapter.addFragment(RF, f.getName());
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Room>> call, Throwable t) {

            }
        });


        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dashboardActivity = (DashboardActivity) context;
    }



}
