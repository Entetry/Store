package com.entetry.storecommon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class UserDto implements Serializable {
    @JsonProperty
    private Long id;
    @JsonProperty
    private String username;
    @JsonProperty
    private String passwordHash;
    @JsonProperty
    private String email;
    @JsonProperty
    private boolean enabled;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
