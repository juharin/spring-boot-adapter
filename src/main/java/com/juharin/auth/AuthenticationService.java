package com.juharin.auth;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface AuthenticationService {
    public Authentication getAuthentication(HttpServletRequest request);
}
