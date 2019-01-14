package com.as.AdFitness;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.as.AdFitness.fragments.CoachyFragment;
import com.as.AdFitness.fragments.OurClubFragment;
import com.as.AdFitness.fragments.HomeFragment;
import com.as.AdFitness.fragments.MyCoursesFragment;
import com.as.AdFitness.fragments.ProfileFragment;
import com.as.AdFitness.fragments.ScheduleFragment;
import com.as.AdFitness.fragments.ShopFragment;
import com.as.AdFitness.entities.Profile;
import com.as.AdFitness.entities.User;
import com.as.AdFitness.utility.Api;
import com.as.AdFitness.service.ProfileService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView navigationView;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static User loggedUser ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Don't add another fragment on top of what we already had
        if (savedInstanceState != null) {
            return;
        }
        loggedUser = (User)getIntent().getParcelableExtra("user");

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigationView);

        navigationView.setNavigationItemSelectedListener(this);
        final ImageView iv = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.ivUser_drawer_header);
        TextView tv = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tvName);
        final TextView tvWeight = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tvWeight_drawer_header);
        final TextView tvHeight = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tvHeight_drawer_header);
        LinearLayoutCompat LL = (LinearLayoutCompat) navigationView.getHeaderView(0).findViewById(R.id.User_drawer_header);
        final ProfileService Ps = Api.getInstance().getProfileService();


        tv.setText(loggedUser.getLastName()+" "+loggedUser.getFirstName());

        Call<Profile> call = Ps.getProfile(loggedUser.getId());
        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                //Log.e("res",response.body().toString());
                if(response.body().getUser()==null)
                {
                    Intent profileIn;
                    profileIn = new Intent(DashboardActivity.this,NewProfileActivity.class);
                    profileIn.putExtra("user",loggedUser);
                    startActivity(profileIn);
                    finish();
                }
                else {
                    Profile p = response.body();
                    Picasso.with(getApplicationContext()).load(p.getImage()).into(iv);
                    tvWeight.setText(p.getWeight()+" KG");
                    tvHeight.setText(p.getHeight()+" M");
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {

            }
        });



        //This is the guy that response to drawer toggle
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open,
                R.string.drawer_close){

            @Override
            public void onDrawerClosed(View drawerView) {
                // TODO Auto-generated method stub
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // TODO Auto-generated method stub
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };

        drawerLayout.addDrawerListener(drawerToggle);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        HomeFragment homeFragment = new HomeFragment();
        fragmentTransaction.add(R.id.container, homeFragment);
        fragmentTransaction.addToBackStack("stack");
        fragmentTransaction.commit();

        LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Profile> call = Ps.getProfile(loggedUser.getId());
                call.enqueue(new Callback<Profile>() {
                    @Override
                    public void onResponse(Call<Profile> call, Response<Profile> response) {
                        if(response.body().getUser()!=null)
                        {
                            ProfileFragment p = new ProfileFragment();
                            Bundle b = new Bundle();
                            b.putParcelable("profile",response.body());
                            p.setArguments(b);
                            replaceFragment(p);
                            drawerLayout.closeDrawer(GravityCompat.START);
                        }
                    }
                    @Override
                    public void onFailure(Call<Profile> call, Throwable t) {

                    }
                });
            }
        });



        sharedPreferences = getSharedPreferences("AdFitness",MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        if (getSupportFragmentManager().getBackStackEntryCount() > 0 ){
            getSupportFragmentManager().popBackStack();
        }
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_dashboard, menu);

        // Associate searchable configuration with the SearchView
        final SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if( !searchView.isIconified() ) {
                    searchView.setIconified(true);
                }
                //searchView.onActionViewCollapsed();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                //Toast.makeText(DashboardActivity.this, "Query Changed: "+s, Toast.LENGTH_LONG).show();
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        String title = "Accueil";
        int id = item.getItemId();
        navigationView.setCheckedItem(id);
        if (id == R.id.action_home) {
            fragment = new HomeFragment();
        } else if (id == R.id.action_our_club) {
            title = "Notre Club";
            fragment = new OurClubFragment();
        }else if (id == R.id.action_our_courses) {
            title = "Nos Cours";
            fragment = new ScheduleFragment();
        } else if (id == R.id.action_my_courses) {
            title = "Mes Cours";
            fragment = new MyCoursesFragment();
        } else if (id == R.id.action_live_training) {
            title = "Coachy Live";
            fragment = new CoachyFragment();
        } else if (id == R.id.action_setting) {
            title = "Settings";
            fragment = new ShopFragment();
        }else if (id == R.id.action_logout) {
            title = "Déconnexion";
            editor.remove("id");
            editor.remove("user");
            editor.remove("password");
            editor.remove("status");
            editor.apply();

            Intent loginscreen = new Intent(this,LoginActivity.class);
            loginscreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(loginscreen);
            this.finish();
        }

        if (fragment != null) {
            getSupportActionBar().setTitle(title);
            replaceFragment(fragment);
        }

        //This closes the opened drawer
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }


    public void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        if (manager != null){
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            Fragment currentFrag = manager.findFragmentById(R.id.container);

            //This checks if fragment is the same, if yes don't add to back stack, no add to back stack
            if (currentFrag != null && currentFrag.getClass().equals(fragment.getClass())) {
                fragmentTransaction.replace(R.id.container, fragment).commit();
            } else {
                fragmentTransaction.replace(R.id.container, fragment).addToBackStack(null).commit();
            }
        }
    }

}
