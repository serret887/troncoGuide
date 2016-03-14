package com.troncoguide.androidapp.troncoguide.Utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.android.volley.toolbox.Volley;
import com.troncoguide.androidapp.troncoguide.BuildConfig;
import com.troncoguide.androidapp.troncoguide.Utils.Network.URLBuilderUtil;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

@RunWith(RobolectricGradleTestRunner.class)
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*"})
@PrepareForTest({Volley.class,URLBuilderUtil.class})
@Config(constants = BuildConfig.class, sdk = 21,packageName = "com.troncoguide.androidapp.troncoguide")
public class URLBuilderUtilTest {


    @Test
    public void testNewsURLConstructor() throws Exception {

        String expected = "https://api.themoviedb.org/3/discover/movie?page=1&api_key=6e821dc95705e61b32adec7853edd8c7";
        String actual = URLBuilderUtil.discoveryURLConstructor(0);
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testImageURLConstructor() throws Exception {
//given i want the image URL
        URLBuilderUtil.pickImageSize(RuntimeEnvironment.application);
        String expected = "http://image.tmdb.org/t/p/w342/wVTYlkKPKrljJfugXN7UlLNjtuJ.jpg";
        String actual = URLBuilderUtil.ImageURLConstructor("wVTYlkKPKrljJfugXN7UlLNjtuJ.jpg",RuntimeEnvironment.application);
        Assert.assertEquals(expected,actual);
    }


    @Test
      public void testPickSizeChooseTheRightSizeBig() throws Exception {
        //given an specific screen size  720×1280 this is my phone 160dpi

        //PickSize will choose the right size of the image to download
        URLBuilderUtil.pickImageSize(RuntimeEnvironment.application);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(RuntimeEnvironment.application);
        Assert.assertEquals("w342", pref.getString("poster_size", "default"));

    }

    // TODO: 12/12/2015 create the other test for teest other images that could fit in others devices
    @Test
       public void testPickSizeChooseTheRightSizeMedium() throws Exception {
        //given an specific screen size   540×960
        //PickSize will choose the right size of the image to download

    }
    @Test
       public void testPickSizeChooseTheRightSizeSmall() throws Exception {
        //given an specific screen size  360×640

        //PickSize will choose the right size of the image to download
    }
}