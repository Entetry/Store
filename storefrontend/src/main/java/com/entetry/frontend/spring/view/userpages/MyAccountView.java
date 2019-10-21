package com.entetry.frontend.spring.view.userpages;

import com.entetry.frontend.spring.security.SecuredByRole;
import com.entetry.frontend.spring.view.ApplicationLayout;
import com.entetry.frontend.spring.view.designerpages.DesignerAccountView;
import com.entetry.frontend.spring.view.userpages.addresspages.AddressBookView;
import com.entetry.frontend.spring.view.userpages.creditcardpages.PaymentMethodsView;
import com.entetry.frontend.spring.view.userpages.orderpages.MyOrdersView;
import com.entetry.frontend.spring.view.shoppingcard.ShoppingCardView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
@SecuredByRole("CUSTOMER_AUTHORITY")
@Route(value = MyAccountView.ROUTE)
public class MyAccountView extends AppLayout {
    public static final String ROUTE = "users";
    public static final String TITLE = "Users";
    private RouterLink designerAccountLink  = new RouterLink(DesignerAccountView.TITLE,DesignerAccountView.class);
    private RouterLink itemLayoutLink = new RouterLink(ApplicationLayout.TITLE, ApplicationLayout.class);
    private RouterLink shoppingCardLayoutLink = new RouterLink(ShoppingCardView.TITLE, ShoppingCardView.class);
    private RouterLink customerAccountLink = new RouterLink(MyAccountView.TITLE, MyAccountView.class);
    public MyAccountView() {
        Div logo = new Div(new H2("Store"));
        logo.getStyle().set("margin-left","25px");
        logo.addClickListener(e -> UI.getCurrent().navigate(ApplicationLayout.class));
        final HorizontalLayout menuBar = new HorizontalLayout();
        menuBar.setHeight("100px");
        menuBar.add(logo,itemLayoutLink,customerAccountLink,designerAccountLink,shoppingCardLayoutLink);
        menuBar.getStyle().set("align-items","baseline");
        menuBar.setWidthFull();
        addToNavbar(menuBar);
        VerticalLayout accountMenu = new VerticalLayout();
        accountMenu.add(new RouterLink(MyDetailsView.TITLE, MyDetailsView.class));
        accountMenu.add(new RouterLink(AddressBookView.TITLE, AddressBookView.class));
        accountMenu.add(new RouterLink(PaymentMethodsView.TITLE, PaymentMethodsView.class));
        accountMenu.add(new RouterLink(MyOrdersView.TITLE, MyOrdersView.class));
        this.addToDrawer(accountMenu);
    }
}
