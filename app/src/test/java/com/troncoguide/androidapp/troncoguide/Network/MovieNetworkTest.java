package com.troncoguide.androidapp.troncoguide.Network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import com.troncoguide.androidapp.troncoguide.BuildConfig;

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
 * Created by Alejandro on 12/8/2015.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*"})
@PrepareForTest({Volley.class, URLBuilderUtil.class})
public class MovieNetworkTest {
    @Rule
    public PowerMockRule rule = new PowerMockRule();
    private URL baseUrl;
    private MockWebServer server;
    private String discovery_response;

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(Volley.class);
        PowerMockito.mockStatic(URLBuilderUtil.class);

        Context c = RuntimeEnvironment.application;
        RequestQueue requestQueue = VolleyHttpMock.newRequestQueueForTest(RuntimeEnvironment.application);
        when(Volley.newRequestQueue(RuntimeEnvironment.application))
                .thenReturn(requestQueue);

        server = new MockWebServer();
        discovery_response = NetworkUtils.readDiscoveryResponse();

        server.play();
        baseUrl = server.getUrl("/");
        when(URLBuilderUtil.discoveryURLConstructor(1)).thenReturn(baseUrl + "discover/movie?page=1");

    }

    @Test
    public void testgetDiscoveryObject() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(discovery_response));

        CountDownLatch signal = new CountDownLatch(1);
        NetworkDiscoveryResponseHandlerIMPL impl = new NetworkDiscoveryResponseHandlerIMPL(signal);
        VolleyHttp volley = VolleyHttp.getInstance(RuntimeEnvironment.application);
        DiscoveryNetworkData discoveryNetworkData = new DiscoveryNetworkData(impl, volley);

        discoveryNetworkData.discoveryPage(1);

        RecordedRequest request1 = server.takeRequest();
        Assert.assertEquals("/discover/movie?page=1"
                , request1.getPath());
        signal.await();
    }

    @Test
    public void testError() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(404));


        CountDownLatch signal = new CountDownLatch(1);
        NetworkDiscoveryResponseHandlerIMPL impl = new NetworkDiscoveryResponseHandlerIMPL(signal);
        VolleyHttp volley = VolleyHttp.getInstance(RuntimeEnvironment.application);
        DiscoveryNetworkData discoveryNetworkData = new DiscoveryNetworkData(impl, volley);

        discoveryNetworkData.discoveryPage(1);

        RecordedRequest request1 = server.takeRequest();
        Assert.assertEquals("/discover/movie?page=1"
                , request1.getPath());
        signal.await();
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();

    }
}