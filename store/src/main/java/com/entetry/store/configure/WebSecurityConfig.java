package com.entetry.store.configure;

import com.entetry.store.security.CustomAuthenticationProvider;
import com.entetry.store.security.MySavedRequestAwareAuthenticationSuccessHandler;
import com.entetry.store.security.RequestFiler;
import com.entetry.store.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@ComponentScan({"com.entetry.store"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService userDetailsService;
    //    private AuthenticationSuccessHandlerImpl successHandler;
    private final DataSource dataSource;
    private MySavedRequestAwareAuthenticationSuccessHandler mySuccessHandler;
    private final CustomAuthenticationProvider customAuthenticationProvider;
    @Autowired
    public WebSecurityConfig(CustomAuthenticationProvider customAuthenticationProvider,
                             CustomUserDetailsService detailsService, DataSource dataSource,
                             MySavedRequestAwareAuthenticationSuccessHandler mySuccessHandler) {
        super();
        this.userDetailsService = detailsService;
        this.dataSource = dataSource;
        this.mySuccessHandler = mySuccessHandler;
        this.customAuthenticationProvider=customAuthenticationProvider;
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder())
                .and()
                .authenticationProvider(customAuthenticationProvider);

//
//                .jdbcAuthentication()
//                .dataSource(dataSource);
    }


    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.addFilterBefore(new RequestFiler(authenticationManager()), BasicAuthenticationFilter.class).csrf().disable().authorizeRequests()
                .antMatchers("/login")
                .permitAll()
                .antMatchers("/user").permitAll()
                .and()
                .formLogin().successHandler(mySuccessHandler)
                .permitAll();

    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }
}
