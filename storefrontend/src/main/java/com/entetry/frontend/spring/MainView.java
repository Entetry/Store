package com.entetry.frontend.spring;

import com.entetry.frontend.spring.view.ApplicationLayout;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;


public class MainView extends AppLayout {

    public MainView() {

        Div logo = new Div(new H2("Store"));
        final VerticalLayout menuBar = new VerticalLayout();
        menuBar.add(new RouterLink(ApplicationLayout.TITLE, ApplicationLayout.class));;
        addToNavbar(menuBar);
    }

}
