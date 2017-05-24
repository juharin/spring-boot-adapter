package com.juharin.serviceclient;

import com.juharin.serviceclient.request.RequestAdapter;
import java.util.HashMap;

public class RequestAdapterFactory {
    private static RequestAdapterFactory instance = null;

    HashMap<Class, Class> adapterMap = new HashMap<Class, Class>();

    protected RequestAdapterFactory() {}

    public static RequestAdapterFactory getInstance() {
        if (instance == null) {
            instance = new RequestAdapterFactory();
        }
        return instance;
    }

    public void registerAdapter(Class sourceClass, Class adapterClass) {
        adapterMap.put(sourceClass, adapterClass);
    }

    public RequestAdapter getAdapter(Class sourceClass)
    throws InstantiationException, IllegalAccessException {
        return (RequestAdapter) adapterMap.get(sourceClass).newInstance();
    }
}
