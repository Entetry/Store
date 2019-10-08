package com.entetry.store.mapper;

import com.entetry.store.entity.Order;
import com.entetry.store.entity.OrderItem;
import com.entetry.store.entity.Size;
import com.entetry.storecommon.dto.OrderDto;
import com.entetry.storecommon.dto.OrderItemDto;
import com.entetry.storecommon.dto.SizeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {
    private final DesignerMapper designerMapper;

    private final CustomerMapper customerMapper;
    private final ItemMapper itemMapper;

    @Autowired
    public OrderItemMapper(DesignerMapper designerMapper, CustomerMapper customerMapper, ItemMapper itemMapper) {
        this.designerMapper = designerMapper;
        this.itemMapper = itemMapper;
        this.customerMapper = customerMapper;
    }

    public OrderItemDto toOrderItemDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setItem(itemMapper.toItemDto(orderItem.getItem()));
        SizeDto sizeDto = new SizeDto();
        sizeDto.setId(orderItem.getSize().getId());
        sizeDto.setSize(orderItem.getSize().getSize());
        orderItemDto.setSize(sizeDto);
        OrderDto order = new OrderDto();
        order.setId(orderItem.getOrder().getId());
        order.setCost(orderItem.getOrder().getCost());
        order.setCustomer(customerMapper.toCustomerDto(orderItem.getOrder().getCustomer()));
        order.setOrderDate(orderItem.getOrder().getOrderDate());
        order.setOrderStatus(orderItem.getOrder().getOrderStatus());
        order.setCost(orderItem.getOrder().getCost());
        order.setDesigner(designerMapper.toDesignerDto(orderItem.getOrder().getDesigner()));
        orderItemDto.setOrder(order);
        orderItemDto.setQuantity(orderItem.getQuantity());
        return orderItemDto;
    }

    public OrderItem toOrderItem(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(itemMapper.toItem(orderItemDto.getItem()));
        Size size = new Size();
        size.setId(orderItemDto.getSize().getId());
        size.setSize(orderItemDto.getSize().getSize());
        orderItem.setSize(size);
        Order order = new Order();
        order.setId(orderItemDto.getOrder().getId());
        order.setCost(orderItemDto.getOrder().getCost());
        order.setCustomer(customerMapper.toCustomer(orderItemDto.getOrder().getCustomer()));
        order.setOrderDate(orderItemDto.getOrder().getOrderDate());
        order.setOrderStatus(orderItemDto.getOrder().getOrderStatus());
        order.setCost(orderItemDto.getOrder().getCost());
        order.setDesigner(designerMapper.toDesigner(orderItemDto.getOrder().getDesigner()));
        orderItem.setOrder(order);
        orderItem.setQuantity(orderItemDto.getQuantity());
        return orderItem;
    }
}
