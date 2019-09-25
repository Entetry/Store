package com.entetry.restclient;

import com.entetry.storecommon.dto.UserDetailsDto;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

public class RestTemplateHeaderModifierInterceptor  implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request,
            byte[] body,
            ClientHttpRequestExecution execution) throws IOException {
        if(!request.getHeaders().containsKey("AuthorizationRequest")){
            SecurityContext securityContext = SecurityContextHolder.getContext();
            UserDetailsDto userDetailsDto = (UserDetailsDto) securityContext.getAuthentication().getPrincipal();
            request.getHeaders().add("UserId",userDetailsDto.getUserId().toString());
            System.out.println("ADD HEADER USERID" + userDetailsDto.getUserId());
        }
        return execution.execute(request,body);
    }
}
