package com.juharin.adaptedapi;

import com.juharin.serviceclient.request.RequestAdapter;
import com.juharin.serviceclient.request.RequestProvider;

public class AdaptedRequestAdapter implements RequestAdapter {
   public RequestProvider adapt(final Object originalRequest) {
      return new AdaptedRequestProvider((AdaptedRequest) originalRequest);
   }
}
