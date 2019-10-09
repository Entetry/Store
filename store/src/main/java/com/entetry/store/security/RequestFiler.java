package com.entetry.store.security;

import com.entetry.storecommon.CustomAuthentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestFiler extends OncePerRequestFilter {
    private final AuthenticationManager authenticationManager;

    public RequestFiler(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        if (httpServletRequest.getHeader("UserId") != null &&
                (SecurityContextHolder.getContext().getAuthentication() == null ||
                        !SecurityContextHolder.getContext().getAuthentication().isAuthenticated())) {
            Long userId = Long.parseLong(httpServletRequest.getHeader("UserId"));
            Authentication customAuthentication = new CustomAuthentication(userId, null, null);
            customAuthentication = authenticationManager.authenticate(customAuthentication);
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(customAuthentication);
            SecurityContextHolder.setContext(securityContext);
            System.out.println(((CustomAuthentication) SecurityContextHolder.getContext().getAuthentication()).getUserId());
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
