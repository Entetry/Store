package com.entetry.restclient.itemclient;

import com.entetry.storecommon.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class RestItemClient {
    private final RestTemplate restTemplate;
    @Autowired
    public RestItemClient(RestTemplate template) {
        this.restTemplate = template;
    }
    public List<ItemDto> getAllItems(){
        String resourceUrl = "http://localhost:9977/items";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<List<ItemDto>> response = restTemplate.exchange(resourceUrl, HttpMethod.GET,
                entity, new ParameterizedTypeReference<List<ItemDto>>() {
                });
        return response.getBody();
    }
    public ItemDto getItemByName(String name){
        String resourceUrl = "http://localhost:9977/items/" + name;
        ResponseEntity<ItemDto> response = restTemplate.exchange(resourceUrl, HttpMethod.GET,
                null, new ParameterizedTypeReference<ItemDto>() {
                });

        return  response.getBody();
    }
}
