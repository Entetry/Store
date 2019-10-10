package com.entetry.frontend.spring;

import com.entetry.frontend.spring.view.ApplicationLayout;
import com.entetry.frontend.spring.view.designerpages.DesignerAccountView;
import com.entetry.frontend.spring.view.shoppingcard.ShoppingCardView;
import com.entetry.frontend.spring.view.userpages.MyAccountView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLink;


public class MainView extends AppLayout {

    private RouterLink designerAccountLink  = new RouterLink(DesignerAccountView.TITLE,DesignerAccountView.class);
    private RouterLink itemLayoutLink = new RouterLink(ApplicationLayout.TITLE, ApplicationLayout.class);
    private RouterLink shoppingCardLayoutLink = new RouterLink(ShoppingCardView.TITLE, ShoppingCardView.class);
    private RouterLink customerAccountLink = new RouterLink(MyAccountView.TITLE, MyAccountView.class);
    public MainView() {
        Div logo = new Div(new H2("Store"));
        logo.getStyle().set("margin-left","25px");
        final HorizontalLayout menuBar = new HorizontalLayout();
        menuBar.setHeight("100px");
        menuBar.add(logo,itemLayoutLink,customerAccountLink,designerAccountLink,shoppingCardLayoutLink);
        menuBar.getStyle().set("align-items","baseline");
        menuBar.setWidthFull();
        addToNavbar(menuBar);
    }


}
