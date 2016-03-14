package com.troncoguide.androidapp.troncoguide.TestUtils;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.Gson;
import com.troncoguide.androidapp.troncoguide.ContentProvider.Contract.DatabaseContract;
import com.troncoguide.androidapp.troncoguide.Entity.MovieDiscovery;
import com.troncoguide.androidapp.troncoguide.Entity.ResponseTheMovieOrg;
import com.troncoguide.androidapp.troncoguide.Utils.Network.Mapper;

import junit.framework.Assert;

/**
 * Created by Alejandro on 12/3/2015.
 */
public class ContentProviderUtil {

//    public static ContentValues[] cvMovie = new ContentValues[3];
//
////    static {
////        int i;
////        for (i = 0; i < 3; i++) {
////            ContentValues cv = new ContentValues(1);
////            //movies
////            cv.put(DatabaseContract.MovieEntry.COLUMN_VOTE_AVG, 1.42);
////            cv.put(DatabaseContract.MovieEntry.COLUMN_TITLE, "precioso" + i);
////            cv.put(DatabaseContract.MovieEntry.COLUMN_BACKDROP_IMG, "qSc4L05AnHbMpSk0bsHuX25vX4V.jpg");
////            cv.put(DatabaseContract.MovieEntry.COLUMN_POSTER_IMG, "qSc4L05AnHbMpSk0bsHuX25vX4V.jpg");
////            cv.put(DatabaseContract.MovieEntry.COLUMN_VOTE_COUNT, 25);
////            cvMovie[i] = cv;
////        }
////    }

    public static MovieDiscovery[] dummyMovieDiscoveryArray() {
        Gson gson = new Gson();
        String discovery_response = NetworkUtils.readDiscoveryResponse();
        ResponseTheMovieOrg response = gson.fromJson(discovery_response, ResponseTheMovieOrg.class);
        return Mapper.discoveryResponseToMovieDiscovery(response);
    }

    public static ContentValues[] dummyContentValuesOfMovieDiscoveryArray() {
        MovieDiscovery[] movies = dummyMovieDiscoveryArray();
        return Mapper.mapMovieDiscovery2ContentValues(movies);
    }

    /**
     * this for asser testing with a ContentValues array
     * @param c Cursor for query the databaase
     * @param cv_container with al the content values for examinate and se e if they are equal to teh result showed by the cursor
     */
    public static void assertDiscoveryData(Cursor c, ContentValues[] cv_container) {
        Assert.assertEquals(c.getCount(), cv_container.length);
        c.moveToFirst();
        for (int i = 0; i < cv_container.length ; i++) {
            assertDiscoveryData(c, cv_container[i]);
            c.moveToNext();
        }
    }

    /**
     * Search in the content Value for the simi
     *
     * @param c Cursor en la posicion exacta del data
     * @param cv_container container with the expected data
     */
    public static void assertDiscoveryData(Cursor c, ContentValues cv_container) {

        int title = c.getColumnIndex(DatabaseContract.MovieEntry.COLUMN_TITLE);
        int back_image = c.getColumnIndex(DatabaseContract.MovieEntry.COLUMN_BACKDROP_IMG);
        int poster_image = c.getColumnIndex(DatabaseContract.MovieEntry.COLUMN_POSTER_IMG);
        int vote_avg = c.getColumnIndex(DatabaseContract.MovieEntry.COLUMN_VOTE_AVG);
        int vote_count = c.getColumnIndex(DatabaseContract.MovieEntry.COLUMN_VOTE_COUNT);

        Assert.assertEquals(cv_container.get(DatabaseContract.MovieEntry.COLUMN_TITLE), c.getString(title));
        Assert.assertEquals(cv_container.get(DatabaseContract.MovieEntry.COLUMN_BACKDROP_IMG), c.getString(back_image));
        Assert.assertEquals(cv_container.get(DatabaseContract.MovieEntry.COLUMN_VOTE_AVG), c.getDouble(vote_avg));
        Assert.assertEquals(cv_container.get(DatabaseContract.MovieEntry.COLUMN_POSTER_IMG), c.getString(poster_image));
        Assert.assertEquals(cv_container.get(DatabaseContract.MovieEntry.COLUMN_VOTE_COUNT), c.getInt(vote_count));
    }


}
