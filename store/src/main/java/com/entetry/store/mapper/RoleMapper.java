package com.entetry.store.mapper;

import com.entetry.store.entity.Role;
import com.entetry.storecommon.dto.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RoleMapper {
    private final AuthorityMapper authorityMapper;

    public RoleMapper(AuthorityMapper authorityMapper) {
        this.authorityMapper = authorityMapper;
    }

    public RoleDto toRoleDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setRolename(role.getRolename());
        roleDto.setAuthorities(role.getAuthorities().stream().map(authorityMapper::toAuthorityDto).collect(Collectors.toList()));
        return roleDto;
    }

    public Role toRole(RoleDto roleDto) {
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setRolename(roleDto.getRolename());
        role.setAuthorities(roleDto.getAuthorities().stream().map(authorityMapper::toAuthority).collect(Collectors.toList()));
        return role;
    }
}
