package com.entetry.store.mapper;

import com.entetry.store.entity.Order;
import com.entetry.storecommon.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper {
//    private final DesignerMapper designerMapper;
//    private final OrderItemMapper orderItemMapper;
//    private final CustomerMapper customerMapper;
//    private final ItemMapper itemMapper;
//    @Autowired
//    public OrderMapper(DesignerMapper designerMapper,CustomerMapper customerMapper,ItemMapper itemMapper,OrderItemMapper orderItemMapper){
//        this.designerMapper=designerMapper;
//        this.itemMapper=itemMapper;
//        this.customerMapper=customerMapper;
//        this.orderItemMapper=orderItemMapper;
//    }
//    public OrderDto toOrderDto(Order order){
//        OrderDto orderDto = new OrderDto();
//        orderDto.setId(order.getId());
//        orderDto.setCustomer(customerMapper.toCustomerDto(order.getCustomer()));
//        orderDto.setDesigner(designerMapper.toDesignerDto(order.getDesigner()));
//        orderDto.setOrderDate(order.getOrderDate());
//        orderDto.setCost(order.getCost());
//        orderDto.setOrderStatus(order.getOrderStatus());
//        //orderDto.setItems(order.getItems().stream().map(orderItemMapper::toOrderItemDto).collect(Collectors.toList()));
//        return orderDto;
//    }
//    public Order toOrder(OrderDto orderDto){
//        Order order = new Order();
//        order.setId(order.getId());
//        order.setCustomer(customerMapper.toCustomer(orderDto.getCustomer()));
//        order.setDesigner(designerMapper.toDesigner(orderDto.getDesigner()));
//        order.setOrderDate(orderDto.getOrderDate());
//        order.setCost(orderDto.getCost());
//        order.setOrderStatus(orderDto.getOrderStatus());
//        order
//        return order;
//    }
}
