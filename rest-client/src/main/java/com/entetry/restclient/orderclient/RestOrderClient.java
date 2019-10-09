package com.entetry.restclient.orderclient;

import com.entetry.storecommon.dto.ShoppingCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestOrderClient {
    private final RestTemplate restTemplate;

    @Autowired
    public RestOrderClient(RestTemplate template) {
        this.restTemplate = template;
    }

    public void createOrder(ShoppingCard shoppingCard) {
        String resourceUrl = "http://localhost:9977/orders";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        restTemplate.exchange(resourceUrl, HttpMethod.POST,
                new HttpEntity<>(shoppingCard, headers), new ParameterizedTypeReference<ShoppingCard>() {
                });
    }
}
