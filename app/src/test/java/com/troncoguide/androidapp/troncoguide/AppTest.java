package com.troncoguide.androidapp.troncoguide;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.troncoguide.androidapp.troncoguide.Account.AuthenticatorService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

/**
 * Created by Alejandro on 12/12/2015.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21,packageName = "com.troncoguide.androidapp.troncoguide")
//@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*"})
//@PrepareForTest({Volley.class,URLBuilderUtil.class})
public class AppTest {
    private App app;

    @Before
    public void setUp() throws Exception {
        app = (App)RuntimeEnvironment.application ;
    }

    @Test
    public void TheAppShouldSetSizePreferences() throws Exception {
        //Given I am in the APP
        app.onCreate();
        //Then Image Size is setup in the preferences
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(app);
        Assert.assertEquals("w342", pref.getString("poster_size", "default"));
    }

    @Test
    public void theAppShouldCreateAnAccount() throws Exception {
        AccountManager manager = AccountManager.get(app.getApplicationContext());
        //Given I am in the App
        app.onCreate();
        //then account manager should have a new account
        Account[] account = manager.getAccountsByType(AuthenticatorService.ACCOUNT_TYPE);
        Assert.assertEquals("The account name should be equals",AuthenticatorService.ACCOUNT_NAME,account[0].name);
    }

    @Test
    public void theAppShouldTriggerAManuallytheSyncAdapter() throws Exception {
        //Given I am in the App
        app.onCreate();
        //Then a call to SyncAdapter.onPerformSync should be trigger


    }
}