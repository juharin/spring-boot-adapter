package com.juharin.nativeapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.juharin.auth.AuthenticationService;
import com.juharin.serviceclient.request.NativeRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

public class NativeAuthenticationService implements AuthenticationService {

    private final Logger logger = LoggerFactory.getLogger(NativeAuthenticationService.class);

    public NativeAuthenticationService() {}

    public Authentication getAuthentication(HttpServletRequest request) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            NativeWrapperRequest wrapperRequest = mapper.readValue(getBodyJson(request), NativeWrapperRequest.class);
            NativeRequest nativeRequest = wrapperRequest.request;

            // Do auth

            request.setAttribute("nativeRequest", nativeRequest);
            return new UsernamePasswordAuthenticationToken(nativeRequest.userId, wrapperRequest.signature, authorities("APIUSER"));
        }
        catch (IOException ioe) {
            logger.error("IO error in reading JSON string: {}", ioe.getMessage());
        }
        return null;
    }

    private String getBodyJson(HttpServletRequest request) {
        String line = null;
        StringBuilder jsonBuilder = new StringBuilder();
        try {
            BufferedReader requestReader = request.getReader();
            while ((line = requestReader.readLine()) != null) {
                jsonBuilder.append(line);
            }
        }
        catch (IOException ioe) {
            logger.error("IO error in reading JSON body: {}", ioe.getMessage());
        }
        return jsonBuilder.toString();
    }

    private List<SimpleGrantedAuthority> authorities(String authorityKey) {
        switch (authorityKey) {
            case "APIUSER":
                return Arrays.asList(new SimpleGrantedAuthority(authorityKey));
            default:
                break;
        }
        return null;
    }
 }
