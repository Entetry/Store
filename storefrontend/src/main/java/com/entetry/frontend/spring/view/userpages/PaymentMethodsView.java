package com.entetry.frontend.spring.view.userpages;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

@Route(value = PaymentMethodsView.ROUTE, layout = MyAccountView.class)
public class PaymentMethodsView  extends VerticalLayout implements HasUrlParameter<String> {
    public static final String ROUTE = "payment-methods";
    public static final String TITLE = "Payment methods";
    private Label paymentMethodsViewLabel = new Label("Credit cards");
    public PaymentMethodsView(){
        add(paymentMethodsViewLabel);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {

    }
}
