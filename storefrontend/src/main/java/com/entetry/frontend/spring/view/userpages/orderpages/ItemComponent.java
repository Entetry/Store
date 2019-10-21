package com.entetry.frontend.spring.view.userpages.orderpages;

import com.entetry.frontend.spring.view.customcomponents.CustomDivWithTitle;
import com.entetry.storecommon.dto.OrderItemDto;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToBigDecimalConverter;
import com.vaadin.flow.data.converter.StringToIntegerConverter;


public class ItemComponent extends HorizontalLayout {
    private CustomDivWithTitle itemNameDiv = new CustomDivWithTitle("Name:");
    private CustomDivWithTitle sizeDiv = new CustomDivWithTitle("Size:");
    private CustomDivWithTitle quantityDiv = new CustomDivWithTitle("Quantity:");
    private CustomDivWithTitle priceDiv = new CustomDivWithTitle("Price:");
    private Binder<OrderItemDto> binder = new Binder<>(OrderItemDto.class);
    public ItemComponent(OrderItemDto orderItemDto) {
        binder.setBean(orderItemDto);
        add(itemNameDiv, sizeDiv, quantityDiv, priceDiv);
        setWidthFull();
        getStyle().set("border-bottom", "2px solid black");
        getStyle().set("margin", "0");
        getStyle().set("justify-content", "space-around");
        bind();
    }
    private void bind(){
        binder.forField(itemNameDiv).bind(orderItemDto -> orderItemDto.getItem().getName(),null);
        binder.forField(sizeDiv).bind(orderItemDto -> orderItemDto.getSize().getSize(),null);
        binder.forField(quantityDiv).withConverter(new StringToIntegerConverter("ItemComponent")).bind(OrderItemDto::getQuantity,null);
        binder.forField(priceDiv).withConverter(new StringToBigDecimalConverter("ItemComponent")).bind(orderItemDto -> orderItemDto.getItem().getPrice(),null);
    }
}
