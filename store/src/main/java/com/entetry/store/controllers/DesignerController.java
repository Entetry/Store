package com.entetry.store.controllers;

import com.entetry.store.exception.DesignerNotFoundException;
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

    @Autowired
    public DesignerController(DesignerServiceImpl designerService) {
        this.designerService = designerService;
    }

    @GetMapping("/designers")
    public List<DesignerDto> getAllDesigners() {
        return designerService.getAllDesigners();
    }

    @PostMapping("/designers")
    public void create(@RequestBody DesignerDto designerDto) {
        try {
            designerService.create(designerDto);
        } catch (DesignerNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }

    @DeleteMapping("/designers/{id}")
    public void delete(@PathVariable String id) {
        try {
            designerService.delete(id);
        } catch (DesignerNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }

    @PutMapping("/designers")
    public void update(@RequestBody DesignerDto designerDto) {
        try {
            designerService.update(designerDto);
        } catch (DesignerNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }

    @PostMapping("/designers/bankaccount")
    public void addCreditCardToCustomer(@RequestBody BankAccountDto bankAccountDto) {
        try {
            designerService.addBankAccount(bankAccountDto);
        } catch (DesignerNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }
}
