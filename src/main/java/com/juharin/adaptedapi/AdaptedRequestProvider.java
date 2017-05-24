package com.juharin.adaptedapi;

import com.juharin.serviceclient.request.NativeRequest;
import com.juharin.serviceclient.request.RequestProvider;

public class AdaptedRequestProvider implements RequestProvider {
    private AdaptedRequest adaptedRequest = null;

    public AdaptedRequestProvider(final AdaptedRequest request) {
        adaptedRequest = request;
    }

    @Override
    public NativeRequest getRequest() {
        NativeRequest nativeRequest = new NativeRequest();
        nativeRequest.timestamp = adaptedRequest.datetime;
        nativeRequest.userId = adaptedRequest.apiKey;
        nativeRequest.messageId = adaptedRequest.requestId;
        nativeRequest.operation = adaptedRequest.method == "do_it" ? "foo" : "bar";
        nativeRequest.value = adaptedRequest.value;
        return nativeRequest;
    }
}
