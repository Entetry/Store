package com.entetry.store.service;

import com.entetry.store.entity.Customer;
import com.entetry.store.entity.Order;
import com.entetry.store.entity.OrderItem;
import com.entetry.store.entity.User;
import com.entetry.store.exception.OrderNotFoundException;
import com.entetry.store.exception.UserNotFoundException;
import com.entetry.store.mapper.*;
import com.entetry.store.persistense.CustomerRepository;
import com.entetry.store.persistense.DesignerRepository;
import com.entetry.store.persistense.OrderRepository;
import com.entetry.store.persistense.UserRepository;
import com.entetry.storecommon.CustomAuthentication;
import com.entetry.storecommon.dto.DesignerDto;
import com.entetry.storecommon.dto.ItemDto;
import com.entetry.storecommon.dto.OrderDto;
import com.entetry.storecommon.dto.ShoppingCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderServiceImpl {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final DesignerMapper designerMapper;
    private final DesignerRepository designerRepository;
    private final ItemMapper itemMapper;
    private final SizeMapper sizeMapper;
    private final UserRepository userRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper,
                            CustomerRepository customerRepository, CustomerMapper customerMapper,
                            DesignerMapper designerMapper, DesignerRepository designerRepository,
                            ItemMapper itemMapper, SizeMapper sizeMapper,
                            UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
        this.designerMapper = designerMapper;
        this.designerRepository = designerRepository;
        this.itemMapper = itemMapper;
        this.sizeMapper = sizeMapper;
        this.userRepository = userRepository;
    }

    @Transactional
    public void create(ShoppingCard shoppingCard) {
        List<ItemDto> items = shoppingCard.getItems();
        List<Order> orders = new ArrayList<>();
        List<DesignerDto> designers = items.stream().map(itemDto -> itemDto.getDesigner()).distinct().collect(Collectors.toList());
        Long userId = ((CustomAuthentication) SecurityContextHolder.getContext().getAuthentication()).getUserId();
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Customer customer = customerRepository.findCustomerByUser(user);
        for (DesignerDto designerDto : designers) {
            Order order = new Order();
            order.setId(ThreadLocalRandom.current().nextLong(1, 100000));
            order.setDesigner(designerMapper.toDesigner(designerDto));
            orders.add(order);
        }
        for (ItemDto itemDto : items) {
            Order order = orders.stream().filter(order1 -> order1.getDesigner().getId().equals(itemDto.getDesigner().getId())).findFirst().get();
            order.addItem(itemMapper.toItem(itemDto), sizeMapper.toSize(itemDto.getItemSizes().get(0).getSize()), itemDto.getItemSizes().get(0).getQuantity());
        }
        for (Order order : orders) {
            BigDecimal totalCost = BigDecimal.ZERO;
            for (OrderItem orderItem : order.getItems()) {
                totalCost = totalCost.add(orderItem.getItem().getPrice());
            }
            order.setOrderStatus("Paid");
            order.setCost(totalCost);
            order.setCustomer(customer);
            order.setOrderDate(new Date());
            try {
                order = orderRepository.save(order);
            } catch (Exception e) {
                LOGGER.error("Order creating exception!", e);
                throw e;
            }
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
