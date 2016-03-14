package com.troncoguide.androidapp.troncoguide.Network.MOck;

import com.troncoguide.androidapp.troncoguide.Network.INetworkResponseHandler;

import org.junit.Assert;

/**
 * Created by Alejandro on 12/9/2015.
 */
public class NetworkDiscoveryResponseHandlerIMPL implements INetworkResponseHandler {
    @Override
    public <T> void onResponse(T response) {
        Assert.assertNotNull(response);
    }

    @Override
    public <T> void onError(T error) {
Assert.assertNotNull(error);
    }
}
