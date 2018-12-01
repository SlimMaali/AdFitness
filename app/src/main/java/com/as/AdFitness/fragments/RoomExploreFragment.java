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



/**
 * A simple {@link Fragment} subclass.
 */
public class RoomExploreFragment extends Fragment {


    //The current position of the menu title
    private int position = 0;

    private DashboardActivity dashboardActivity;



    public RoomExploreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_room, container, false);

        String roomTitle=getArguments().getString("Title");
        String roomDescription=getArguments().getString("Description");
        String roomImage=getArguments().getString("Image");


        TextView roomDescriptionField = (TextView) view.findViewById(R.id.roomDescription);
        roomDescriptionField.setText(roomDescription);


        int resID = getResources().getIdentifier(roomImage , "drawable", getActivity().getPackageName());

        ImageView roomImageView = (ImageView) view.findViewById(R.id.exploreImages);
        roomImageView.setImageResource(resID);
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
}
