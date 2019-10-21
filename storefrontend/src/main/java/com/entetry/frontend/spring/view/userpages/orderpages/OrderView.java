package com.entetry.frontend.spring.view.userpages.orderpages;

import com.entetry.frontend.spring.view.customcomponents.CustomDivWithTitle;
import com.entetry.frontend.spring.view.userpages.MyAccountView;
import com.entetry.restclient.orderclient.RestOrderClient;
import com.entetry.storecommon.dto.OrderDto;
import com.entetry.storecommon.dto.OrderItemDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToBigDecimalConverter;
import com.vaadin.flow.data.converter.StringToDateConverter;
import com.vaadin.flow.data.converter.StringToLongConverter;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = OrderView.ROUTE, layout = MyAccountView.class)
public class OrderView extends VerticalLayout implements HasUrlParameter<String> {
    public static final String ROUTE = "orders";
    public static final String TITLE = "Order";
    private Button confirmOrderButton = new Button("Confirm order");
    VerticalLayout orderContainer = new VerticalLayout();
    HorizontalLayout orderDetailsLayout = new HorizontalLayout();
    HorizontalLayout itemDivLayout = new HorizontalLayout();
    VerticalLayout itemsLayout = new VerticalLayout();
    CustomDivWithTitle orderIdDiv = new CustomDivWithTitle("Order id:");
    CustomDivWithTitle orderDateDiv = new CustomDivWithTitle("Order date:");
    CustomDivWithTitle designerDiv = new CustomDivWithTitle("Designer:");
    CustomDivWithTitle totalCostDiv = new CustomDivWithTitle("Total cost:");
    CustomDivWithTitle statusDiv = new CustomDivWithTitle("Status:");
    private Div itemsDiv = new Div();
    private Binder<OrderDto> orderBinder = new Binder<>(OrderDto.class);
    private final RestOrderClient restOrderClient;
    @Autowired
    public OrderView(RestOrderClient restOrderClient){
        this.restOrderClient = restOrderClient;
        setupCssStyles();
        orderContainer.add(orderDetailsLayout,itemDivLayout,itemsLayout);
        orderDetailsLayout.add(orderIdDiv,orderDateDiv,designerDiv,totalCostDiv,statusDiv);
        itemsDiv.setText("Items:");
        add(confirmOrderButton,orderContainer);
        bind();
    }
    private void setupCssStyles(){
        orderContainer.setPadding(false);
        orderContainer.getStyle().set("border-top","2px solid black");
        orderContainer.getStyle().set("border-left","2px solid black");
        orderContainer.getStyle().set("border-right","2px solid black");
        itemsLayout.setPadding(false);
        itemsLayout.setMargin(false);
        itemDivLayout.getStyle().set("justify-content","center");
        itemDivLayout.add(itemsDiv);
        itemDivLayout.setMargin(false);
        itemDivLayout.setWidthFull();
        orderDetailsLayout.getStyle().set("border-bottom","2px solid black");
        orderDetailsLayout.setWidthFull();
        orderDetailsLayout.getStyle().set("justify-content","space-evenly");
        itemDivLayout.getStyle().set("border-bottom","2px solid black");
    }
    public void bind(){
        orderBinder.forField(orderIdDiv).withConverter(new StringToLongConverter("df")).bind(OrderDto::getId,null);
        orderBinder.forField(orderDateDiv).withConverter(new StringToDateConverter()).bind(OrderDto::getOrderDate,null);
        orderBinder.forField(designerDiv).bind(orderDto -> orderDto.getDesigner().getDesignerName(),null);
        orderBinder.forField(totalCostDiv).withConverter(new StringToBigDecimalConverter("OrderViewComponent"))
                .bind(OrderDto::getCost,null);
        orderBinder.forField(statusDiv).bind(OrderDto::getOrderStatus,null);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String orderId) {
    OrderDto orderDto = restOrderClient.getOrderById(orderId);
    orderBinder.setBean(orderDto);
    for (OrderItemDto orderItemDto : orderDto.getItems()){
        itemsLayout.add(new ItemComponent(orderItemDto));
    }
    }
}
