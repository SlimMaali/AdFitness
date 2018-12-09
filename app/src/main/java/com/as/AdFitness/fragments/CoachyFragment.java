package com.as.AdFitness.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.as.AdFitness.DashboardActivity;
import com.as.AdFitness.R;
import com.as.AdFitness.pojo.Room;
import com.as.AdFitness.pojo.User;
import com.as.AdFitness.utility.Api;
import com.as.AdFitness.utility.ExploreSlidePagerAdapter;
import com.as.AdFitness.utility.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CoachyFragment extends Fragment {
    private DashboardActivity dashboardActivity;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public CoachyFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_coachy, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.viewPager_Coachy);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout_Coachy);

        final ExploreSlidePagerAdapter adapter = new ExploreSlidePagerAdapter(dashboardActivity.getSupportFragmentManager());

        UserService Us = Api.getInstance().getUserService();
        Call<ArrayList<User>> call = Us.getCoachs();
        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                ArrayList<User> coachs = response.body();
                CoachExploreFragment Cef = new CoachExploreFragment();
                Bundle b = new Bundle();
                b.putParcelableArrayList("coachs",coachs);
                Cef.setArguments(b);
                adapter.addFragment(Cef,"Nos Coachs");
                adapter.addFragment(new CustomSessionFragment(),"Cours personnalisés");
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {

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
