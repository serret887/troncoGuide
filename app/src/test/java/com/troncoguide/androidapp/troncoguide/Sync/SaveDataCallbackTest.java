package com.troncoguide.androidapp.troncoguide.Sync;

import android.app.Activity;
import android.content.ContentProviderClient;
import android.database.Cursor;

import com.google.gson.Gson;
import com.troncoguide.androidapp.troncoguide.BuildConfig;
import com.troncoguide.androidapp.troncoguide.ContentProvider.Contract.DatabaseContract;
import com.troncoguide.androidapp.troncoguide.ContentProvider.TroncoProvider;
import com.troncoguide.androidapp.troncoguide.Entity.MovieDiscovery;
import com.troncoguide.androidapp.troncoguide.Entity.ResponseTheMovieOrg;
import com.troncoguide.androidapp.troncoguide.TestUtils.NetworkUtils;
import com.troncoguide.androidapp.troncoguide.Utils.Network.Mapper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowContentResolver;

/**
 * Created by root on 1/6/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class SaveDataCallbackTest {

    private SaveDataCallback mSaveDataCallback;
    private MovieDiscovery[] discovery;
    private ContentProviderClient mContentResolver;

    @Before
    public void setUp() throws Exception {
        mSaveDataCallback = new SaveDataCallback();
        Gson gson = new Gson();
        String discovery_response = NetworkUtils.readDiscoveryResponse();
        ResponseTheMovieOrg response = gson.fromJson(discovery_response, ResponseTheMovieOrg.class);
        discovery = Mapper.discoveryResponseToMovieDiscovery(response);
        //setting up the content Provider for the test
        Activity activity = new Activity();
        TroncoProvider mProvider = new TroncoProvider();
        mContentResolver = activity.getContentResolver().acquireContentProviderClient(DatabaseContract.CONTENT_AUTHORITY);
        mProvider.onCreate();
        ShadowContentResolver.registerProvider(DatabaseContract.CONTENT_AUTHORITY, mProvider);
        mSaveDataCallback.setProvider(mContentResolver);
    }

    @Test
    public void testOnResponseGetAnArrayOfMoviesDiscoverys_DontThrowExeption() throws Exception {
        mSaveDataCallback.onResponse(discovery);
    }

    @Test
    public void testOnresponseDiscoverySaveTheDataUsingTheContentProvider() throws Exception {

///////
        mSaveDataCallback.onResponse(discovery);
////////
        Cursor c = mContentResolver.query(DatabaseContract.MovieEntry.CONTENT_MOVIE_URI,null,null,null,null);
        int expected = discovery.length;
        Assert.assertEquals("The amount of data most be equal", expected,c.getCount());
    }

    @Test
    public void testOnError() throws Exception {

    }
}