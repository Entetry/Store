package com.entetry.store.mapper;

import com.entetry.store.entity.CreditCard;
import com.entetry.store.entity.Customer;
import com.entetry.storecommon.dto.CreditCardDto;
import com.entetry.storecommon.dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CreditCardMapper {
    private final UserMapper userMapper;
    public CreditCardMapper(UserMapper userMapper){
        this.userMapper=userMapper;
    }
    public CreditCardDto toCreditCardDto(CreditCard creditCard){
        CreditCardDto creditCardDto = new CreditCardDto();
        creditCardDto.setId(creditCard.getId());
        creditCardDto.setBalance(creditCard.getBalance());
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(creditCard.getCustomer().getId());
        customerDto.setUser(userMapper.toUserDto(creditCard.getCustomer().getUser()));
        customerDto.setStatus(creditCard.getCustomer().getStatus());
        customerDto.setSex(creditCard.getCustomer().getSex());
        customerDto.setFirstname(creditCard.getCustomer().getFirstname());
        customerDto.setLastname(creditCard.getCustomer().getLastname());
        customerDto.setDateOfBirth(creditCard.getCustomer().getDateOfBirth());
        customerDto.setPhone(creditCard.getCustomer().getPhone());
        creditCardDto.setCustomer(customerDto);
        return creditCardDto;
    }
    public CreditCard toCreditCard(CreditCardDto creditCardDto){
        CreditCard creditCard = new CreditCard();
        creditCard.setId(creditCard.getId());
        creditCard.setBalance(creditCard.getBalance());
        Customer customer = new Customer();
        customer.setId(creditCardDto.getCustomer().getId());
        customer.setUser(userMapper.toUser(creditCardDto.getCustomer().getUser()));
        customer.setStatus(creditCardDto.getCustomer().getStatus());
        customer.setSex(creditCardDto.getCustomer().getSex());
        customer.setFirstname(creditCardDto.getCustomer().getFirstname());
        customer.setLastname(creditCardDto.getCustomer().getLastname());
        customer.setDateOfBirth(creditCardDto.getCustomer().getDateOfBirth());
        customer.setPhone(creditCardDto.getCustomer().getPhone());
        creditCard.setCustomer(customer);
        return creditCard;
    }
}
