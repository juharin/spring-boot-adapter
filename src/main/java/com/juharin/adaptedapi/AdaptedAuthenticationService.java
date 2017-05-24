package com.juharin.adaptedapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.juharin.auth.AuthenticationService;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
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
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class AdaptedAuthenticationService implements AuthenticationService {

    private static final String HMAC_ALGO = "HmacSHA256";
    private static final String SECRET = "kakkapussi";
    private final Logger logger = LoggerFactory.getLogger(AdaptedAuthenticationService.class);
    private final Mac hmac;

    public AdaptedAuthenticationService()
    throws NoSuchAlgorithmException, InvalidKeyException {
        hmac = Mac.getInstance(HMAC_ALGO);
        hmac.init(new SecretKeySpec(SECRET.getBytes(StandardCharsets.UTF_8), HMAC_ALGO));
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        String encodedJson = request.getParameter("req");
        String requestHash = request.getParameter("sig");

    	try {
            String requestJson = new String(Base64.getDecoder().decode(encodedJson), StandardCharsets.UTF_8);
            logger.info("request JSON: {}", requestJson);

            byte[] contentHashBytes = hmac.doFinal(requestJson.getBytes(StandardCharsets.UTF_8));
            String contentHash = new String(Hex.encodeHex(contentHashBytes));
            logger.info("content hash: {}", contentHash);
            logger.info("request hash: {}", requestHash);

            if (contentHash.equals(requestHash)) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());
                AdaptedRequest adaptedRequest = mapper.readValue(requestJson, AdaptedRequest.class);
                request.setAttribute("adaptedRequest", adaptedRequest);
                return new UsernamePasswordAuthenticationToken(adaptedRequest.apiKey, requestHash, authorities("APIUSER"));
            }
    	}
        catch (UnsupportedEncodingException uee) {
            logger.error("Unsupported String encoding used: {}", uee.getMessage());
        }
        catch (IOException ioe) {
            logger.error("IO error in reading JSON string: {}", ioe.getMessage());
        }
        return null;
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
