package com.entetry.store.mapper;

import com.entetry.store.entity.Customer;
import com.entetry.store.entity.User;
import com.entetry.storecommon.dto.CustomerDto;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@Component
public class CustomerMapper {
    private final UserMapper userMapper;
    private final AdressMapper adressMapper;
    private final CreditCardMapper creditCardMapper;
    public CustomerMapper(UserMapper userMapper,AdressMapper adressMapper,CreditCardMapper creditCardMapper){
        this.userMapper=userMapper;
        this.adressMapper=adressMapper;
        this.creditCardMapper=creditCardMapper;
    }
    public  CustomerDto toCustomerDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setDateOfBirth(customer.getDateOfBirth());
        customerDto.setFirstname(customer.getFirstname());
        customerDto.setLastname(customer.getLastname());
        customerDto.setPhone(customer.getPhone());
        customerDto.setSex(customer.getSex());
        customerDto.setStatus(customer.getStatus());
        customerDto.setUser(userMapper.toUserDto(customer.getUser()));
        customerDto.setAdresses(customer.getAdresses().stream().map(adressMapper::toAdressDto).collect(Collectors.toList()));
        customerDto.setCreditCards(customer.getCreditCards().stream().map(creditCardMapper::toCreditCardDto).collect(Collectors.toSet()));
        return customerDto;
    }
    public  Customer toCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setDateOfBirth(customerDto.getDateOfBirth());
        customer.setFirstname(customerDto.getFirstname());
        customer.setLastname(customerDto.getLastname());
        customer.setPhone(customerDto.getPhone());
        customer.setSex(customerDto.getSex());
        customer.setStatus(customerDto.getStatus());
        customer.setUser(userMapper.toUser(customerDto.getUser()));
        customer.setAdresses(customerDto.getAdresses().stream().map(adressMapper::toAdress).collect(Collectors.toList()));
        customer.setCreditCards(customerDto.getCreditCards().stream().map(creditCardMapper::toCreditCard).collect(Collectors.toSet()));
        return customer;
    }
}
