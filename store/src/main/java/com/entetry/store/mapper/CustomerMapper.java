package com.entetry.store.mapper;

import com.entetry.store.entity.Customer;
import com.entetry.storecommon.dto.CustomerDto;

import java.util.stream.Collectors;

public class CustomerMapper {
    public static CustomerDto toCustomerDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setDateOfBirth(customer.getDateOfBirth());
        customerDto.setFirstname(customer.getFirstname());
        customerDto.setLastname(customer.getLastname());
        customerDto.setPhone(customer.getPhone());
        customerDto.setSex(customer.getSex());
        customerDto.setStatus(customer.getStatus());
        customerDto.setUser(UserMapper.toUserDto(customer.getUser()));
        customerDto.setAdresses(customer.getAdresses().stream().map(AdressMapper::toAdressDto).collect(Collectors.toList()));

        return customerDto;
    }

    public static Customer toCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        return customer;
    }
}
