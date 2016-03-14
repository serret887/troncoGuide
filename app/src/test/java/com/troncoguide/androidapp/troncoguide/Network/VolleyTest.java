//package com.troncoguide.androidapp.troncoguide.Network;
//
//
//import android.content.Context;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.squareup.okhttp.mockwebserver.Dispatcher;
//import com.squareup.okhttp.mockwebserver.MockResponse;
//import com.squareup.okhttp.mockwebserver.MockWebServer;
//import com.squareup.okhttp.mockwebserver.RecordedRequest;
//import com.troncoguide.androidapp.troncoguide.BuildConfig;
//import com.troncoguide.androidapp.troncoguide.TestUtils.NetworkUtils;
//
//import org.junit.AfterClass;
//import org.junit.Assert;
//import org.junit.BeforeClass;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PowerMockIgnore;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.rule.PowerMockRule;
//import org.robolectric.RobolectricGradleTestRunner;
//import org.robolectric.RuntimeEnvironment;
//import org.robolectric.annotation.Config;
//
//import java.io.IOException;
//import java.util.concurrent.CountDownLatch;
//
//import static org.mockito.Mockito.when;
//
//@RunWith(RobolectricGradleTestRunner.class)
//@Config(constants = BuildConfig.class, sdk = 21)
//@PowerMockIgnore({ "org.mockito.*", "org.robolectric.*", "android.*" })
//@PrepareForTest(Volley.class)
//public class VolleyTest {
//    @Rule
//    public PowerMockRule rule = new PowerMockRule();
//
//    static String discovery_response;
//    private static MockWebServer server;
//
//    @BeforeClass
//    public static void initClass() throws Exception {
////            stting up sync problems with volley
//        PowerMockito.mockStatic(Volley.class);
//        Context c = RuntimeEnvironment.application;
//        RequestQueue requestQueue = VolleyHttpMock.newRequestQueueForTest(RuntimeEnvironment.application);
//        when(Volley.newRequestQueue(RuntimeEnvironment.application))
//                .thenReturn(requestQueue);
//         server = new MockWebServer();
//        discovery_response = NetworkUtils.readDiscoveryResponse();
//        //setting dispatcher
//        Dispatcher dispatcher = new Dispatcher() {
//            @Override
//            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
//                if (request.getPath().equals("/3/discover/movie?page=1&append_to_response=genres&api_key=6e821dc95705e61b32adec7853edd8c7")) {
//                    return new MockResponse().setResponseCode(200).setBody(discovery_response);
//                } else if (request.getPath().equals("v1/check/version/")) {
//                    return new MockResponse().setResponseCode(200).setBody("version=9");
//                } else if (request.getPath().equals("/v1/profile/info")) {
//                    return new MockResponse().setResponseCode(200).setBody("{\\\"info\\\":{\\\"name\":\"Lucas Albuquerque\",\"age\":\"21\",\"gender\":\"male\"}}");
//                }
//                return new MockResponse().setResponseCode(404);
//            }
//        };
//
//        server.setDispatcher(dispatcher);
//        server.play();
//        server.getUrl("/MockServerResponse/");
//    }
//
//
//    @Test
//    public void test_VolleyExecuteRequest() throws Exception {
//
//        final CountDownLatch signal = new CountDownLatch(1);
//        // Exercise your application code, which should make those HTTP requests.
//        // Responses are returned in the same order that they are enqueued.
//        StringRequest request = new StringRequest(Request.Method.GET,
//                "https://api.themoviedb.org/3/discover/movie?page=1&append_to_response=genres&api_key=6e821dc95705e61b32adec7853edd8c7",
//                new Response.Listener<String>() {
//
//                    @Override
//                    public void onResponse(String response1) {
//                        Assert.assertEquals(discovery_response, response1);
//                        signal.countDown();
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Assert.assertNotNull("The HTTP Request was a failure", null);
//                signal.countDown();
//            }
//        });
//
//        VolleyHttp volley = VolleyHttp.getInstance(RuntimeEnvironment.application);
//        volley.addToRequestQueue(request);
//        signal.await();
//        RecordedRequest request1 = server.takeRequest();
//        Assert.assertEquals("https://api.themoviedb.org/3/discover/movie?page=1&append_to_response=genres&api_key=6e821dc95705e61b32adec7853edd8c7"
//                , request1.getPath());
//    }
//
//    @AfterClass
//    public static void close() throws IOException {
//        server.shutdown();
//    }
//}
//
//

package com.troncoguide.androidapp.troncoguide.Network;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import com.troncoguide.androidapp.troncoguide.BuildConfig;
import com.troncoguide.androidapp.troncoguide.TestUtils.NetworkUtils;

import org.junit.After;
import org.junit.Assert;
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

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
@PowerMockIgnore({ "org.mockito.*", "org.robolectric.*", "android.*" })
@PrepareForTest(Volley.class)
public class VolleyTest {
        @Rule
    public PowerMockRule rule = new PowerMockRule();
    private URL baseUrl;
    private MockWebServer server;
    private String discovery_response;

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(Volley.class);
        Context c = RuntimeEnvironment.application;
        RequestQueue requestQueue = VolleyHttpMock.newRequestQueueForTest(RuntimeEnvironment.application);
        when(Volley.newRequestQueue(RuntimeEnvironment.application))
                .thenReturn(requestQueue);

         server = new MockWebServer();
         discovery_response = NetworkUtils.readDiscoveryResponse();
        //setting dispatcher
        Dispatcher dispatcher = new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                String path = request.getPath();
                //discover page 1
                if (path.contains("3/discover/movie?page=1&append_to_response=genres")) {
                    return new MockResponse().setResponseCode(200).setBody("discovery");
                } else if (path.equals("v1/check/version/")) {
                    return new MockResponse().setResponseCode(200).setBody("version=9");
                } else if (path.equals("/v1/profile/info")) {
                    return new MockResponse().setResponseCode(200).setBody("{\\\"info\\\":{\\\"name\":\"Lucas Albuquerque\",\"age\":\"21\",\"gender\":\"male\"}}");
                }
                return new MockResponse().setResponseCode(404);
            }
        };

        server.setDispatcher(dispatcher);
        server.play();
         baseUrl = server.getUrl("/");

    }

    @Test
    public void testName() throws Exception {

//        server.enqueue(new MockResponse().setBody("asd"));
        final CountDownLatch signal = new CountDownLatch(1);
        // Exercise your application code, which should make those HTTP requests.
        // Responses are returned in the same order that they are enqueued.
        StringRequest request = new StringRequest(Request.Method.GET,
               baseUrl + "3/discover/movie?page=1&append_to_response=genres",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response1) {
                        Assert.assertEquals("discovery", response1);
                        signal.countDown();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Assert.assertNotNull("The HTTP Request was a failure", null);
                signal.countDown();
            }
        });

        VolleyHttp volley = VolleyHttp.getInstance(RuntimeEnvironment.application);
        volley.addToRequestQueue(request);
        signal.await();
        RecordedRequest request1 = server.takeRequest();
        Assert.assertEquals("/3/discover/movie?page=1&append_to_response=genres"
                , request1.getPath());
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();

    }
}