package com.troncoguide.androidapp.troncoguide;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.troncoguide.androidapp.troncoguide.Fragments.DiscoveryFragment.DiscoveryFragment;
import com.troncoguide.androidapp.troncoguide.Fragments.MostPopular.Adapter.MostPopularAdapter;
import com.troncoguide.androidapp.troncoguide.Fragments.MostPopular.MostPopularFragment;
import com.troncoguide.androidapp.troncoguide.Utils.Tabs.MoviePagerAdapter;
import com.troncoguide.androidapp.troncoguide.Utils.Tabs.SlidingTabLayout;


public class MainActivity extends AppCompatActivity
        implements
        NavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener, View.OnClickListener {

    private static final String TAG = "MAIN_ACT";
    private static final int BY_NAME = 1;
    private static final int BY_RATING = 2;
    private static final int COMEDY = 3;
    private static final int TERROR = 4;
    private static final int ACTION = 5;
    private static final int ANIMATE = 6;
    private static final int SCIFY = 7;
    private ViewPager mpager;
    private DrawerLayout mdrawer;
    private FloatingActionButton sortNews;
    private FloatingActionButton filterButton;
    private MoviePagerAdapter pageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setting the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupDrawer(toolbar);
        setupTabs();
        createFloatingButton();


    }


    //setup methods
    private void setupDrawer(Toolbar toolbar) {
        //setup the drawer
        if (mdrawer == null) {
            Log.d(TAG, "first time instantiating the drawer");
            mdrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, mdrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            mdrawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            Log.d(TAG, "setupDrawer() finished");
        }
    }

    private void setupTabs() {
        Log.i(TAG, "setupTabs: ");
        //setting the pager
        if (mpager == null) {
            mpager = (ViewPager) findViewById(R.id.pager_container);

            // TODO: 11/18/2015 cambiar los colores apariencia es importante

            SlidingTabLayout mslidingTabLayout = (SlidingTabLayout) findViewById(R.id.tabs);
            mslidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
                @Override
                public int getIndicatorColor(int position) {
                    // TODO: 12/11/2015 change for one that use the color
                    return getResources().getIntArray(R.array.tabs_color_array)[position];
                }
            });
            mslidingTabLayout.setDistributeEvenly(true);
            pageAdapter = new MoviePagerAdapter(getSupportFragmentManager(), getResources());
            mpager.setAdapter(pageAdapter);
            mslidingTabLayout.setViewPager(mpager);

            mpager.addOnPageChangeListener(this);
            Log.d(TAG, "setupTabs() Finished");
        }
    }

    @Override
    public void onBackPressed() {

        if (mdrawer.isDrawerOpen(GravityCompat.START)) {
            mdrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
//            int a = getContentResolver().delete(MovieGuideDataContract.MovieEntry.CONTENT_MOVIE_URI,null,null);
//            Toast.makeText(getApplicationContext(),String.valueOf(a),Toast.LENGTH_LONG).show();
////            getLoaderManager().restartLoader(1,null,null);
        } else if (id == R.id.nav_gallery) {


        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }


        mdrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                sortNews.setVisibility(View.VISIBLE);
                filterButton.setVisibility(View.GONE);
                break;
            case 1:
                filterButton.setVisibility(View.VISIBLE);
                sortNews.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void createFloatingButton() {

        //folating button
        ImageView iconGenre = new ImageView(this); // Create an icon
        iconGenre.setImageResource(R.drawable.ic_search_black_24dp);

        filterButton = new FloatingActionButton.Builder(this)
                .setContentView(iconGenre)
                .build();

        ImageView imgComedy = new ImageView(this);
        imgComedy.setImageResource(R.drawable.ic_child_care_black_24dp);

        ImageView imgTerror = new ImageView(this);
        imgTerror.setImageResource(R.drawable.ic_menu_manage);

        ImageView imgAction = new ImageView(this);
        imgAction.setImageResource(R.drawable.ic_directions_run_black_24dp);

        ImageView imgAnimate= new ImageView(this);
        imgAnimate.setImageResource(R.drawable.ic_tag_faces_black_24dp);

        ImageView imgSciFy = new ImageView(this);
        imgSciFy.setImageResource(R.drawable.ic_android_black_24dp);

        //sub action menu
        SubActionButton.Builder itemBuilder1 = new SubActionButton.Builder(this);
        SubActionButton comedy= itemBuilder1.setContentView(imgComedy).build();
        SubActionButton terror = itemBuilder1.setContentView(imgTerror).build();
        SubActionButton animate= itemBuilder1.setContentView(imgAnimate).build();
        SubActionButton action = itemBuilder1.setContentView(imgAction).build();
        SubActionButton sciFy = itemBuilder1.setContentView(imgSciFy).build();

        comedy.setOnClickListener(this);
        terror.setOnClickListener(this);
        animate.setOnClickListener(this);
        action.setOnClickListener(this);
        sciFy.setOnClickListener(this);

        comedy.setTag(COMEDY);
        terror.setTag(TERROR);
        animate.setTag(ANIMATE);
        action.setTag(ACTION);
        sciFy.setTag(SCIFY);

        FloatingActionMenu actionMenu1 = new FloatingActionMenu.Builder(this)
                .addSubActionView(comedy)
                .addSubActionView(action)
              //  .addSubActionView(terror)
                .addSubActionView(sciFy)
                .addSubActionView(animate)
                .attachTo(filterButton)
                .build();

        //        folating button
        ImageView icon = new ImageView(this); // Create an icon
        icon.setImageResource(R.drawable.ic_details_black_24dp);

        sortNews = new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .build();

        ImageView sortName = new ImageView(this);
        sortName.setImageResource(R.drawable.ic_format_underlined_black_24dp);

        ImageView sortRating = new ImageView(this);
        sortRating.setImageResource(R.drawable.ic_star_black_24dp);
        //sub action menu
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        SubActionButton sortByName = itemBuilder.setContentView(sortName).build();
        SubActionButton sortByRating = itemBuilder.setContentView(sortRating).build();

        sortByName.setOnClickListener(this);
        sortByRating.setOnClickListener(this);

        sortByName.setTag(BY_NAME);
        sortByRating.setTag(BY_RATING);

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(sortByName)
                .addSubActionView(sortByRating)
                .attachTo(sortNews)
                .build();


    }

    @Override
    public void onClick(View v) {
        if (v.getTag().equals(BY_NAME)) {
            DiscoveryFragment discovery = (DiscoveryFragment) pageAdapter.instantiateItem(mpager, mpager.getCurrentItem());
            discovery.sort("byName");
        }
        if (v.getTag().equals(BY_RATING)) {
            DiscoveryFragment discovery = (DiscoveryFragment) pageAdapter.instantiateItem(mpager, mpager.getCurrentItem());
            discovery.sort("byRating");
        }
        if (v.getTag().equals(COMEDY)) {
            MostPopularFragment mostPopular= (MostPopularFragment) pageAdapter.instantiateItem(mpager, mpager.getCurrentItem());
           mostPopular.fetch("comedy");
        }
        if (v.getTag().equals(ACTION)) {
            MostPopularFragment mostPopular= (MostPopularFragment) pageAdapter.instantiateItem(mpager, mpager.getCurrentItem());
            mostPopular.fetch("action");
        }
        if (v.getTag().equals(SCIFY)) {
            MostPopularFragment mostPopular= (MostPopularFragment) pageAdapter.instantiateItem(mpager, mpager.getCurrentItem());
            mostPopular.fetch("sciFy");
        }
        if (v.getTag().equals(ANIMATE)) {
            MostPopularFragment mostPopular= (MostPopularFragment) pageAdapter.instantiateItem(mpager, mpager.getCurrentItem());
            mostPopular.fetch("drama");

        }
        if (v.getTag().equals(TERROR)) {
            MostPopularFragment mostPopular= (MostPopularFragment) pageAdapter.instantiateItem(mpager, mpager.getCurrentItem());
            mostPopular.fetch("terror");
        }


    }
}