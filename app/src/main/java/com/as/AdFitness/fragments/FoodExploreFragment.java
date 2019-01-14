package com.as.AdFitness.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.as.AdFitness.DashboardActivity;
import com.as.AdFitness.R;
import com.as.AdFitness.pojo.Explore;
import com.as.AdFitness.adapters.ExploreAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodExploreFragment extends Fragment {
    //Sliding menu titles at the top of the explore fragment
    private String[] foodTitle = new String[]{"Sweet", "Salad", "Fish", "Soup", "Burger", "Amala", "Drink"};

    //Images for each menu titles
    private int[] foodImage = new int[] {R.drawable.food_explore1, R.drawable.recipe_1, R.drawable.recipe_2,
                R.drawable.recipe_3, R.drawable.food_explore1, R.drawable.recipe_1, R.drawable.recipe_3};


    private int[] exploreImage = new int[] {R.drawable.recipe_1, R.drawable.recipe_3, R.drawable.recipe_2};
    private String[] exploreFoodName = new String[] {"Jhonny Waffles with Vanila", "Amala with Ewedu Soap", "Oustrich Spleculando"};

    //The current position of the menu title
    private int position = 0;

    private ExploreAdapter adapter;
    private DashboardActivity dashboardActivity;
    private List<Explore> exploreList;


    public FoodExploreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sweet, container, false);

        TextView foodTitleField = (TextView) view.findViewById(R.id.foodTitle);
        foodTitleField.setText(foodTitle[position]);

        ImageView foodImageView = (ImageView) view.findViewById(R.id.exploreImages);
        foodImageView.setImageResource(foodImage[position]);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);

        exploreList = new ArrayList<>();
        adapter = new ExploreAdapter(dashboardActivity, exploreList);

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(dashboardActivity.getApplicationContext()));
        recyclerView.setAdapter(adapter);


        populateView();

        return view;
    }

    /**
     * This gets the current position the user tap on
     * @param position
     */
    public void getPosition(int position) {
        this.position = position;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dashboardActivity = (DashboardActivity) context;
    }

    private void populateView() {
        Random random = new Random();
        for (int i = 0; i < exploreFoodName.length; i++) {
            int randomMinute = random.nextInt(60);
            int randomNo = random.nextInt(1000);
            Explore explore = new Explore();
            explore.setChat(randomNo);
            explore.setImage(exploreImage[i]);
            explore.setLike(randomNo);
            explore.setMinute(String.valueOf(randomMinute));
            explore.setName(exploreFoodName[i]);

            exploreList.add(explore);
            adapter.notifyDataSetChanged();
        }
    }
}
