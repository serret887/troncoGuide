package com.troncoguide.androidapp.troncoguide.Utils;

import com.android.volley.toolbox.Volley;
import com.troncoguide.androidapp.troncoguide.BuildConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by Alejandro on 12/10/2015.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
@PowerMockIgnore({ "org.mockito.*", "org.robolectric.*", "android.*" })
@PrepareForTest(Volley.class)
public class SyncUtilsTest {
    @Test
    public void testCreateSyncAccount() throws Exception {

    }

    @Test
    public void testTriggerSync() throws Exception {


    }
}