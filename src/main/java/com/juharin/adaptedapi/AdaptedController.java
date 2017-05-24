package com.juharin.adaptedapi;

import com.juharin.adaptedapi.AdaptedResponse;
import com.juharin.adaptedapi.AdaptedRequest;
import com.juharin.serviceclient.response.NativeResponse;
import com.juharin.serviceclient.ServiceInterface;
import java.time.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdaptedController {

    @Autowired
    private ServiceInterface service;

    private final Logger logger = LoggerFactory.getLogger(AdaptedController.class);

    @RequestMapping(value = "/adapted", method = RequestMethod.POST)
    public ResponseEntity<?> adaptedApi(@RequestAttribute("adaptedRequest") AdaptedRequest request) {
        logger.info("/adapted: {}", request);
        NativeResponse nativeResponse = service.doOperation(AdaptedRequest.class, request);
        return new ResponseEntity(convert(nativeResponse), new HttpHeaders(), HttpStatus.OK);
    }

    private AdaptedResponse convert(NativeResponse nativeResponse) {
        AdaptedResponse response = new AdaptedResponse();
        response.datetime = nativeResponse.timestamp;
        response.status = nativeResponse.statusCode;
        response.requestId = nativeResponse.messageId;
        return response;
    }
}
