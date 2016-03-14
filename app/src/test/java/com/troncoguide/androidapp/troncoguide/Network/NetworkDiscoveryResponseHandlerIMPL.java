package com.troncoguide.androidapp.troncoguide.Network;

import org.junit.Assert;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Alejandro on 12/9/2015.
 */
public class NetworkDiscoveryResponseHandlerIMPL<T> implements INetworkResponseHandler {
    CountDownLatch signal;
    private T err;

    public NetworkDiscoveryResponseHandlerIMPL(CountDownLatch signal) {
        this.signal = signal;
    }

    @Override
    public <T> void onResponse(T response) {
        Assert.assertNotNull(response);
        signal.countDown();
    }

    @Override
    public <T> void onError(T error) {
        Assert.assertNotNull(error);
        signal.countDown();
    }
}
