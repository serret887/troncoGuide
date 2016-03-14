package com.troncoguide.androidapp.troncoguide.UI;

import android.support.v7.widget.RecyclerView;

import com.troncoguide.androidapp.troncoguide.BuildConfig;
import com.troncoguide.androidapp.troncoguide.MainActivity;
import com.troncoguide.androidapp.troncoguide.R;
import com.troncoguide.androidapp.troncoguide.Utils.Tabs.SlidingTabLayout;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by Alejandro on 12/10/2015.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21,packageName = "com.troncoguide.androidapp.troncoguide",assetDir = "src/test/assets")
public class MainActivityTest {

    private MainActivity mainActivity;

    @Before
    public void setUp() throws Exception {
        mainActivity = Robolectric.buildActivity(MainActivity.class).create().resume().visible().get();;

    }

    @Test
    public void shouldhaveDiscoveryFragment() throws Exception {
//Given I am in the main activity
//        Then I see the assertDiscoveryData Fragment news list
        RecyclerView list = (RecyclerView) mainActivity.findViewById(R.id.news_list);
        Assert.assertNotNull(list);
    }
    @Test
    public void shouldhaveViewPager() throws Exception {
//Given I am in the main activity
//I can see the tabs
        SlidingTabLayout tabs = (SlidingTabLayout) mainActivity.findViewById(R.id.tabs);
        Assert.assertNotNull(tabs);
    }

    @Test
    public void testKeepTheStateWhenConfigurationChange() throws Exception {
        //Given I am in the Main Activity
        MainActivity initial = mainActivity;
        //And the configuration Change
mainActivity.recreate();
        //Then I get the same MainActivity
        Assert.assertSame(initial,mainActivity);
    }

    @After
    public void tearDown() throws Exception {

    }
}