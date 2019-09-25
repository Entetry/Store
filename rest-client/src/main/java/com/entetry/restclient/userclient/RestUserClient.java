package com.entetry.restclient.userclient;

import com.entetry.storecommon.dto.UserDetailsDto;
import com.entetry.storecommon.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class RestUserClient implements UserDetailsService {
    private final RestTemplate restTemplate;
    @Autowired
    public RestUserClient(RestTemplate template) {
        this.restTemplate = template;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String resourceUrl = "http://localhost:9977/users/userdetails";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("AuthorizationRequest","authorization");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(resourceUrl)
                .queryParam("username", username);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<UserDetailsDto> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
                entity, new ParameterizedTypeReference<UserDetailsDto>() {
                });
        UserDetailsDto userDetails=response.getBody();
        return userDetails;
    }
    public List<UserDto> getAllUsers(){
        String resourceUrl = "http://localhost:9977/users";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<List<UserDto>> response = restTemplate.exchange(resourceUrl, HttpMethod.GET,
                entity, new ParameterizedTypeReference<List<UserDto>>() {
                });

        return response.getBody();
    }
}
