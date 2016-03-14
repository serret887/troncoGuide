package com.troncoguide.androidapp.troncoguide.Network;

/**
 * Created by Alejandro on 12/9/2015.
 * this is an interface for get the response and the error ffrom the volley wrapper MOviewNetwork
 */
public interface INetworkResponseHandler {
    public<T> void onResponse(T response);
    public<T> void onError(T error);

}
