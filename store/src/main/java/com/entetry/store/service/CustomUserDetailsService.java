package com.entetry.store.service;

import com.entetry.store.entity.User;
import com.entetry.store.persistense.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final LocalContainerEntityManagerFactoryBean entityManagerFactory;
    private final WebApplicationContext applicationContext;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, LocalContainerEntityManagerFactoryBean entityManagerFactory, WebApplicationContext applicationContext) {
        super();
        this.userRepository = userRepository;
        this.entityManagerFactory = entityManagerFactory;
        this.applicationContext = applicationContext;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EntityManager entityManager = entityManagerFactory.getNativeEntityManagerFactory().createEntityManager();
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("invalid username or password");
        }
        User user = userOptional.get();
        Collection<? extends GrantedAuthority> authorities = user.getRoles().stream()
                .flatMap(x -> x.getAuthorities().stream())
                .map(authority -> new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toList());
        entityManager.close();
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPasswordHash(),
                authorities);
    }

}
