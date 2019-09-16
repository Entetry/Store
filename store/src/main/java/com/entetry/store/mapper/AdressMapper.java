package com.entetry.store.mapper;

import com.entetry.store.entity.Adress;
import com.entetry.store.entity.Customer;
import com.entetry.storecommon.dto.AdressDto;
import com.entetry.storecommon.dto.CustomerDto;

public class AdressMapper {
    public static AdressDto toAdressDto(Adress adress) {
        AdressDto adressDto = new AdressDto();
        adressDto.setAdress(adress.getAdress());
        adressDto.setCity(adress.getCity());
        adressDto.setRegion(adress.getRegion());
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(adress.getCustomer().getId());
        customerDto.setDateOfBirth(adress.getCustomer().getDateOfBirth());
        customerDto.setFirstname(adress.getCustomer().getFirstname());
        customerDto.setLastname(adress.getCustomer().getLastname());
        customerDto.setPhone(adress.getCustomer().getPhone());
        customerDto.setSex(adress.getCustomer().getSex());
        customerDto.setStatus(adress.getCustomer().getStatus());
        customerDto.setUser(UserMapper.toUserDto(adress.getCustomer().getUser()));
        adressDto.setCustomer(customerDto);
        adressDto.setEmail(adress.getEmail());
        adressDto.setFirstname(adress.getFirstname());
        adressDto.setLastname(adress.getLastname());
        adressDto.setPhone(adress.getPhone());
        adressDto.setPostIndex(adress.getPostIndex());
        adressDto.setId(adress.getId());
        return adressDto;
    }

    public static Adress toAdress(AdressDto adressDto) {
        Adress adress = new Adress();
        adress.setCustomer(CustomerMapper.toCustomer(adressDto.getCustomer()));
        adress.setAdress(adressDto.getAdress());
        adress.setCity(adressDto.getCity());
        adress.setEmail(adressDto.getEmail());
        adress.setRegion(adressDto.getRegion());
        adress.setFirstname(adressDto.getFirstname());
        adress.setLastname(adressDto.getLastname());
        adress.setPhone(adressDto.getPhone());
        adress.setPostIndex(adressDto.getPostIndex());
        adress.setId(adressDto.getId());
        return adress;
    }
}
