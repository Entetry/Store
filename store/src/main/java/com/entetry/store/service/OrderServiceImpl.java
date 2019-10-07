package com.entetry.store.service;

import com.entetry.store.entity.Order;
import com.entetry.store.exception.OrderNotFoundException;
import com.entetry.store.mapper.CustomerMapper;
import com.entetry.store.mapper.OrderMapper;
import com.entetry.store.persistense.CustomerRepository;
import com.entetry.store.persistense.OrderRepository;
import com.entetry.storecommon.dto.DesignerDto;
import com.entetry.storecommon.dto.ItemDto;
import com.entetry.storecommon.dto.OrderDto;
import com.entetry.storecommon.dto.ShoppingCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderServiceImpl {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper,
                            CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public void create(ShoppingCard shoppingCard) {
        List<ItemDto> items = shoppingCard.getItems();
        List<OrderDto> orders = new ArrayList<>();
        List<DesignerDto> designers = items.stream().map(itemDto -> itemDto.getDesigner()).distinct().collect(Collectors.toList());
        for (DesignerDto designerDto : designers) {
            OrderDto orderDto = new OrderDto();
            orderDto.setDesigner(designerDto);
            orders.add(orderDto);
        }
        for (ItemDto itemDto : items) {
            OrderDto orderDto = orders.stream().filter(orderDto1 -> orderDto1.getDesigner().getId().equals(itemDto.getDesigner().getId())).findFirst().get();
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
            throw e;
        }
    }

    @Transactional
    public void delete(String id) {
        Order order = orderRepository.findById(Long.parseLong(id)).orElseThrow(OrderNotFoundException::new);
        try {
            orderRepository.delete(order);
        } catch (Exception e) {
            LOGGER.error("an exception occured!", e);
            throw e;
        }
    }

    public List<OrderDto> getAllOrders() {
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false).map(orderMapper::toOrderDto).collect(Collectors.toList());
    }
}
