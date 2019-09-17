package com.entetry.store.service;

import com.entetry.store.entity.Customer;
import com.entetry.store.exception.CustomerNotFoundException;
import com.entetry.store.mapper.AdressMapper;
import com.entetry.store.mapper.CustomerMapper;
import com.entetry.store.persistense.CustomerRepository;
import com.entetry.storecommon.dto.AdressDto;
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
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,CustomerMapper customerMapper,AdressMapper adressMapper) {
        this.customerRepository=customerRepository;
        this.customerMapper=customerMapper;
        this.adressMapper=adressMapper;
    }
    @Transactional
    public void create(CustomerDto customerDto){
        try{
            customerRepository.save(customerMapper.toCustomer(customerDto));
        }
        catch (Exception e){
            LOGGER.error("an exception occurred!", e);
        }
    }
    @Transactional
    public void update(CustomerDto customerDto){
        Customer designer = customerRepository.findById(customerDto.getId()).orElseThrow(CustomerNotFoundException::new);
        Customer updatedDesigner= customerMapper.toCustomer(customerDto);
        try {
            customerRepository.save(updatedDesigner);
        } catch (Exception e) {
            LOGGER.error("an exception occured!", e);
        }
    }
    @Transactional
    public void delete(CustomerDto customerDto){
        try {
            customerRepository.delete(customerMapper.toCustomer(customerDto));
        } catch (Exception e){
            LOGGER.error("an exception occured!", e);
        }
    }
    public List<CustomerDto> getAllCustomers(){
        return StreamSupport.stream(customerRepository.findAll().spliterator(),false).map(customerMapper::toCustomerDto).collect(Collectors.toList());
    }
    @Transactional
    public void addAddressToCustomer(AdressDto adressDto){
        Customer customer=customerMapper.toCustomer(adressDto.getCustomer());
        customer.addAdress(adressMapper.toAdress(adressDto));
        try {
            customerRepository.save(customer);
        } catch (Exception e){
            LOGGER.error("an exception occured!", e);
        }
    }
}
