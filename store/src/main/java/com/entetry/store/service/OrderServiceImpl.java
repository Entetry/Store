package com.entetry.store.service;

import com.entetry.store.entity.Order;
import com.entetry.store.exception.OrderNotFoundException;
import com.entetry.store.mapper.OrderMapper;
import com.entetry.store.persistense.OrderRepository;
import com.entetry.storecommon.dto.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderServiceImpl {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Transactional
    public void create(OrderDto orderDto) {
        try {
            orderRepository.save(orderMapper.toOrder(orderDto));
        } catch (Exception e) {
            LOGGER.error("an exception occurred!", e);
        }
    }

    @Transactional
    public void update(OrderDto orderDto) {
        Order order = orderRepository.findById(orderDto.getId()).orElseThrow(OrderNotFoundException::new);
        Order updatedOrder = orderMapper.toOrder(orderDto);
        try {
            orderRepository.save(updatedOrder);
        } catch (Exception e) {
            LOGGER.error("an exception occured!", e);
        }
    }

    @Transactional
    public void delete(OrderDto orderDto) {
        try {
            orderRepository.delete(orderMapper.toOrder(orderDto));
        } catch (Exception e) {
            LOGGER.error("an exception occured!", e);
        }
    }

    public List<OrderDto> getAllOrders() {
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false).map(orderMapper::toOrderDto).collect(Collectors.toList());
    }
}
