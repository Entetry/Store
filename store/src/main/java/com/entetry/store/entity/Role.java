package com.entetry.store.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Role")
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue
    private Long id;
    @Column(name = "role_name")
    private String rolename;
    @ElementCollection(targetClass=Authority.class)
    private List<Authority> authorities = new ArrayList<>();
    @ManyToMany(targetEntity = User.class,mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
