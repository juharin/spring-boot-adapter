package com.juharin.nativeapi;

import com.juharin.serviceclient.request.NativeRequest;
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
public class NativeController {

    @Autowired
    private ServiceInterface service;

    private final Logger logger = LoggerFactory.getLogger(NativeController.class);

    @RequestMapping(value = "/native", method = RequestMethod.POST)
    public ResponseEntity<?> nativeApi(@RequestAttribute("nativeRequest") NativeRequest request) {
        logger.debug("/native: {}", request);
        NativeResponse response = service.doOperation(NativeRequest.class, request);
        return new ResponseEntity(response, new HttpHeaders(), HttpStatus.OK);
    }
}
