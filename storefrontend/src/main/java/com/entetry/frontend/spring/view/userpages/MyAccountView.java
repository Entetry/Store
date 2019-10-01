package com.entetry.frontend.spring.view.userpages;

import com.entetry.frontend.spring.MainView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route(value = MyAccountView.ROUTE,layout = MainView.class)
public class MyAccountView extends AppLayout {
    public static final String ROUTE = "users";
    public static final String TITLE = "Users";
public MyAccountView(){
    this.setPrimarySection(AppLayout.Section.DRAWER);
    VerticalLayout accountMenu = new VerticalLayout();
//    accountMenu.add(new RouterLink(AddressBookView.TITLE,AddressBookView.class));
    accountMenu.add(new RouterLink(MyDetailsView.TITLE,MyDetailsView.class,"2"));
    accountMenu.add(new RouterLink(AddressBookView.TITLE,AddressBookView.class,"2"));
    accountMenu.add(new RouterLink(PaymentMethodsView.TITLE,PaymentMethodsView.class,"2"));
    this.addToDrawer(accountMenu);
}
}
