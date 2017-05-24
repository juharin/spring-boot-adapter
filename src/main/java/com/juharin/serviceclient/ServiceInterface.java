package com.juharin.serviceclient;

import com.juharin.serviceclient.response.NativeResponse;

public interface ServiceInterface {
    public NativeResponse doOperation(Class operationClass, Object operation);
}
