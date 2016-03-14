package com.troncoguide.androidapp.troncoguide.Utils.Network;


import android.content.Context;

import com.troncoguide.androidapp.troncoguide.Entity.MovieDiscovery;
import com.troncoguide.androidapp.troncoguide.Entity.ResponseTheMovieOrg;

import java.util.List;

/**
 * this class is in charge of get a Discovery Response and map it to MovieDiscovery[]  class
 * Created by Alejandro on 12/9/2015.
 */
public class Mapper {
    /**
     * this method take the Discovery  response from the server and traslate it to an array of MovieDiscovery
     * @param resp is a Json response from the discovery API
     * @return an organize array from the server response in the MovieDiscovery class
     */
    public static MovieDiscovery[] discoveryResponseToMovieDiscovery(ResponseTheMovieOrg resp, Context ctx) {
        List<ResponseTheMovieOrg.ResultsEntity> results = resp.getResults();
        int result_size = results.size();
        MovieDiscovery[] movies = new MovieDiscovery[result_size];
        for (int i = result_size - 1; i >= 0; i--) {
            ResponseTheMovieOrg.ResultsEntity resultsEntity = results.get(i);
            //movies
            MovieDiscovery movie = new MovieDiscovery();
            movie.id = resultsEntity.getId();
            movie.vote_avg = resultsEntity.getVote_average();
            movie.title = resultsEntity.getTitle();
            movie.back_drop_img = URLBuilderUtil.ImageURLConstructor(resultsEntity.getBackdrop_path(),ctx);
            movie.poster_img = URLBuilderUtil.ImageURLConstructor(resultsEntity.getPoster_path(),ctx);
            movie.vote_count = resultsEntity.getVote_count();
            movies[i] = movie;
        }
        return movies;
    }

}
