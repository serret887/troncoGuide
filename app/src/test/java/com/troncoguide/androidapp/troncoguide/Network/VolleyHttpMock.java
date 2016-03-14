package com.troncoguide.androidapp.troncoguide.Network;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.android.volley.ExecutorDelivery;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.ResponseDelivery;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;

import java.io.File;
import java.util.concurrent.Executors;

/**
 * Created by Alejandro on 12/4/2015.
 */

public class VolleyHttpMock {

    public static RequestQueue newRequestQueueForTest(Context context) {
        final File cacheDir = new File(context.getCacheDir(), "volley");

        String userAgent = "volley/0";
        try {
            String packageName = context.getPackageName();
            PackageInfo info = context.getPackageManager().getPackageInfo(packageName, 0);
            userAgent = packageName + "/" + info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
        }


        HttpStack stack = new HurlStack();


        Network network = new BasicNetwork(stack);

        final ResponseDelivery responseDelivery = new ExecutorDelivery(Executors.newSingleThreadExecutor());

        final RequestQueue queue =
                new RequestQueue(
                        new DiskBasedCache(cacheDir),
                        network,
                        1,
                        responseDelivery);
        queue.start();

        return queue;
    }
}
