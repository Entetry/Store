package com.entetry.frontend.spring.view.userpages.orderpages;

import com.entetry.frontend.spring.security.SecuredByRole;
import com.entetry.frontend.spring.view.userpages.MyAccountView;
import com.entetry.restclient.orderclient.RestOrderClient;
import com.entetry.storecommon.dto.OrderDto;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SecuredByRole("CUSTOMER_AUTHORITY")
@Route(value = MyOrdersView.ROUTE, layout = MyAccountView.class)
public class MyOrdersView extends VerticalLayout {
    public static final String ROUTE = "my-orders";
    public static final String TITLE = "My orders";
    public Label orderViewLabel = new Label("Your orders:");
    public VerticalLayout orderLayout = new VerticalLayout();
    private final RestOrderClient restOrderClient;
    @Autowired
    public MyOrdersView(RestOrderClient restOrderClient){
        this.restOrderClient=restOrderClient;
        orderLayout.add(orderViewLabel);
        orderLayout.getStyle().set("border-style","solid");
        orderLayout.getStyle().set("padding","3px");
        add(orderLayout);
        setItems();
    }
    public void setItems(){
        List<OrderDto> orderDtos = restOrderClient.getAllOrders();
        for (OrderDto orderDto : orderDtos){
            orderLayout.add(new OrderViewComponent(orderDto));
        }
    }
}
