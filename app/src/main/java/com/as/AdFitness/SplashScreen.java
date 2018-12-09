package com.as.AdFitness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.as.AdFitness.fragments.JoinInfographicFragment;
import com.as.AdFitness.fragments.WelcomeInfographicFragment;
import com.as.AdFitness.pojo.User;
import com.as.AdFitness.utility.Api;
import com.as.AdFitness.utility.UserService;
import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;
import com.rd.draw.data.Orientation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sharedPreferences = getSharedPreferences("AdFitness",MODE_PRIVATE);
        if(sharedPreferences.getString("status","false").equals("logged"))
        {
            UserService userService = Api.getInstance().getUserService();
            Call<User> call = userService.getUserData(sharedPreferences.getInt("id",0));
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User u = response.body();

                    Intent loggedIn;
                    if (u.getRole().equals("admin"))loggedIn = new Intent(SplashScreen.this, AdminHomeActivity.class);
                    else loggedIn = new Intent(SplashScreen.this, DashboardActivity.class);

                    loggedIn.putExtra("user",u);
                    startActivity(loggedIn);
                    finish();
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    startActivity(new Intent(SplashScreen.this,LoginActivity.class));
                }
            });

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        initViews();
    }

    /**
     * Initialize views used in this activity
     */
    private void initViews() {
        ScreenSlidePagerAdapter adapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(adapter);

        PageIndicatorView pageIndicatorView = (PageIndicatorView) findViewById(R.id.pageIndicatorView);
        pageIndicatorView.setViewPager(pager);

        pageIndicatorView.setAnimationType(AnimationType.SWAP);
        pageIndicatorView.setOrientation(Orientation.HORIZONTAL);
        pageIndicatorView.setAutoVisibility(true);



        findViewById(R.id.skipButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                SplashScreen.this.finish();
            }
        });

    }


    /**
     * A simple pager adapter that represents 3 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new WelcomeInfographicFragment();//Share page info graphic
                    break;
                case 1:
                    fragment = new JoinInfographicFragment();//Explore page info graphic
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;//No of fragment created
        }
    }


}
