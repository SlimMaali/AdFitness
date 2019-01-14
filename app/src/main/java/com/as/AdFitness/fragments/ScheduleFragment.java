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
import com.as.AdFitness.entities.Session;
import com.as.AdFitness.utility.Api;
import com.as.AdFitness.service.SessionService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {
    private DashboardActivity dashboardActivity;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String[] tabTitle = new String[]{"Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};

    public ScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.viewPager_schedule);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout_schedule);


        final ExploreSlidePagerAdapter adapter = new ExploreSlidePagerAdapter(dashboardActivity.getSupportFragmentManager());

        SessionService sessionService = Api.getInstance().getSessionService();
        Call<ArrayList<Session>> call = sessionService.getSessions();
        call.enqueue(new Callback<ArrayList<Session>>() {
            @Override
            public void onResponse(Call<ArrayList<Session>> call, Response<ArrayList<Session>> response) {
                List<Session> sessions = response.body();
                Map<Integer, ArrayList<Session>> sessionMap = new TreeMap<>();
                Calendar c = Calendar.getInstance();
                int dayOfWeek = 0;
                ArrayList<Session> currentList;
                for (Session S : sessions) {
                    try {
                        Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(S.getDate());
                        c.setTime(d);
                        dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
                    } catch (ParseException e) {
                        Log.e("Exception", e.getMessage());
                    }
                    currentList = sessionMap.get(dayOfWeek);
                    if (currentList == null)
                        currentList = new ArrayList<>();
                    currentList.add(S);
                    sessionMap.put(dayOfWeek, currentList);
                }


                for (int i = 0; i < 7; i++) {
                    ScheduleDayFragment SDF = new ScheduleDayFragment();
                    Bundle b = new Bundle();
                    b.putParcelableArrayList("session", sessionMap.get(i + 1));
                    SDF.setArguments(b);
                    adapter.addFragment(SDF, tabTitle[i]);
                    adapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onFailure(Call<ArrayList<Session>> call, Throwable t) {
                Log.d("Failure", t.getLocalizedMessage());

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

    private class ExploreSlidePagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ExploreSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }



}