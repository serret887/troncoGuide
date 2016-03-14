package com.troncoguide.androidapp.troncoguide.Network;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Jeremy on 11/19/2015.
 */
public class VolleyHttp {

    private static VolleyHttp mInstance;
    private static Context mCtx;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private int limitKb;

    private VolleyHttp(Context context) {
        this.mCtx = context;
        this.mRequestQueue = getRequestQueue();
    }

    private int memoryCalculator(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(
                Context.ACTIVITY_SERVICE);
        int maxKb = am.getMemoryClass() * 1024;
        return maxKb / 8; // 1/8th of total ram
    }

    public static synchronized VolleyHttp getInstance(Context context) {
        if (mInstance == null) {
            // TODO: 11/23/2015 use appContext
            mInstance = new VolleyHttp(context);
        }
        return mInstance;
    }

    private RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
            imageLoaderInitializer(mCtx);


            //defining disk cache


        }
        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        if (mImageLoader == null) {
            imageLoaderInitializer(mCtx);
        }
        return mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    private void imageLoaderInitializer(Context mCtx){
        limitKb = memoryCalculator(mCtx);
        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(limitKb);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }


}
