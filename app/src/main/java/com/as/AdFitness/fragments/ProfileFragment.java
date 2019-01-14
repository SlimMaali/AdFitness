package com.as.AdFitness.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.as.AdFitness.DashboardActivity;
import com.as.AdFitness.R;
import com.as.AdFitness.entities.Profile;
import com.as.AdFitness.entities.User;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {

    private DashboardActivity dashboardActivity;

    public ProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        Profile P = (Profile)getArguments().getParcelable("profile");
        TextView nameField = (TextView)view.findViewById(R.id.user_name_profile);
        ImageView imageField = (ImageView)view.findViewById(R.id.user_img_profile);
        TextView birthdayField = (TextView)view.findViewById(R.id.user_birthday_profile);
        TextView phoneField = (TextView)view.findViewById(R.id.user_phone_profile);
        TextView weightField = (TextView)view.findViewById(R.id.user_weight_profile);
        TextView heightField = (TextView)view.findViewById(R.id.user_height_profile);

        nameField.setText(P.getUser().getFirstName()+" "+P.getUser().getLastName());
        Picasso.with(getContext()).load(P.getImage()).into(imageField);
        birthdayField.setText(P.getUser().getBirthday());
        phoneField.setText(P.getUser().getPhone());
        weightField.setText(Float.toString(P.getWeight())+" KG");
        heightField.setText(Float.toString(P.getHeight())+" M");

        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dashboardActivity = (DashboardActivity) context;
    }


}
