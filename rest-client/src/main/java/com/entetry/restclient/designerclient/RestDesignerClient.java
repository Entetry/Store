package com.entetry.restclient.designerclient;

import com.entetry.storecommon.dto.BankAccountDto;
import com.entetry.storecommon.dto.DesignerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class RestDesignerClient {
    private final RestTemplate restTemplate;

    @Autowired
    public RestDesignerClient(RestTemplate template) {
        this.restTemplate = template;
    }

    public DesignerDto getDesignerByUserId(String id) {
        String resourceUrl = "http://localhost:9977/designers/" + id;
        ResponseEntity<DesignerDto> response = restTemplate.exchange(resourceUrl, HttpMethod.GET,
                null, new ParameterizedTypeReference<DesignerDto>() {
                });

        return response.getBody();
    }
    public void updateDesigner(DesignerDto designerDto) {
        String resourceUrl = "http://localhost:9977/designers";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        restTemplate.exchange(resourceUrl, HttpMethod.PUT,
                new HttpEntity<>(designerDto, headers), new ParameterizedTypeReference<DesignerDto>() {
                });
    }
    public BankAccountDto getBankAccountById(String id) {
        String resourceUrl = "http://localhost:9977/designers/bankaccounts/" + id;
        ResponseEntity<BankAccountDto> response = restTemplate.exchange(resourceUrl, HttpMethod.GET,
                null, new ParameterizedTypeReference<BankAccountDto>() {
                });
        return response.getBody();
    }

    public void saveOrUpdateBankAccount(BankAccountDto bankAccountDto) {
        String resourceUrl = "http://localhost:9977/designers/bankaccounts";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        restTemplate.exchange(resourceUrl, HttpMethod.POST,
                new HttpEntity<>(bankAccountDto, headers), new ParameterizedTypeReference<BankAccountDto>() {
                });
    }
    public void deleteBankAccount(String id){
        String resourceUrl = "http://localhost:9977/designers/bankaccounts/" + id;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        restTemplate.exchange(resourceUrl, HttpMethod.DELETE,
                null, new ParameterizedTypeReference<BankAccountDto>() {
                });
    }
}
