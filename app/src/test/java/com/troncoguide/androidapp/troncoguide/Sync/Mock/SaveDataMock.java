package com.troncoguide.androidapp.troncoguide.Sync.Mock;

import android.content.ContentProviderClient;

import com.troncoguide.androidapp.troncoguide.ContentProvider.Mock.ProviderMock;
import com.troncoguide.androidapp.troncoguide.Network.INetworkResponseHandler;
import com.troncoguide.androidapp.troncoguide.Sync.ISaveData;

import java.util.concurrent.CountDownLatch;

/**
 * Created by root on 1/6/16.
 */
public class SaveDataMock implements INetworkResponseHandler ,ISaveData{
    private CountDownLatch signal;
    private ProviderMock mProvider;

    public SaveDataMock(CountDownLatch signal) {
        this.signal = signal;
    }

    @Override
    public <T> void onResponse(T response) {
        signal.countDown();
    }

    @Override
    public <T> void onError(T error) {
        signal.countDown();
    }

    @Override
    public void setProvider(ContentProviderClient provider) {
        mProvider = new ProviderMock();
    }
}
