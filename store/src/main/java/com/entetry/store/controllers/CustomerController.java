package com.entetry.store.controllers;

import com.entetry.store.exception.AddressNotFoundException;
import com.entetry.store.exception.CustomerNotFoundException;
import com.entetry.store.service.AddressServiceImpl;
import com.entetry.store.service.CustomerServiceImpl;
import com.entetry.storecommon.dto.AdressDto;
import com.entetry.storecommon.dto.CreditCardDto;
import com.entetry.storecommon.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CustomerController {
    private final CustomerServiceImpl customerService;
    private final AddressServiceImpl addressService;
    @Autowired
    public CustomerController(CustomerServiceImpl customerService,AddressServiceImpl addressService) {
        this.customerService = customerService;
        this.addressService=addressService;
    }

    @GetMapping("/customers")
    public List<CustomerDto> getAllDesigners() {
        return customerService.getAllCustomers();
    }

    @PostMapping("/customers")
    public void create(@RequestBody CustomerDto customerDto) {
        customerService.create(customerDto);
    }
    @GetMapping("/customers/{id}")
    public CustomerDto getCustomerByUserId(@PathVariable String id){
        try {
            return customerService.getCustomerByUserId(id);
        } catch (CustomerNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
    }
    @DeleteMapping("/customers/{id}")
    public void delete(@PathVariable String id) {
        try {
            customerService.delete(id);
        } catch (CustomerNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }

    @PutMapping("/customers")
    public void update(@RequestBody CustomerDto customerDto) {
        try {
            customerService.update(customerDto);
        } catch (CustomerNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }

    @PostMapping("/customers/address")
    public void addAddressToCustomer(@RequestBody AdressDto adressDto) {
        try {
            customerService.addAddressToCustomer(adressDto);
        } catch (CustomerNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }

    @PostMapping("/customers/card")
    public void addCreditCardToCustomer(@RequestBody CreditCardDto creditCardDto) {
        try {
            customerService.addCreditCardtoCustomer(creditCardDto);
        } catch (CustomerNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }
    //Address methods
    @GetMapping("/customers/addresses/{id}")
    public AdressDto getAddressById(@PathVariable String id){
        try {
            return addressService.getAddressById(id);
        } catch (AddressNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
    }
    @PostMapping("/customers/addresses")
    public void saveOrUpdateAddress(@RequestBody AdressDto adressDto) {
        try {
            addressService.create(adressDto);
        } catch (CustomerNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }
}
