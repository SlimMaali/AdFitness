package com.as.AdFitness.fragments;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.as.AdFitness.R;
import com.as.AdFitness.entities.Profile;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class CoachSingleFragment extends DialogFragment {


    public CoachSingleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_coach_single, container, false);
        Profile P = (Profile)getArguments().getParcelable("profile");

        TextView nameField = (TextView)view.findViewById(R.id.coach_name_single);
        TextView heightField = (TextView)view.findViewById(R.id.coach_height_single);
        TextView weightField = (TextView)view.findViewById(R.id.coach_weight_single);
        ImageView imageField = (ImageView)view.findViewById(R.id.coach_img_single);
        Picasso.with(getContext()).load(P.getImage()).into(imageField);
        nameField.setText(P.getUser().getFirstName()+" "+P.getUser().getLastName());
        heightField.setText(Float.toString(P.getHeight())+" M");
        weightField.setText(Float.toString(P.getWeight())+" KG");
        getDialog().setTitle(P.getUser().getFirstName()+" "+P.getUser().getLastName());
        return view;
    }

}
