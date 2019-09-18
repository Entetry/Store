package com.entetry.store.controllers;

import com.entetry.store.exception.CustomerNotFoundException;
import com.entetry.store.service.CustomerServiceImpl;
import com.entetry.storecommon.dto.AdressDto;
import com.entetry.storecommon.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CustomerController {
    private final CustomerServiceImpl customerService;
    @Autowired
    public CustomerController(CustomerServiceImpl customerService){
        this.customerService=customerService;
    }
    @GetMapping("/customer")
    public List<CustomerDto> getAllDesigners(){
        return customerService.getAllCustomers();
    }
    @PostMapping("/customer")
    public void create(@RequestBody CustomerDto customerDto){
            customerService.create(customerDto);
    }
    @DeleteMapping("/customer")
    public void delete(@RequestBody CustomerDto customerDto) {
        try {
            customerService.delete(customerDto);
        } catch (CustomerNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
    }
    @PutMapping("/customer")
    public void update(@RequestBody CustomerDto customerDto) {
        try {
            customerService.update(customerDto);
        } catch (CustomerNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
    }
    @PostMapping("/customer/address")
    public void addAddressToCustomer(@RequestBody AdressDto adressDto){
        try {
            customerService.addAddressToCustomer(adressDto);
        } catch (CustomerNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
    }
}
