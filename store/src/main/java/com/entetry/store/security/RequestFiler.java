package com.entetry.store.security;

import com.entetry.storecommon.CustomAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestFiler extends OncePerRequestFilter {
    private CustomAuthenticationProvider customAuthenticationProvider ;
    public RequestFiler(){};
    @Autowired
    public RequestFiler(CustomAuthenticationProvider customAuthenticationProvider){
        this.customAuthenticationProvider= customAuthenticationProvider;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

       if(!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
           Long userId = Long.parseLong(httpServletRequest.getHeader("UserId"));
           if(!((CustomAuthentication) SecurityContextHolder.getContext().getAuthentication()).getUserId().equals(userId)){
    CustomAuthentication customAuthentication = new CustomAuthentication(userId,null,null);
    customAuthentication= (CustomAuthentication) customAuthenticationProvider.authenticate(customAuthentication);
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(customAuthentication);
        SecurityContextHolder.setContext(securityContext);}
       }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
