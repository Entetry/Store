package com.entetry.storecommon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserDetailsDto implements UserDetails {
    @JsonProperty
    private String password;
    @JsonProperty
    private  String username;
    @JsonProperty
    private  Set<GrantedAuthority> authorities;
    @JsonProperty
    private  boolean accountNonExpired;
    @JsonProperty
    private  boolean accountNonLocked;
    @JsonProperty
    private  boolean credentialsNonExpired;
    @JsonProperty
    private  boolean enabled;
    @JsonProperty
    private Long userId;

    private UserDetailsDto(){}
    public UserDetailsDto(Long userId,String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        if (username != null && !"".equals(username) && password != null) {
            this.username = username;
            this.password = password;
            this.enabled = enabled;
            this.accountNonExpired = accountNonExpired;
            this.credentialsNonExpired = credentialsNonExpired;
            this.accountNonLocked = accountNonLocked;
            this.authorities = new HashSet<>(authorities);
            this.userId = userId;
        } else {
            throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
        }
    }
    public UserDetailsDto(Long userId,String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this(userId,username, password, true, true, true, true, authorities);
    }

    public Long getUserId() {
        return userId;
    }

//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }

    @Override
    public String getPassword() {
        return password;
    }

//    public void setPassword(String password) {
//        this.password = password;
//    }

    @Override
    public String getUsername() {
        return username;
    }

//    public void setUsername(String username) {
//        this.username = username;
//    }

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }

//    public void setAuthorities(Set<GrantedAuthority> authorities) {
//        this.authorities = authorities;
//    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

//    public void setAccountNonExpired(boolean accountNonExpired) {
//        this.accountNonExpired = accountNonExpired;
//    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

//    public void setAccountNonLocked(boolean accountNonLocked) {
//        this.accountNonLocked = accountNonLocked;
//    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

//    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
//        this.credentialsNonExpired = credentialsNonExpired;
//    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

//    public void setEnabled(boolean enabled) {
//        this.enabled = enabled;
//    }
}
