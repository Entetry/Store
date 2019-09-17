package com.entetry.store.controllers;

import com.entetry.store.exception.OrderNotFoundException;
import com.entetry.store.service.OrderServiceImpl;
import com.entetry.storecommon.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
public class OrderController {
    private final OrderServiceImpl orderService;
    @Autowired
    public OrderController(OrderServiceImpl orderService){
        this.orderService=orderService;
    }
    @GetMapping("/order")
    public List<OrderDto> getAllOrders(){
        return orderService.getAllOrders();
    }
    @PostMapping("/order")
    public void create(@RequestBody OrderDto orderDto){
        try {
            orderService.create(orderDto);
        } catch (OrderNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
    }
    @DeleteMapping("/order")
    public void delete(@RequestBody OrderDto orderDto) {
        try {
            orderService.delete(orderDto);
        } catch (OrderNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
    }
    @PutMapping("/order")
    public void update(@RequestBody OrderDto orderDto) {
        try {
            orderService.update(orderDto);
        } catch (OrderNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
    }
}
