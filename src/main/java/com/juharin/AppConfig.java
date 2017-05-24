package com.juharin;

import com.juharin.serviceclient.RequestAdapterConfig;
import com.juharin.serviceclient.RequestAdapterFactory;
import com.juharin.serviceclient.HttpClient;
import com.juharin.serviceclient.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

@Configuration
@Import(RequestAdapterConfig.class)
public class AppConfig {

    @Autowired
    RequestAdapterConfig requestAdapterConfig;

    @Bean
    ServiceInterface serviceInterface()
    {
        RequestAdapterFactory adapterFactory = requestAdapterConfig.registerAdapters();
        return new HttpClient();
    }
}
