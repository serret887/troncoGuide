package com.troncoguide.androidapp.troncoguide.Utils.Network;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by Jeremy on 11/19/2015.
 */
public class URLBuilderUtil {

    private static final String APP_ID = "api_key=6e821dc95705e61b32adec7853edd8c7";
    private static final String BASE_URL_IMAGE = "http://image.tmdb.org/t/p";
    private static final String TAG = "URL_FACTORY";
    private static final String BASE_URL_DISCOVER = "https://api.themoviedb.org/3/discover/movie";
    private static final String PAGE_TAG = "page";

    public static String mostPopularURLConstructor(int page,Context context) {
        int current_page = (page > 0) ? page : 1;
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        int genre = genreCode(pref.getString("genre", "comedy"));

        String url = BASE_URL_DISCOVER + "?"+"with_genres="+genre+"&" + PAGE_TAG + "=" + current_page + "&" + APP_ID;
        Log.d(TAG, "ConstructURL() returned: " + url);
        return url;
    }

    private static int genreCode(String genre) {
        if(genre.equals("comedy"))
            return 35;
        if(genre.equals("action"))
            return 28;
        if(genre.equals("sciFy"))
            return 878;
        if(genre.equals("animate"))
            return 16;
        if(genre.equals("drama"))
            return 18;

return 80;
    }


    public  static String discoveryURLConstructor(int page) {
        int current_page = (page > 0) ? page : 1;
        String url = BASE_URL_DISCOVER + "?" + PAGE_TAG + "=" + current_page +"&" + APP_ID;
        Log.d(TAG, "ConstructURL() returned: " + url);
        return url;
    }

    public static String ImageURLConstructor(String image,Context ctx) {
        String size = PreferenceManager.getDefaultSharedPreferences(ctx).getString("poster_size", "w342");
        String url = BASE_URL_IMAGE + "/" + size + "/" + image;
        Log.d(TAG, "ImageConstructURL() returned: " + url);
        return url;
    }

    /**
     * this method is in charge of choose the right image configuration for your device
     * and save all this informatinon in a preference
     * @param application the context of the application
     */
    public static void pickImageSize(Context application) {
// TODO: 1/5/16 create the method for choose the right size for the image
        float density = application.getResources().getDisplayMetrics().density;
        //Choose the right
        //Discovery Poster
        // TODO: 12/12/2015 change the raw string for attr in the xml file
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(application);
        if (!pref.contains("poster_size")) {
            SharedPreferences.Editor prefEdit = pref.edit();
            prefEdit.putString("poster_size","w342").apply();
        }

    }

}

