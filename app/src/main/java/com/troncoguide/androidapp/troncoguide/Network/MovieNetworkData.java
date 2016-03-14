package com.troncoguide.androidapp.troncoguide.Network;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.troncoguide.androidapp.troncoguide.Entity.MovieDiscovery;
import com.troncoguide.androidapp.troncoguide.Entity.ResponseTheMovieOrg;
import com.troncoguide.androidapp.troncoguide.Utils.Network.Mapper;
import com.troncoguide.androidapp.troncoguide.Utils.Network.URLBuilderUtil;

import java.util.Arrays;
import java.util.List;

/**
 * this is a wrapper class for comunicate with the server and parse his response
 * Created by Alejandro on 12/8/2015.
 */
public  class MovieNetworkData {
    private static final String TAG = "MOVIE_NET";
    private final Context ctx;
    private VolleyHttp volley;
    private Gson gson;
    private INetworkResponseHandler mImplementation;

    public MovieNetworkData(INetworkResponseHandler mImplementation, VolleyHttp volley, Context ctx) {
        this.mImplementation = mImplementation;
        this.volley = volley;
        this.gson = new Gson();
        this.ctx = ctx;
    }


    /**
     * this method create a thread and execute the most popular request
     */

    public void mostPopularPage(int page) {
        String mostPopular = URLBuilderUtil.mostPopularURLConstructor(page,ctx);
        jsonMoviesRequest(mostPopular);
    }

    /**
     * this method create a thread and execute a discovery request when it finish it trigger
     * the response callback implemented by the object that trigger the event
     * if you going to use in a anctivity that activity should implement INetworkResponseHandler
     *
     * @param page the page for request the data 1 by default
     */
    public void discoveryPage(int page) {
        String discovery = URLBuilderUtil.discoveryURLConstructor(page);
        jsonMoviesRequest(discovery);
    }
    /**
     * helper method for create a json request discovery
     * for volley
     *
     * @param url the url for request the data
     */
    private void jsonMoviesRequest(String url) {
        StringRequest request = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ResponseTheMovieOrg parse = gson.fromJson(response, ResponseTheMovieOrg.class);
                        MovieDiscovery[] response1 = Mapper.discoveryResponseToMovieDiscovery(parse,ctx);
                        List<MovieDiscovery> response2 = Arrays.asList(response1);
                        mImplementation.onResponse(response2);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i(TAG, "json request Error: ");
                        mImplementation.onError(error);
                        error.printStackTrace();
                    }
                });
        volley.addToRequestQueue(request);
    }

    public void detail(String id) {
    }
}
