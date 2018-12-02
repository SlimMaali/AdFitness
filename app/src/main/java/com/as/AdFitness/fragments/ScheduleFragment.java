package com.as.AdFitness.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.as.AdFitness.DashboardActivity;
import com.as.AdFitness.LoginActivity;
import com.as.AdFitness.R;
import com.as.AdFitness.pojo.Room;
import com.as.AdFitness.pojo.Session;
import com.as.AdFitness.pojo.User;
import com.as.AdFitness.utility.Api;
import com.as.AdFitness.utility.RoomService;
import com.as.AdFitness.utility.SessionAdapter;
import com.as.AdFitness.utility.SessionService;

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
    private String[] tabTitle = new String[]{"Dimanche","Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};

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
        Call<List<Session>> call = sessionService.getSessions();
        call.enqueue(new Callback<List<Session>>() {
            @Override
            public void onResponse(Call<List<Session>> call, Response<List<Session>> response) {
                List<Session> sessions = response.body();
                Log.d("TG", "Data successfully downloaded");
                Map<Integer,ArrayList<Session>> sessionMap = new TreeMap<>();
                Calendar c = Calendar.getInstance();
                int dayOfWeek=0;
                ArrayList<Session> currentList;
                    for (Session S : sessions) {
                    try{
                        Date d=new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(S.getDate());
                        Log.d("TG", ""+d.toString());
                        c.setTime(d);
                        dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
                        Log.d("dayOfWeek", "dayOfWeek IS : "+dayOfWeek+" For "+S.toString());
                    }catch (ParseException e ){
                        Log.e("Exception",e.getMessage());
                    }
                    currentList=sessionMap.get(dayOfWeek);
                    if(currentList==null)
                        currentList = new ArrayList<>();
                    currentList.add(S);
                    Log.e("List is ",currentList.toString());
                    sessionMap.put(dayOfWeek,currentList);

                }
                for (int i = 0; i < 7; i++) {
                    ScheduleDayFragment SDF = new ScheduleDayFragment();
                    Bundle b = new Bundle();
                    b.putParcelableArrayList("session",sessionMap.get(i+1));
                    SDF.setArguments(b);
                    adapter.addFragment(SDF, tabTitle[i]);
                    adapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onFailure(Call<List<Session>> call, Throwable t) {
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