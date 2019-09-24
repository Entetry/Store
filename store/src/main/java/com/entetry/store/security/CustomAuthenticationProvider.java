package com.entetry.store.security;

import com.entetry.store.entity.User;
import com.entetry.store.exception.UserNotFoundException;
import com.entetry.store.persistense.UserRepository;
import com.entetry.storecommon.CustomAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final UserRepository userRepository;
    @Autowired
    public CustomAuthenticationProvider(UserRepository userRepository) {
        super();
        this.userRepository=userRepository;
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomAuthentication customAuthenticatiom = (CustomAuthentication) authentication;
        Long userId = customAuthenticatiom.getUserId();
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Collection<? extends GrantedAuthority> authorities = user.getRoles().stream()
                .flatMap(x -> x.getAuthorities().stream())
                .map(authority -> new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toList());
        return new CustomAuthentication(user.getUsername(),user.getPasswordHash(),userId,authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(CustomAuthentication.class);
    }
}

