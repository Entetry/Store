package com.entetry.store.service;

import com.entetry.store.entity.*;
import com.entetry.store.exception.ItemNotFoundException;
import com.entetry.store.exception.OrderNotFoundException;
import com.entetry.store.exception.UserNotFoundException;
import com.entetry.store.mapper.*;
import com.entetry.store.persistense.*;
import com.entetry.storecommon.CustomAuthentication;
import com.entetry.storecommon.dto.DesignerDto;
import com.entetry.storecommon.dto.OrderDto;
import com.entetry.storecommon.dto.ShoppingCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
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
    private final ItemRepository itemRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper,
                            CustomerRepository customerRepository, CustomerMapper customerMapper,
                            DesignerMapper designerMapper, DesignerRepository designerRepository,
                            ItemMapper itemMapper, SizeMapper sizeMapper,
                            UserRepository userRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
        this.designerMapper = designerMapper;
        this.designerRepository = designerRepository;
        this.itemMapper = itemMapper;
        this.sizeMapper = sizeMapper;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    @Transactional
    public OrderDto getOrderById(String id) {
        return orderMapper.toOrderDto(orderRepository.findById(Long.parseLong(id)).orElseThrow(OrderNotFoundException::new));
    }

    @Transactional
    public void create(ShoppingCard shoppingCard) {
        Map<Designer, List<Item>> designerItems = new HashMap<>();
        List<Order> orders = new ArrayList<>();
        Long userId = ((CustomAuthentication) SecurityContextHolder.getContext().getAuthentication()).getUserId();
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Customer customer = customerRepository.findCustomerByUser(user);
        List<DesignerDto> designers = shoppingCard.getItems().stream().map(itemDto -> itemDto.getDesigner()).distinct().collect(Collectors.toList());
        designers.forEach(designerDto -> designerItems.put(designerMapper.toDesigner(designerDto), new ArrayList<>()));
        shoppingCard.getItems().forEach(itemDto -> {
            Item item = itemRepository.findById(itemDto.getId()).orElseThrow(ItemNotFoundException::new);
            designerItems.get(designerMapper.toDesigner(itemDto.getDesigner())).add(item);
        });
        try {
            designerItems.forEach((designer, items) -> {
                Order order = new Order();
                order.setDesigner(designer);
                BigDecimal totalCost = BigDecimal.ZERO;
                for (Item item : items) {
                    totalCost = totalCost.add(item.getPrice());
                }
                order.setCost(totalCost);
                order.setOrderStatus("In progress");
                order.setOrderDate(new Date());
                order.setCustomer(customer);
                order = orderRepository.save(order);
                for (Item item : items) {
                    order.addItem(item, item.getItemSizes().get(0).getSize(), item.getItemSizes().get(0).getQuantity());
                }
                orderRepository.save(order);
            });
        } catch (Exception e) {
            LOGGER.error("Order creating exception!", e);
            throw e;
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
