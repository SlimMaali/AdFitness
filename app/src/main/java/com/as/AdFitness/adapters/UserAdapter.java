package com.as.AdFitness.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.as.AdFitness.R;
import com.as.AdFitness.entities.Profile;
import com.as.AdFitness.entities.User;
import com.as.AdFitness.service.ProfileService;
import com.as.AdFitness.utility.Api;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAdapter extends ArrayAdapter<User> {
    public UserAdapter(Context context, ArrayList<User> Coachs) {
        super(context, R.layout.item_user, Coachs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        User Coach = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
        }
        // Lookup view for data population
        final ImageView ivImg = (ImageView) convertView.findViewById(R.id.ivImg_item_coach);
        TextView tvNom = (TextView) convertView.findViewById(R.id.tvName_item_coach);
        // Populate the data into the template view using the data object
        //ivImg.setImageResource(Coach.img);
        tvNom.setText(Coach.getFirstName()+" "+Coach.getLastName());
        if (Coach.getRole().equals("User"))
        {
            ProfileService ps= Api.getInstance().getProfileService();
            Call<Profile> call = ps.getProfile(Coach.getId());
            call.enqueue(new Callback<Profile>() {
                @Override
                public void onResponse(Call<Profile> call, Response<Profile> response) {
                    Profile p = response.body();
                    Picasso.with(getContext()).load(p.getImage()).into(ivImg);
                }

                @Override
                public void onFailure(Call<Profile> call, Throwable t) {
                    Log.d("Failure", t.getLocalizedMessage());
                }
            });



        }else
            Picasso.with(getContext()).load("http://10.0.2.2/AdFitness/uploads/user_image/2.png").into(ivImg);
        // Return the completed view to render on screen
        return convertView;
    }
}
