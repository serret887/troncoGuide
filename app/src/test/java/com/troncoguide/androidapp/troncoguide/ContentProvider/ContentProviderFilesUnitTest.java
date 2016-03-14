package com.troncoguide.androidapp.troncoguide.ContentProvider;

import android.app.Activity;
import android.content.ContentResolver;
import android.net.Uri;

import com.troncoguide.androidapp.troncoguide.BuildConfig;
import com.troncoguide.androidapp.troncoguide.ContentProvider.Contract.DatabaseContract;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowContentResolver;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;

/**
 * Created by Alejandro on 12/12/2015.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, packageName = "com.troncoguide.androidapp.troncoguide", assetDir = "src/test/assets")
public class ContentProviderFilesUnitTest {
    private File file;
    private Activity activity;
    private TroncoProvider mProvider;
    private ContentResolver mContentResolver;

    @Before
    public void setUp() throws Exception {
        activity = new Activity();
        mProvider = new TroncoProvider();
        mContentResolver = activity.getContentResolver();

        mProvider.onCreate();
        ShadowContentResolver.registerProvider(DatabaseContract.CONTENT_AUTHORITY, mProvider);
    }

    @Test
    public void testGivenURICorrectImagereturned() throws Exception {

        file = new File("src/test/assets/europe1870.jpg");
        Uri uri = Uri.fromFile(file);

        InputStream stream = mContentResolver.openInputStream(uri);

        BufferedInputStream bufferedInputStream = new BufferedInputStream(stream);

        int line ;
        while((line = bufferedInputStream.read())> 0){
//            Asse
        };
    }

    @Test
    public void testInsertImageWork() throws Exception {

//        mContentResolver.openO

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }


}
