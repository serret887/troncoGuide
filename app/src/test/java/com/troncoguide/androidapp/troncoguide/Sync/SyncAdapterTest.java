package com.troncoguide.androidapp.troncoguide.Sync;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import com.troncoguide.androidapp.troncoguide.BuildConfig;
import com.troncoguide.androidapp.troncoguide.ContentProvider.Contract.DatabaseContract;
import com.troncoguide.androidapp.troncoguide.Network.VolleyHttpMock;
import com.troncoguide.androidapp.troncoguide.Sync.Mock.SaveDataMock;
import com.troncoguide.androidapp.troncoguide.TestUtils.NetworkUtils;
import com.troncoguide.androidapp.troncoguide.Utils.Network.URLBuilderUtil;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.net.URL;
import java.util.concurrent.CountDownLatch;

import static org.mockito.Mockito.when;

/**
 * Created by root on 1/6/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*"})
@PrepareForTest({Volley.class, URLBuilderUtil.class})
public class SyncAdapterTest {
    private static final String TAG = "Sync_Adapter_Test";
    @Rule
    public PowerMockRule rule = new PowerMockRule();
    private MockWebServer server;
    private SyncAdapter mSyncAdapter;
    private String discovery_response;
    private CountDownLatch signal;

    @Before
    public void setUp() throws Exception {
        signal = new CountDownLatch(1);

        PowerMockito.mockStatic(URLBuilderUtil.class);
        PowerMockito.mockStatic(Volley.class);
        Context c = RuntimeEnvironment.application;
        RequestQueue requestQueue = VolleyHttpMock.newRequestQueueForTest(RuntimeEnvironment.application);
        when(Volley.newRequestQueue(RuntimeEnvironment.application))
                .thenReturn(requestQueue);
        server = new MockWebServer();
        discovery_response = NetworkUtils.readDiscoveryResponse();

        server.play();
        URL baseUrl = server.getUrl("/");
        when(URLBuilderUtil.discoveryURLConstructor(1)).thenReturn(baseUrl + "discover/movie?page=1");

        MockResponse mockResponse = new MockResponse();
        mockResponse.setBody(NetworkUtils.readDiscoveryResponse());
        server.enqueue(mockResponse);
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

    /**
     * this test is for know if onPerform Sync make a server request and if it trigger the onResponse of
     * my implementation of SaveDatacALLBACK
     * @throws Exception
     */
    @Test
    public void testOnPerformSyncTriggerARestApiCall_andCallingOnResponseMethod_toDiscoveryPage() throws Exception {
        mSyncAdapter = new SyncAdapter(RuntimeEnvironment.application, false, new SaveDataMock(signal));
        Account testAccount = new Account("test", "testing.Sync.Adapter");
        Bundle b = new Bundle();
        b.putInt("DISCOVERY_PAGE", 1);
        mSyncAdapter.onPerformSync(testAccount, b, DatabaseContract.CONTENT_AUTHORITY, null, null);

        RecordedRequest request = server.takeRequest();
        Log.d(TAG, "testOnPerformSyncTriggerARestApiCall: " + request.toString());
        Assert.assertEquals("/discover/movie?page=1"
                , request.getPath());
        signal.await();
    }

}