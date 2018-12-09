package com.as.AdFitness.utility;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.as.AdFitness.R;
import com.as.AdFitness.pojo.Profile;
import com.as.AdFitness.pojo.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoachAdapter extends ArrayAdapter<User> {
    public CoachAdapter(Context context, ArrayList<User> Coachs) {
        super(context, 0, Coachs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        User Coach = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_coach, parent, false);
        }
        // Lookup view for data population
        final ImageView ivImg = (ImageView) convertView.findViewById(R.id.ivImg_item_coach);
        TextView tvNom = (TextView) convertView.findViewById(R.id.tvName_item_coach);

        ProfileService Ps = Api.getInstance().getProfileService();
        Call<Profile> call = Ps.getProfile(Coach.getId());
        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                Profile p = response.body();
                Log.e("Profile N ",p.toString());
                Picasso.with(getContext()).load("http://10.0.2.2/AdFitness/uploads/user_image/"+p.getImage()+".png").into(ivImg);
            }
            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Log.d("Failure", t.getLocalizedMessage());

            }
        });
        // Populate the data into the template view using the data object
        //ivImg.setImageResource(Coach.img);
        tvNom.setText(Coach.getFirstName()+" "+Coach.getLastName());
        // Return the completed view to render on screen
        return convertView;
    }
}
