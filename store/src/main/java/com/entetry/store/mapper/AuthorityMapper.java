package com.entetry.store.mapper;

import com.entetry.store.entity.Authority;
import com.entetry.store.entity.Role;
import com.entetry.storecommon.dto.AuthorityDto;
import com.entetry.storecommon.dto.RoleDto;
import org.springframework.stereotype.Component;

@Component
public class AuthorityMapper {
    public  AuthorityDto toAuthorityDto(Authority authority) {
        AuthorityDto authorityDto = new AuthorityDto();
        authorityDto.setId(authority.getId());
        authorityDto.setName(authority.getName());
        RoleDto roleDto = new RoleDto();
        roleDto.setId(authority.getRole().getId());
        roleDto.setRolename(authority.getRole().getRolename());
        authorityDto.setRole(roleDto);
        return authorityDto;
    }

    public  Authority toAuthority(AuthorityDto authorityDto) {
        Authority authority = new Authority();
        authority.setId(authorityDto.getId());
        authority.setName(authorityDto.getName());
        Role role = new Role();
        role.setId(authorityDto.getRole().getId());
        role.setRolename(authorityDto.getRole().getRolename());
        authority.setRole(role);
        return authority;
    }
}
