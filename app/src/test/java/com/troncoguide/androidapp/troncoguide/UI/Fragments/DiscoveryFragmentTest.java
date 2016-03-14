package com.troncoguide.androidapp.troncoguide.UI.Fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.troncoguide.androidapp.troncoguide.BuildConfig;
import com.troncoguide.androidapp.troncoguide.Fragments.DiscoveryFragment.DiscoveryFragment;
import com.troncoguide.androidapp.troncoguide.MainActivity;
import com.troncoguide.androidapp.troncoguide.R;

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
@Config(constants = BuildConfig.class, sdk = 21,packageName = "com.troncoguide.androidapp.troncoguide")
public class DiscoveryFragmentTest {

        private static final String FRAGMENT_TAG = "discoveryFragment";

        FragmentActivity activity;
        DiscoveryFragment customFragment;

        @Before
        public void setUp() throws Exception {
            activity = Robolectric.buildActivity(MainActivity.class).create().start().resume().visible().get();;
            customFragment = new DiscoveryFragment();
            addFragment(activity, customFragment);
        }

        @Test
        public void shouldRestoreFragmentWhenHostActivityIsRecreated() throws Exception {
            activity.recreate();
            DiscoveryFragment recreatedFragment =(DiscoveryFragment) activity.getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);

            Assert.assertNotNull(recreatedFragment);
            Assert.assertSame(customFragment, recreatedFragment);
        }

    @Test
    public void testdiscoveryFragmentHastheList() throws Exception {
        View fragmentView = customFragment.getView();
        RecyclerView rv = (RecyclerView) fragmentView.findViewById(R.id.news_list);
        Assert.assertNotNull("should have a list xml",rv);

    }

    private void addFragment(FragmentActivity activity, Fragment fragment) {
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(fragment, FRAGMENT_TAG);
            transaction.commit();
        }
    }


