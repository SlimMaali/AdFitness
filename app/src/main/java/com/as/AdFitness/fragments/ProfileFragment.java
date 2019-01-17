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
        ImageView sexField = (ImageView)view.findViewById(R.id.user_sex_profile);
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
        if(P.getGender().equals("female"))
            sexField.setImageResource(R.drawable.female);
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dashboardActivity = (DashboardActivity) context;
    }

    public float calculateBMI(float weight,float height) {


            float bmi = weight / (height * height);

        return bmi;
    }

    private void displayBMI(float bmi) {
        String bmiLabel = "";
        /*
        if (Float.compare(bmi, 15f) <= 0) {
            bmiLabel = getString(R.string.very_severely_underweight);
        } else if (Float.compare(bmi, 15f) > 0  &&  Float.compare(bmi, 16f) <= 0) {
            bmiLabel = getString(R.string.severely_underweight);
        } else if (Float.compare(bmi, 16f) > 0  &&  Float.compare(bmi, 18.5f) <= 0) {
            bmiLabel = getString(R.string.underweight);
        } else if (Float.compare(bmi, 18.5f) > 0  &&  Float.compare(bmi, 25f) <= 0) {
            bmiLabel = getString(R.string.normal);
        } else if (Float.compare(bmi, 25f) > 0  &&  Float.compare(bmi, 30f) <= 0) {
            bmiLabel = getString(R.string.overweight);
        } else if (Float.compare(bmi, 30f) > 0  &&  Float.compare(bmi, 35f) <= 0) {
            bmiLabel = getString(R.string.obese_class_i);
        } else if (Float.compare(bmi, 35f) > 0  &&  Float.compare(bmi, 40f) <= 0) {
            bmiLabel = getString(R.string.obese_class_ii);
        } else {
            bmiLabel = getString(R.string.obese_class_iii);
        }

        bmiLabel = bmi + "\n\n" + bmiLabel;
        result.setText(bmiLabel);*/
    }

}
