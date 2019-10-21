package com.entetry.store.controllers;

import com.entetry.store.exception.OrderNotFoundException;
import com.entetry.store.service.OrderServiceImpl;
import com.entetry.storecommon.dto.OrderDto;
import com.entetry.storecommon.dto.ShoppingCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
public class OrderController {
    private final OrderServiceImpl orderService;

    @Autowired
    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/orders")
    public void create(@RequestBody ShoppingCard shoppingCard) {
        try {
            orderService.create(shoppingCard);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @DeleteMapping("/orders/{id}")
    public void delete(@PathVariable String id) {
        try {
            orderService.delete(id);
        } catch (OrderNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @PutMapping("/orders")
    public void update(@RequestBody OrderDto orderDto) {
        try {
            orderService.update(orderDto);
        } catch (OrderNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
    @GetMapping("/orders/{id}")
    public OrderDto getCreditCardById(@PathVariable String id) {
        try {
            return orderService.getOrderById(id);
        } catch (OrderNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
    }
}
