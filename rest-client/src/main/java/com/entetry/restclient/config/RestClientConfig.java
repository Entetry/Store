package com.entetry.restclient.config;

import com.entetry.restclient.RestTemplateHeaderModifierInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan("com.entetry.restclient")
public class RestClientConfig {
    @Bean
    public ClientHttpRequestFactory getClientHttpRequestFactory() {
        int timeout = 5000;
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(timeout);
        return clientHttpRequestFactory;
    }

    @Bean
    public RestTemplate getRestTempalte() {
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if(CollectionUtils.isEmpty(interceptors)){
            interceptors = new ArrayList<>();
        }
        interceptors.add(new RestTemplateHeaderModifierInterceptor());
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

}
