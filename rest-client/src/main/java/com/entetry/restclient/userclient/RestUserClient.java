package com.entetry.restclient.userclient;

import com.entetry.storecommon.dto.UserDetailsDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Service
public class RestUserClient implements UserDetailsService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    @Autowired
    public RestUserClient(RestTemplate template,ObjectMapper objectMapper) {
        this.restTemplate = template;
        this.objectMapper=objectMapper;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String resourceUrl = "http://localhost:9977/users/userdetails";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(resourceUrl)
                .queryParam("username", username);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<UserDetailsDto> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
                entity, new ParameterizedTypeReference<UserDetailsDto>() {
                });
        UserDetails userDetails=response.getBody();
        return userDetails;
    }
    public org.springframework.security.core.userdetails.User getUser(UserDetails userDetails){
        String jsonObj="";
        User user = null;
        try{
            jsonObj=objectMapper.writeValueAsString(userDetails);
            user = objectMapper.readValue(jsonObj,user.getClass());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

}
