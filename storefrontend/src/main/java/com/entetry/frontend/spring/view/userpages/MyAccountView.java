package com.entetry.frontend.spring.view.userpages;

import com.entetry.frontend.spring.view.ApplicationLayout;
import com.entetry.storecommon.dto.UserDetailsDto;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.springframework.security.core.context.SecurityContextHolder;

@Route(value = MyAccountView.ROUTE)
public class MyAccountView extends AppLayout {
    public static final String ROUTE = "users";
    public static final String TITLE = "Users";
public MyAccountView(){
    this.setPrimarySection(AppLayout.Section.DRAWER);
    String userId = ((UserDetailsDto)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId().toString();
    Div logo = new Div(new H2("Store"));
    logo.setHeight("44px");
    logo.addClickListener(e-> UI.getCurrent().navigate(ApplicationLayout.class));
    addToNavbar(logo);
    VerticalLayout accountMenu = new VerticalLayout();
    accountMenu.add(new RouterLink(MyDetailsView.TITLE,MyDetailsView.class));
    accountMenu.add(new RouterLink(AddressBookView.TITLE,AddressBookView.class));
    accountMenu.add(new RouterLink(PaymentMethodsView.TITLE,PaymentMethodsView.class));
    this.addToDrawer(accountMenu);
}
}
