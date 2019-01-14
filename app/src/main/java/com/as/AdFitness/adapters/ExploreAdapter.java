package com.as.AdFitness.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.as.AdFitness.R;
import com.as.AdFitness.pojo.Explore;

import java.util.List;

/**
 * Created by dejiogunnubi on 9/24/17.
 */

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ExploreViewAdapter> {
    private Context mContext;
    private List<Explore> exploreList;


    public ExploreAdapter(Context context, List<Explore> explores) {
        mContext = context;
        exploreList = explores;
    }

    @Override
    public ExploreViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.my_recipe_card_layout, null);
        return new ExploreViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(ExploreViewAdapter holder, int position) {
        Explore explore = exploreList.get(position);

        holder.recipeName.setText(explore.getName());
        holder.recipeTime.setText(explore.getMinute());
        holder.recipeLike.setText(String.valueOf(explore.getLike()));
        holder.recipeChat.setText(String.valueOf(explore.getChat()));
        holder.recipeImage.setImageResource(explore.getImage());
    }

    @Override
    public int getItemCount() {
        return exploreList.size();
    }

    public class ExploreViewAdapter extends RecyclerView.ViewHolder {
        private ImageView recipeImage;
        private TextView recipeName;
        private TextView recipeTime;
        private TextView recipeLike;
        private TextView recipeChat;

        public ExploreViewAdapter(View itemView) {
            super(itemView);

            recipeImage = (ImageView) itemView.findViewById(R.id.recipeImage);
            recipeName = (TextView) itemView.findViewById(R.id.recipeName);
            recipeTime = (TextView) itemView.findViewById(R.id.recipeTime);
            recipeLike = (TextView) itemView.findViewById(R.id.recipeLike);
            recipeChat = (TextView) itemView.findViewById(R.id.recipeChat);
        }


    }
}
