package com.juharin.nativeapi;

//import com.fasterxml.jackson.annotation.JsonProperty;
import com.juharin.serviceclient.request.NativeRequest;
import com.juharin.serviceclient.request.RequestProvider;

public class NativeRequestProvider implements RequestProvider {
    private NativeRequest nativeRequest = null;

    public NativeRequestProvider(final NativeRequest request) {
        nativeRequest = request;
    }

    @Override
    public NativeRequest getRequest() {
        return nativeRequest;
    }
}
