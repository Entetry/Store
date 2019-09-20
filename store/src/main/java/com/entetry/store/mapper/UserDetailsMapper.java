package com.entetry.store.mapper;

import com.entetry.storecommon.dto.UserDetailsDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserDetailsMapper {
    public UserDetailsDto toUserDetailsDto(UserDetails userDetails){
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setUsername(userDetails.getUsername());
        userDetailsDto.setPassword(userDetails.getPassword());
        userDetailsDto.setEnabled(userDetails.isEnabled());
        userDetailsDto.setAccountNonExpired(userDetails.isAccountNonExpired());
        userDetailsDto.setAccountNonLocked(userDetails.isAccountNonLocked());
        userDetailsDto.setCredentialsNonExpired(userDetails.isCredentialsNonExpired());
        userDetailsDto.setAuthorities(userDetails.getAuthorities().stream().map(detail->new SimpleGrantedAuthority(((GrantedAuthority) detail).getAuthority())).collect(Collectors.toSet()));
        return userDetailsDto;
    }
}
