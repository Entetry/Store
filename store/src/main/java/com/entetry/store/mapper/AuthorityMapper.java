package com.entetry.store.mapper;

import com.entetry.store.entity.Authority;
import com.entetry.storecommon.dto.AuthorityDto;
import org.springframework.stereotype.Component;

@Component
public class AuthorityMapper {
    public AuthorityDto toAuthorityDto(Authority authority) {
        AuthorityDto authorityDto = new AuthorityDto();
        authorityDto.setId(authority.getId());
        authorityDto.setName(authority.getName());
        return authorityDto;
    }

    public Authority toAuthority(AuthorityDto authorityDto) {
        Authority authority = new Authority();
        authority.setId(authorityDto.getId());
        authority.setName(authorityDto.getName());
        return authority;
    }
}
