package com.troncoguide.androidapp.troncoguide.Utils.Tabs;


import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.troncoguide.androidapp.troncoguide.Fragments.DiscoveryFragment.DiscoveryFragment;
import com.troncoguide.androidapp.troncoguide.Fragments.MostPopular.MostPopularFragment;
import com.troncoguide.androidapp.troncoguide.R;


/**
 * Created by Jeremy on 11/10/2015.
 */
public class MoviePagerAdapter extends FragmentPagerAdapter {
    private static final int NEWS = 0;
    private  final int MOST_POPULAR = 1;
    private static final int HISTORY = 2;
    private  final String TAG = "PAGER_ADAPTER";

    private final String[] mtabs;



    public MoviePagerAdapter(FragmentManager fm, Resources r) {
        super(fm);
        mtabs = r.getStringArray(R.array.tabs_array);
    }


    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
switch (position){
    case NEWS:
        return DiscoveryFragment.newInstance();
    case MOST_POPULAR:
        return MostPopularFragment.newInstance();
    default:return null;
}
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        Log.d(TAG, "getCount() returned: " + 3);
        return mtabs.length;
    }

    /**
     * This method may be called by the ViewPager to obtain a title string
     * to describe the specified page. This method may return null
     * indicating no title for this page. The default implementation returns
     * null.
     *
     * @param position The position of the title requested
     * @return A title for the requested page
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return mtabs[position];
    }
}
