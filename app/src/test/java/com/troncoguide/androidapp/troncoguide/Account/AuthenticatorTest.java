package com.troncoguide.androidapp.troncoguide.Account;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Application;
import android.test.suitebuilder.annotation.MediumTest;

import com.troncoguide.androidapp.troncoguide.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

/**
 * Created by root on 1/5/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
@MediumTest
public class AuthenticatorTest {

    private Application app;
    private AccountManager am;

    @Before
    public void setUp() throws Exception {
        app = RuntimeEnvironment.application;
        am = AccountManager.get(app);
    }






    @Test
    public void testEditProperties() throws Exception {

    }

    @Test
    public void testAddAccountTriggerLoginActivity() throws Exception {
        Account account = new Account("asd","test");
        am.addAccountExplicitly(account,"adsf",null);



    }

    @Test
    public void testConfirmCredentials() throws Exception {

    }

    @Test
    public void testGetAuthToken() throws Exception {

    }

    @Test
    public void testGetAuthTokenLabel() throws Exception {

    }

    @Test
    public void testUpdateCredentials() throws Exception {

    }

    @Test
    public void testHasFeatures() throws Exception {

    }
}