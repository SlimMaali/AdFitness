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

/**
 * A simple {@link Fragment} subclass.
 */
public class ExploreFragment extends Fragment {
    private DashboardActivity dashboardActivity;
    private String[] tabTitle = new String[]{"RPM", "MusculaIon", "Cardio", "Salle des Cours", "Vestaires"};

    public ExploreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_explore, container, false);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);

        ExploreSlidePagerAdapter adapter = new ExploreSlidePagerAdapter(dashboardActivity.getSupportFragmentManager());

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dashboardActivity = (DashboardActivity) context;
    }

    private class ExploreSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ExploreSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            FoodExploreFragment fragment = new FoodExploreFragment();
            fragment.getPosition(position);

            return fragment;
        }

        @Override
        public int getCount() {
            return tabTitle.length;//No of fragment created
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitle[position];
        }
    }

}
