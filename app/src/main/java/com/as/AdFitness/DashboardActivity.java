package com.as.AdFitness;

import android.app.SearchManager;
import android.content.Context;
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
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.as.AdFitness.extra.ExtraOneFragment;
import com.as.AdFitness.extra.ExtraThreeFragment;
import com.as.AdFitness.extra.ExtraTwoFragment;
import com.as.AdFitness.fragments.ExploreFragment;
import com.as.AdFitness.fragments.HomeFragment;
import com.as.AdFitness.fragments.MyRecipeFragment;
import com.as.AdFitness.fragments.ShopFragment;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView navigationView;

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

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigationView);

        navigationView.setNavigationItemSelectedListener(this);

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
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

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
                //Toast.makeText(DashboardActivity.this, "Query: "+query, Toast.LENGTH_LONG).show();
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
        String title = "Home";
        int id = item.getItemId();
        navigationView.setCheckedItem(id);
        if (id == R.id.action_home) {
            title = "Accueil";
            fragment = new HomeFragment();
        } else if (id == R.id.action_our_club) {
            title = "Notre Club";
            fragment = new ExploreFragment();
        } else if (id == R.id.action_my_courses) {
            title = "Mes Cours";
            fragment = new MyRecipeFragment();
        } else if (id == R.id.action_setting) {
            title = "Settings";
            fragment = new ShopFragment();
        } else if (id == R.id.action_extra_1) {
            title = "Extra One";
           fragment = new ExtraOneFragment();
        } else if (id == R.id.action_extra_2) {
            title = "Extra Two";
            fragment = new ExtraTwoFragment();
        } else if (id == R.id.action_extra_3) {
            title = "Extra Three";
            fragment = new ExtraThreeFragment();
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
