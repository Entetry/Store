package com.entetry.frontend.spring.view.userpages.orderpages;

import com.entetry.frontend.spring.view.customcomponents.CustomDivWithTitle;
import com.entetry.storecommon.dto.OrderDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToBigDecimalConverter;
import com.vaadin.flow.data.converter.StringToDateConverter;
import com.vaadin.flow.data.converter.StringToLongConverter;

public class OrderViewComponent extends HorizontalLayout {
    private CustomDivWithTitle orderIdDiv = new CustomDivWithTitle("Order Id :");
    private CustomDivWithTitle dateDiv = new CustomDivWithTitle("OrderDate");
    private CustomDivWithTitle designerDiv = new CustomDivWithTitle("Designer:");
    private CustomDivWithTitle totalCostDiv = new CustomDivWithTitle("Total cost:");
    private CustomDivWithTitle statusDiv = new CustomDivWithTitle("Status:");
    private Button detailsButton = new Button("Details");
    private HorizontalLayout horizontalLayout = new HorizontalLayout();
    private Binder<OrderDto> binder = new Binder<>(OrderDto.class);

    public OrderViewComponent(OrderDto orderDto) {
        binder.setBean(orderDto);
        setupCssStyles();
        horizontalLayout.add(orderIdDiv, dateDiv, designerDiv, totalCostDiv, statusDiv);
        detailsButton.addClickListener(buttonClickEvent -> detailsButton.getUI().ifPresent(ui -> ui.navigate(OrderView.class, orderDto.getId().toString())));
        add(horizontalLayout, detailsButton);
        bind();
    }
    private void setupCssStyles(){
        getStyle().set("border-style", "solid");
//        getStyle().set("padding","3px");
//        getStyle().set("display","flex");
        setWidth("100%");
        horizontalLayout.setWidth("75%");
        horizontalLayout.setHeightFull();
        horizontalLayout.getStyle().set("justify-content", "space-around");
        detailsButton.setHeight("75px");
        detailsButton.setWidth("25%");
        detailsButton.getStyle().set("background-color", "white");
    }
    private void bind() {
        binder.forField(orderIdDiv).withConverter(new StringToLongConverter("df")).bind(OrderDto::getId, null);
        binder.forField(dateDiv).withConverter(new StringToDateConverter()).bind(OrderDto::getOrderDate, null);
        binder.forField(designerDiv).bind(orderDto -> orderDto.getDesigner().getDesignerName(), null);
        binder.forField(totalCostDiv).withConverter(new StringToBigDecimalConverter("OrderViewComponent"))
                .bind(OrderDto::getCost, null);
        binder.forField(statusDiv).bind(OrderDto::getOrderStatus, null);
    }

    public void setOrder(OrderDto orderDto) {
        binder.setBean(orderDto);
    }
}
