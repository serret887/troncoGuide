package com.troncoguide.androidapp.troncoguide;

/**
 * Created by Alejandro on 12/10/2015.
 * with this class avoid the problem with robolectric and the flavors
 */
public  final class RoboelectricConfig {
    public static final boolean DEBUG = BuildConfig.DEBUG;
    public static final String APPLICATION_ID = "com.troncoguide.androidapp.troncoguide";
    public static final String BUILD_TYPE = BuildConfig.BUILD_TYPE;
    public static final String FLAVOR = BuildConfig.FLAVOR;
    public static final int VERSION_CODE = BuildConfig.VERSION_CODE;
    public static final String VERSION_NAME = BuildConfig.VERSION_NAME;
}