package com.troncoguide.androidapp.troncoguide;

import android.app.Application;

import com.troncoguide.androidapp.troncoguide.Utils.Network.URLBuilderUtil;

/**
 * Created by Alejandro on 12/12/2015.
 */
public class App extends Application {
    /**
     * Called when the application is starting, before any activity, service,
     * or receiver objects (excluding content providers) have been created.
     * Implementations should be as quick as possible (for example using
     * lazy initialization of state) since the time spent in this function
     * directly impacts the performance of starting the first activity,
     * service, or receiver in a process.
     * If you override this method, be sure to call super.onCreate().
     */
    @Override
    public void onCreate() {
        super.onCreate();
        // TODO: 12/12/2015 this is a porsiacaso maybe all the initialization should be passed to a thread
        URLBuilderUtil.pickImageSize(getApplicationContext());


    }
}
