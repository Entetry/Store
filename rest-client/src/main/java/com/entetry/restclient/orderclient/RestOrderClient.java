package com.entetry.restclient.orderclient;

import com.entetry.storecommon.dto.OrderDto;
import com.entetry.storecommon.dto.ShoppingCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
    public OrderDto getOrderById(String id) {
        String resourceUrl = "http://localhost:9977/orders/" + id;
        ResponseEntity<OrderDto> response = restTemplate.exchange(resourceUrl, HttpMethod.GET,
                null, new ParameterizedTypeReference<OrderDto>() {
                });
        return response.getBody();
    }
    public List<OrderDto> getAllOrders() {
        String resourceUrl = "http://localhost:9977/orders";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<List<OrderDto>> response = restTemplate.exchange(resourceUrl, HttpMethod.GET,
                entity, new ParameterizedTypeReference<List<OrderDto>>() {
                });
        return response.getBody();
    }
}
