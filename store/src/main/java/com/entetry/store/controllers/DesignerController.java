package com.entetry.store.controllers;

import com.entetry.store.exception.DesignerNotFoundException;
import com.entetry.store.service.DesignerServiceImpl;
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
    public DesignerController(DesignerServiceImpl designerService){
        this.designerService=designerService;
    }
    @GetMapping("/designer")
    public List<DesignerDto> getAllDesigners(){
        return designerService.getAllDesigners();
    }
    @PostMapping("/designer")
    public void create(@RequestBody DesignerDto designerDto){
        try {
            designerService.create(designerDto);
        } catch (DesignerNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
    }
    @DeleteMapping("/designer")
    public void delete(@RequestBody DesignerDto designerDto) {
        try {
            designerService.delete(designerDto);
        } catch (DesignerNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
    }
    @PutMapping("/designer")
    public void update(@RequestBody DesignerDto designerDto) {
        try {
            designerService.update(designerDto);
        } catch (DesignerNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
    }
}
