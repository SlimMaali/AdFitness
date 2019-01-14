package com.as.AdFitness.fragments;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.as.AdFitness.DashboardActivity;
import com.as.AdFitness.R;
import com.as.AdFitness.adapters.SlidingImage_Adapter;
import com.as.AdFitness.entities.ImageModel;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class RoomExploreFragment extends Fragment {


    //The current position of the menu title
    private int position = 0;

    private DashboardActivity dashboardActivity;


    //Slider

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<ImageModel> imageModelArrayList;

    /* private int[] myImageList = new int[]{R.drawable.harley2, R.drawable.benz2,
           R.drawable.vecto,R.drawable.webshots
            ,R.drawable.bikess,R.drawable.img1};

*/
    private int[] myImageList ;


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

        myImageList = new int[]{getResources().getIdentifier(roomImage+"1" , "drawable", getActivity().getPackageName()),
                getResources().getIdentifier(roomImage+"2" , "drawable", getActivity().getPackageName()),
                getResources().getIdentifier(roomImage+"3", "drawable", getActivity().getPackageName())};

        int resID = getResources().getIdentifier(roomImage , "drawable", getActivity().getPackageName());



        imageModelArrayList = new ArrayList<>();
        imageModelArrayList = populateList();

        init(view);

        return view;
    }


    private ArrayList<ImageModel> populateList(){

        ArrayList<ImageModel> list = new ArrayList<>();

        for(int i = 0; i < 3; i++){
            ImageModel imageModel = new ImageModel();
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }

        return list;
    }

    private void init(View v) {

        mPager = (ViewPager) v.findViewById(R.id.pager);
        CirclePageIndicator indicator = (CirclePageIndicator)
                v.findViewById(R.id.indicator);
        mPager.setAdapter(new SlidingImage_Adapter(getContext(),imageModelArrayList));



        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

        //Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES =imageModelArrayList.size();


        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

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
