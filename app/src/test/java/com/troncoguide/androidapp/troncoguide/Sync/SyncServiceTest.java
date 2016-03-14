package com.troncoguide.androidapp.troncoguide.Sync;

import android.app.Activity;
import android.content.Intent;

import org.junit.After;
import org.junit.Before;

/**
 * Created by root on 1/6/16.
 */
public class SyncServiceTest {

    @Before
    public void setUp() throws Exception {
        Activity mActivity = new Activity();
        mActivity.startService(new Intent());
//        Intent receivedIntent = shadowOf(mActivity).getNextStartedService();

    }

    @After
    public void tearDown() throws Exception {

    }
}