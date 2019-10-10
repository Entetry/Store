package com.entetry.store.controllers;

import com.entetry.store.exception.BankAccountNotFoundException;
import com.entetry.store.exception.DesignerNotFoundException;
import com.entetry.store.service.BankAccountService;
import com.entetry.store.service.DesignerServiceImpl;
import com.entetry.storecommon.dto.BankAccountDto;
import com.entetry.storecommon.dto.DesignerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class DesignerController {
    private final DesignerServiceImpl designerService;
    private final BankAccountService bankAccountService;
    @Autowired
    public DesignerController(DesignerServiceImpl designerService,BankAccountService bankAccountService) {
        this.designerService = designerService;
        this.bankAccountService= bankAccountService;
    }

    @GetMapping("/designers")
    public List<DesignerDto> getAllDesigners() {
        return designerService.getAllDesigners();
    }
    @GetMapping("/designers/{id}")
    public DesignerDto getCustomerByUserId(@PathVariable String id) {
        try {
            return designerService.getDesignerByUserId(id);
        } catch (DesignerNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
    }
    @PostMapping("/designers")
    public void create(@RequestBody DesignerDto designerDto) {
        try {
            designerService.create(designerDto);
        } catch (DesignerNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @DeleteMapping("/designers/{id}")
    public void delete(@PathVariable String id) {
        try {
            designerService.delete(id);
        } catch (DesignerNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @PutMapping("/designers")
    public void update(@RequestBody DesignerDto designerDto) {
        try {
            designerService.update(designerDto);
        } catch (DesignerNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
    @GetMapping("/designers/bankaccounts/{id}")
    public BankAccountDto getCreditCardById(@PathVariable String id) {
        try {
            return bankAccountService.getBankAccountById(id);
        } catch (BankAccountNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
    }

    @PostMapping("/designers/bankaccounts")
    public void saveOrUpdateBankAccount(@RequestBody BankAccountDto bankAccountDto) {
        try {
            bankAccountService.create(bankAccountDto);
        } catch (BankAccountNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
    @DeleteMapping("/designers/bankaccounts/{id}")
    public void deleteBankAccount(@PathVariable String id){
        try {
            bankAccountService.delete(id);
        } catch (BankAccountNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
