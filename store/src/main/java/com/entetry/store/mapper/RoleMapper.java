package com.entetry.store.mapper;

import com.entetry.store.entity.Role;
import com.entetry.storecommon.dto.RoleDto;

import java.util.stream.Collectors;

public class RoleMapper {
    public static RoleDto toRoleDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setRolename(role.getRolename());
        roleDto.setAuthorities(role.getAuthorities().stream().map(AuthorityMapper::toAuthorityDto).collect(Collectors.toList()));
        return roleDto;
    }

    public static Role toRole(RoleDto roleDto) {
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setRolename(roleDto.getRolename());
        role.setAuthorities(roleDto.getAuthorities().stream().map(AuthorityMapper::toAuthority).collect(Collectors.toList()));
        return role;
    }
}
