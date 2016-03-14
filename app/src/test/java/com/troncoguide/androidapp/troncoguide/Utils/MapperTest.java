package com.troncoguide.androidapp.troncoguide.Utils;

import android.content.ContentValues;

import com.google.gson.Gson;
import com.troncoguide.androidapp.troncoguide.BuildConfig;
import com.troncoguide.androidapp.troncoguide.ContentProvider.Contract.DatabaseContract;
import com.troncoguide.androidapp.troncoguide.Entity.MovieDiscovery;
import com.troncoguide.androidapp.troncoguide.Entity.ResponseTheMovieOrg;
import com.troncoguide.androidapp.troncoguide.TestUtils.NetworkUtils;
import com.troncoguide.androidapp.troncoguide.Utils.Network.Mapper;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

/**
 * Created by Alejandro on 12/9/2015.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MapperTest {
    private ResponseTheMovieOrg expected;
    private List<ResponseTheMovieOrg.ResultsEntity> response_result;
    private ResponseTheMovieOrg response;

    @Before
    public void setUp() throws Exception {
        //taking a response
        Gson gson = new Gson();
        String discovery_response = NetworkUtils.readDiscoveryResponse();
        response = gson.fromJson(discovery_response, ResponseTheMovieOrg.class);
        response_result = response.getResults();
    }

    @Test
    public void testDiscoveryResponseToMovieDiscovery() throws Exception {

        MovieDiscovery[] movies = Mapper.discoveryResponseToMovieDiscovery(response);
        int size = movies.length;
        for (int i = 0; i < size - 1; i++) {
            Assert.assertEquals("Title dont match", response_result.get(i).getTitle(), movies[i].title);
            Assert.assertEquals("poster dont match", response_result.get(i).getPoster_path(), movies[i].poster_img);
            Assert.assertEquals("backdrop path image dont match", response_result.get(i).getBackdrop_path(), movies[i].back_drop_img);
            Assert.assertEquals("vote average dont match", response_result.get(i).getVote_average(), movies[i].vote_avg);
            Assert.assertEquals("vote count dont match", response_result.get(i).getVote_count(), movies[i].vote_count);
        }
    }

    @Test
    public void testDiscoveryMovieToContentValues() throws Exception {

        MovieDiscovery[] movies = Mapper.discoveryResponseToMovieDiscovery(response);
        ContentValues[] cvTemp = Mapper.mapMovieDiscovery2ContentValues(movies);
        int size = movies.length;
        for (int i = 0; i < size; i++) {
            Assert.assertEquals("Title dont match", movies[i].title, cvTemp[i].getAsString(DatabaseContract.MovieEntry.COLUMN_TITLE));
            Assert.assertEquals("poster dont match", movies[i].poster_img, cvTemp[i].getAsString(DatabaseContract.MovieEntry.COLUMN_POSTER_IMG));
            Assert.assertEquals("backdrop path image dont match", movies[i].back_drop_img, cvTemp[i].getAsString(DatabaseContract.MovieEntry.COLUMN_BACKDROP_IMG));
            Assert.assertEquals("vote average dont match", movies[i].vote_avg, cvTemp[i].getAsDouble(DatabaseContract.MovieEntry.COLUMN_VOTE_AVG));
            Assert.assertEquals("vote count dont match", movies[i].vote_count, cvTemp[i].getAsInteger(DatabaseContract.MovieEntry.COLUMN_VOTE_COUNT).intValue());
        }
    }


}