package com.entetry.frontend.spring;

import com.entetry.frontend.spring.view.ApplicationLayout;
import com.entetry.frontend.spring.view.shoppingcard.ShoppingCardView;
import com.entetry.frontend.spring.view.userpages.MyAccountView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLink;


public class MainView extends AppLayout {

    public MainView() {
        Div logo = new Div(new H2("Store"));
        final HorizontalLayout menuBar = new HorizontalLayout();
        menuBar.add(new RouterLink(ApplicationLayout.TITLE, ApplicationLayout.class));
//        menuBar.add( new Anchor(UI.getCurrent().getRouter().getUrl(MyAccountView.class),"My account"));
        menuBar.add(new RouterLink(MyAccountView.TITLE, MyAccountView.class));
        menuBar.add(new RouterLink(ShoppingCardView.TITLE, ShoppingCardView.class));
        addToNavbar(logo, menuBar);
    }

}
