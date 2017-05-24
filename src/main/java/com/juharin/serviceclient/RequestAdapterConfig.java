package com.juharin.serviceclient;

import com.juharin.adaptedapi.AdaptedRequest;
import com.juharin.adaptedapi.AdaptedRequestAdapter;
import com.juharin.nativeapi.NativeRequestAdapter;
import com.juharin.serviceclient.request.NativeRequest;
import org.springframework.context.annotation.*;

@Configuration
public class RequestAdapterConfig {

    @Bean
    public RequestAdapterFactory registerAdapters() {
        RequestAdapterFactory adapterFactory = RequestAdapterFactory.getInstance();
        adapterFactory.registerAdapter(NativeRequest.class, NativeRequestAdapter.class);
        adapterFactory.registerAdapter(AdaptedRequest.class, AdaptedRequestAdapter.class);
        return adapterFactory;
    }
}
