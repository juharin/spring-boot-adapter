package com.juharin.nativeapi;

import com.juharin.serviceclient.request.NativeRequest;
import com.juharin.serviceclient.request.RequestAdapter;
import com.juharin.serviceclient.request.RequestProvider;

public class NativeRequestAdapter implements RequestAdapter {
   public RequestProvider adapt(final Object originalRequest) {
      return new NativeRequestProvider((NativeRequest) originalRequest);
   }
}
