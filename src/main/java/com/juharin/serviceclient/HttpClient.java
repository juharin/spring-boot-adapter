package com.juharin.serviceclient;

import com.juharin.serviceclient.request.NativeRequest;
import com.juharin.serviceclient.request.RequestProvider;
import com.juharin.serviceclient.response.NativeResponse;
import java.time.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class HttpClient implements ServiceInterface {

    private final Logger logger = LoggerFactory.getLogger(HttpClient.class);

    // Environment variables where underscores are replaced with dots
    @Value("${HTTP.PROTOCOL}")
    private String httpProtocol;
    @Value("${SERVICE.URL}")
    private String serviceUrl;
    @Value("${SERVICE.PORT}")
    private String servicePort;
    @Value("${RESOURCE}")
    private String resource;

    @Override
    public NativeResponse doOperation(Class operationClass, Object operation) {
        try {
            RequestAdapterFactory adapterFactory = RequestAdapterFactory.getInstance();
            RequestProvider requestProvider = adapterFactory
                .getAdapter(operationClass)
                .adapt(operation);

            RestTemplate restTemplate = new RestTemplate();
            NativeRequest nativeRequest = requestProvider.getRequest();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // Crude HTTP Basic Auth for demo purposes
            headers.add("Authorization", "Basic MDAyMjMzNDQ4OnRlc3RhYWph");
            HttpEntity<NativeRequest> request = new HttpEntity<>(nativeRequest, headers);

            String serviceApi = serviceApi();
            logger.info("calling: {}", serviceApi);
            ResponseEntity<NativeResponse> response = restTemplate
                .exchange(
                    serviceApi,
                    HttpMethod.POST,
                    request,
                    NativeResponse.class
                );
            if (response.getStatusCode() == HttpStatus.OK && response.hasBody()) {
                return response.getBody();
            }
            else {
                NativeResponse errorResponse = new NativeResponse();
                errorResponse.timestamp = ZonedDateTime.now();
                errorResponse.messageId = nativeRequest.messageId;
                errorResponse.success = false;
                errorResponse.statusCode = response.getStatusCode().toString();
                errorResponse.statusMessage = "Communication error";
                return errorResponse;
            }
        }
        catch (InstantiationException | IllegalAccessException e) {
            logger.debug("Cannot create an adapter for request: {}", e.getMessage());
        }
        return null;
    }

    private String serviceApi() {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(httpProtocol);
        urlBuilder.append(serviceUrl);
        urlBuilder.append(":");
        urlBuilder.append(servicePort);
        urlBuilder.append(resource);
        return urlBuilder.toString();
    }
}
