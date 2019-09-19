package com.entetry.restclient.userclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(resourceUrl)
                .queryParam("username", username);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<UserDetails> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
                entity, new ParameterizedTypeReference<UserDetails>() {
                });
        UserDetails userDetails=response.getBody();
        return userDetails;
    }

}
