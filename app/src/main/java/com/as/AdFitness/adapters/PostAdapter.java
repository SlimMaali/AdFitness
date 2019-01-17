package com.as.AdFitness.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.as.AdFitness.R;
import com.as.AdFitness.entities.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostAdapter extends ArrayAdapter<Post> {
    public PostAdapter(Context context, ArrayList<Post> Posts) {
        super(context, R.layout.item_post, Posts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Post Post = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_post, parent, false);
        }
        else convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_post, parent, false);
        // Lookup view for data population
        final ImageView ivImg = (ImageView) convertView.findViewById(R.id.Image_Post);
        if (Post.getImage()!=null) Picasso.with(getContext()).load(Post.getImage()).into(ivImg);
        else Picasso.with(getContext()).load("http://10.0.2.2/AdFitness/uploads/user_image/2.png").into(ivImg);

        TextView title = (TextView) convertView.findViewById(R.id.title_Post);
        TextView description = (TextView) convertView.findViewById(R.id.description_Post);
        TextView date = (TextView) convertView.findViewById(R.id.date_Post);

        // Populate the data into the template view using the data object
        //ivImg.setImageResource(Coach.img);
        title.setText(Post.getTitle());
        description.setText(Post.getContent());
        date.setText(Post.getDate().toString());
        // Return the completed view to render on screen
        return convertView;
    }


}
