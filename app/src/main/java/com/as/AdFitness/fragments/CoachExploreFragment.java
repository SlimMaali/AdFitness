package com.as.AdFitness.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.as.AdFitness.DashboardActivity;
import com.as.AdFitness.R;
import com.as.AdFitness.entities.Profile;
import com.as.AdFitness.entities.User;
import com.as.AdFitness.utility.Api;
import com.as.AdFitness.adapters.CoachAdapter;
import com.as.AdFitness.service.ProfileService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoachExploreFragment extends Fragment {
    private DashboardActivity dashboardActivity;
    private ListView listView;


    public CoachExploreFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_coach_explore, container, false);
        final ArrayList<User> Coachs = getArguments().getParcelableArrayList("coachs");
        listView = (ListView) v.findViewById(R.id.listView_Coachs);
        CoachAdapter adapter = new CoachAdapter(getActivity(),Coachs);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, int i, long l) {
                ProfileService ps = Api.getInstance().getProfileService();
                Call<Profile> call = ps.getProfile(Coachs.get(i).getId());
                call.enqueue(new Callback<Profile>() {
                    @Override
                    public void onResponse(Call<Profile> call, Response<Profile> response) {
                        Profile p = response.body();
                        CoachSingleFragment CSF = new CoachSingleFragment();
                        Bundle b = new Bundle();
                        b.putParcelable("profile",p);
                        CSF.setArguments(b);
                        CSF.show(getFragmentManager(),"frag");

                      /* getChildFragmentManager().beginTransaction()
                                .replace(v.findViewById(R.id.container_coach).getId(), CSF)
                                .addToBackStack(null)
                                .commit();*/

                    }
                    @Override
                    public void onFailure(Call<Profile> call, Throwable t) {

                    }
                });
            }
        });
        listView.setAdapter(adapter);

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dashboardActivity = (DashboardActivity) context;
    }




}
