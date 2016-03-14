package com.troncoguide.androidapp.troncoguide.ContentProvider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.suitebuilder.annotation.MediumTest;

import com.troncoguide.androidapp.troncoguide.BuildConfig;
import com.troncoguide.androidapp.troncoguide.ContentProvider.Contract.DatabaseContract;
import com.troncoguide.androidapp.troncoguide.TestUtils.ContentProviderUtil;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowContentResolver;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
@MediumTest
public class ContentProviderDataBaseUnitTest {

    private ContentValues[] cvMovie;
    private Activity activity;
    private TroncoProvider mProvider;
    private ContentResolver mContentResolver;

    @Before
    public void setUp() throws Exception {
        cvMovie = ContentProviderUtil.dummyContentValuesOfMovieDiscoveryArray();
        activity = new Activity();
        mProvider = new TroncoProvider();
        mContentResolver = activity.getContentResolver();

        mProvider.onCreate();
        ShadowContentResolver.registerProvider(DatabaseContract.CONTENT_AUTHORITY, mProvider);
    }

    @Test
    public void testInsert() throws Exception {

        mContentResolver.insert(DatabaseContract.MovieEntry.CONTENT_MOVIE_URI, cvMovie[0]);
        Cursor c = mContentResolver.query(DatabaseContract.MovieEntry.CONTENT_MOVIE_URI, null, null, null, null);
        c.moveToFirst();
        ContentProviderUtil.assertDiscoveryData(c, cvMovie[0]);
    }

    @Test
    public void testInsert_WithGarbishUri() throws Exception {
        mContentResolver.insert(Uri.parse("content://com.troncoguide.androidapp.provider/4movies4/1"), cvMovie[0]);
        Cursor c = mContentResolver.query(DatabaseContract.MovieEntry.CONTENT_MOVIE_URI, null, null, null, null);
        c.moveToFirst();
        Assert.assertEquals(0, c.getCount());
    }

    @Test
    public void testInsert_EqualDataDontIncrementTheDatabaseRows() throws Exception {
        mContentResolver.bulkInsert(DatabaseContract.MovieEntry.CONTENT_MOVIE_URI, cvMovie);
        //   mContentResolver.bulkInsert(DatabaseContract.MovieEntry.CONTENT_MOVIE_URI, ContentProviderUtil.cvMovie);
        mContentResolver.insert(DatabaseContract.MovieEntry.CONTENT_MOVIE_URI, cvMovie[0]);
        mContentResolver.bulkInsert(DatabaseContract.MovieEntry.CONTENT_MOVIE_URI, cvMovie);
        Cursor c = mContentResolver.query(DatabaseContract.MovieEntry.CONTENT_MOVIE_URI, null, null, null, null);
        ContentProviderUtil.assertDiscoveryData(c, cvMovie);

    }

    @Test
    public void testInsert_EqualTitlesUpdateTheRowtothelastEntry() throws Exception {
        mContentResolver.bulkInsert(DatabaseContract.MovieEntry.CONTENT_MOVIE_URI, cvMovie);
        //modifie the data
        ContentValues[] new_cv = cvMovie;
        new_cv[0].put(DatabaseContract.MovieEntry.COLUMN_VOTE_AVG, 100.6);
        //inserting the new data
        mContentResolver.bulkInsert(DatabaseContract.MovieEntry.CONTENT_MOVIE_URI, new_cv);
        Cursor c = mContentResolver.query(DatabaseContract.MovieEntry.CONTENT_MOVIE_URI, null, null, null, null);
        ContentProviderUtil.assertDiscoveryData(c, new_cv);
    }

    @Test
    public void testBulkInsert() throws Exception {
        int count = mContentResolver.bulkInsert(DatabaseContract.MovieEntry.CONTENT_MOVIE_URI, cvMovie);
        Cursor c = mContentResolver.query(DatabaseContract.MovieEntry.CONTENT_MOVIE_URI, null, null, null, null);
        ContentProviderUtil.assertDiscoveryData(c, cvMovie);
    }

    @Test
    public void testRetrive_CorrectTypeDir() throws Exception {
        String actual = mContentResolver.getType(DatabaseContract.MovieEntry.CONTENT_MOVIE_URI);
        Assert.assertEquals("vnd.android.cursor.dir/vnd.movies.entries", actual);
    }

    @Test
    public void testRetrive_CorrectTypeItem() throws Exception {
        String actual = mContentResolver.getType(DatabaseContract.MovieEntry.CONTENT_MOVIE_URI.buildUpon().appendPath("1").build());
        Assert.assertEquals("vnd.android.cursor.item/vnd.movies.entry", actual);
    }

    @Test
    public void testRetrive_IncorrectTypeItem() throws Exception {
        String actual = mContentResolver.getType(DatabaseContract.MovieEntry.CONTENT_MOVIE_URI.buildUpon().appendPath("z1").build());
        Assert.assertEquals("Not Match", actual);
    }

    @Test
    public void testDeleteSingleRow() throws Exception {
        mContentResolver.bulkInsert(DatabaseContract.MovieEntry.CONTENT_MOVIE_URI, cvMovie);

        mContentResolver.delete(Uri.parse("content://com.troncoguide.androidapp.provider/movies/1"), null, null);

        Cursor c = mContentResolver.query(DatabaseContract.MovieEntry.CONTENT_MOVIE_URI, null, null, null, null);
        Assert.assertEquals(cvMovie.length-1, c.getCount());

    }

    @Test
    public void testDeleteAllRows() throws Exception {
        mContentResolver.bulkInsert(DatabaseContract.MovieEntry.CONTENT_MOVIE_URI, cvMovie);

        mContentResolver.delete(DatabaseContract.MovieEntry.CONTENT_MOVIE_URI, null, null);

        Cursor c = mContentResolver.query(DatabaseContract.MovieEntry.CONTENT_MOVIE_URI, null, null, null, null);
        Assert.assertEquals(0, c.getCount());
    }


    @Test
    public void testQueryForSpecificRows() throws Exception {
        mContentResolver.bulkInsert(DatabaseContract.MovieEntry.CONTENT_MOVIE_URI, cvMovie);
        String[] selectionArgs = new String[]{"1786666"};
        Cursor c = mContentResolver.query(DatabaseContract.MovieEntry.CONTENT_MOVIE_URI, null, DatabaseContract.MovieEntry.COLUMN_VOTE_COUNT, selectionArgs, null);
        c.moveToFirst();
        ContentProviderUtil.assertDiscoveryData(c, cvMovie[0]);
    }

    @Test
    public void testQueryForSpecificItem() throws Exception {
        mContentResolver.bulkInsert(DatabaseContract.MovieEntry.CONTENT_MOVIE_URI, cvMovie);
        Cursor c = mContentResolver.query(Uri.parse("content://com.troncoguide.androidapp.provider/movies/1"), null, null, null, null);
        c.moveToFirst();
        ContentProviderUtil.assertDiscoveryData(c, cvMovie[0]);

    }

    @After
    public void tearDown() throws Exception {

        mContentResolver.delete(DatabaseContract.MovieEntry.CONTENT_MOVIE_URI, null, null);

    }
}
