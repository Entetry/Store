package com.entetry.restclient.customerclient;

import com.entetry.storecommon.dto.AdressDto;
import com.entetry.storecommon.dto.CreditCardDto;
import com.entetry.storecommon.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class RestCustomerClient {
    private final RestTemplate restTemplate;
    @Autowired
    public RestCustomerClient(RestTemplate template) {
        this.restTemplate = template;
    }
    public CustomerDto getCustomerByUserId(String id){
        String resourceUrl = "http://localhost:9977/customers/" + id;
        ResponseEntity<CustomerDto> response = restTemplate.exchange(resourceUrl, HttpMethod.GET,
                null, new ParameterizedTypeReference<CustomerDto>() {
                });

        return  response.getBody();
    }
    public AdressDto getAddressById(String id){
        String resourceUrl = "http://localhost:9977/customers/addresses/" + id;
        ResponseEntity<AdressDto> response = restTemplate.exchange(resourceUrl, HttpMethod.GET,
                null, new ParameterizedTypeReference<AdressDto>() {
                });

        return  response.getBody();
    }
    public void saveOrUpdateAddress(AdressDto adressDto){
        String resourceUrl = "http://localhost:9977/customers/addresses";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        restTemplate.exchange(resourceUrl, HttpMethod.POST,
                new HttpEntity<>(adressDto, headers), new ParameterizedTypeReference<AdressDto>() {
                });
    }
    public void updateCustomer(CustomerDto customerDto){
        String resourceUrl = "http://localhost:9977/customers";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        restTemplate.exchange(resourceUrl, HttpMethod.PUT,
                new HttpEntity<>(customerDto, headers), new ParameterizedTypeReference<CustomerDto>() {
                });

    }
    public CreditCardDto getCreditCardById(String id){
        String resourceUrl = "http://localhost:9977/customers/creditcards/" + id;
        ResponseEntity<CreditCardDto> response = restTemplate.exchange(resourceUrl, HttpMethod.GET,
                null, new ParameterizedTypeReference<CreditCardDto>() {
                });
        return  response.getBody();
    }
    public void saveOrUpdateCreditCard(CreditCardDto creditCardDto){
        String resourceUrl = "http://localhost:9977/customers/creditcards";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        restTemplate.exchange(resourceUrl, HttpMethod.POST,
                new HttpEntity<>(creditCardDto, headers), new ParameterizedTypeReference<CreditCardDto>() {
                });
    }
}
