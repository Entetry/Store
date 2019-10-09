package com.entetry.store.service;

import com.entetry.store.entity.Customer;
import com.entetry.store.entity.User;
import com.entetry.store.exception.CustomerNotFoundException;
import com.entetry.store.exception.UserNotFoundException;
import com.entetry.store.mapper.AdressMapper;
import com.entetry.store.mapper.CreditCardMapper;
import com.entetry.store.mapper.CustomerMapper;
import com.entetry.store.persistense.CustomerRepository;
import com.entetry.store.persistense.UserRepository;
import com.entetry.storecommon.dto.AdressDto;
import com.entetry.storecommon.dto.CreditCardDto;
import com.entetry.storecommon.dto.CustomerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CustomerServiceImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(DesignerServiceImpl.class);
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final AdressMapper adressMapper;
    private final CreditCardMapper creditCardMapper;
    private final UserRepository userRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper,
                               AdressMapper adressMapper, CreditCardMapper creditCardMapper,
                               UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.adressMapper = adressMapper;
        this.creditCardMapper = creditCardMapper;
        this.userRepository = userRepository;
    }

    @Transactional
    public void create(CustomerDto customerDto) {
        try {
            customerRepository.save(customerMapper.toCustomer(customerDto));
        } catch (Exception e) {
            LOGGER.error("an exception occurred!", e);
            throw e;
        }
    }

    @Transactional
    public void update(CustomerDto customerDto) {
        Customer customer = customerRepository.findById(customerDto.getId()).orElseThrow(CustomerNotFoundException::new);
        Customer updatedCustomer = customerMapper.toCustomer(customerDto);
        try {
            customerRepository.save(updatedCustomer);
        } catch (Exception e) {
            LOGGER.error("an exception occured!", e);
            throw e;
        }
    }

    @Transactional
    public CustomerDto getCustomerByUserId(String id) {
        User user = userRepository.findById(Long.parseLong(id)).orElseThrow(UserNotFoundException::new);
        return customerMapper.toCustomerDto(customerRepository.findCustomerByUser(user));
    }

    @Transactional
    public void delete(String id) {
        Customer customer = customerRepository.findById(Long.parseLong(id)).orElseThrow(CustomerNotFoundException::new);
        try {
            customerRepository.delete(customer);
        } catch (Exception e) {
            LOGGER.error("an exception occured!", e);
            throw e;
        }
    }

    public List<CustomerDto> getAllCustomers() {
        return StreamSupport.stream(customerRepository.findAll().spliterator(), false).map(customerMapper::toCustomerDto).collect(Collectors.toList());
    }

    @Transactional
    public void addAddressToCustomer(AdressDto adressDto) {
        Customer customer = customerRepository.findById(adressDto.getCustomer().getId()).orElseThrow(CustomerNotFoundException::new);
        customer.addAdress(adressMapper.toAdress(adressDto));
        try {
            customerRepository.save(customer);
        } catch (Exception e) {
            LOGGER.error("an exception occured!", e);
            throw e;
        }
    }

    @Transactional
    public void addCreditCardtoCustomer(CreditCardDto creditCardDto) {
        Customer customer = customerRepository.findById(creditCardDto.getCustomer().getId()).orElseThrow(CustomerNotFoundException::new);
        customer.addCreditCard(creditCardMapper.toCreditCard(creditCardDto));
        try {
            customerRepository.save(customer);
        } catch (Exception e) {
            LOGGER.error("an exception occured!", e);
            throw e;
        }
    }

}
