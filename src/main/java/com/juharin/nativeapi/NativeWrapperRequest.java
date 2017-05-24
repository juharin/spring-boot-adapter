package com.juharin.nativeapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.juharin.serviceclient.request.NativeRequest;

public class NativeWrapperRequest {
    @JsonProperty("req")
    NativeRequest request;
    @JsonProperty("sig")
    String signature;
}
