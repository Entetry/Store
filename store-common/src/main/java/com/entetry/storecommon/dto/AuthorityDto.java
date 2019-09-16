package com.entetry.storecommon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class AuthorityDto implements Serializable {
    @JsonProperty
    private Long id;
    @JsonProperty
    private String name;
    @JsonProperty
    private RoleDto role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoleDto getRole() {
        return role;
    }

    public void setRole(RoleDto role) {
        this.role = role;
    }
}
