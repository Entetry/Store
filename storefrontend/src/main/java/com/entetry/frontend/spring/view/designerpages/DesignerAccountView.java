package com.entetry.frontend.spring.view.designerpages;

import com.entetry.frontend.spring.security.SecuredByRole;
import com.entetry.frontend.spring.view.ApplicationLayout;
import com.entetry.frontend.spring.view.designerpages.bankaccountpages.BankAccountsView;
import com.entetry.frontend.spring.view.shoppingcard.ShoppingCardView;
import com.entetry.frontend.spring.view.userpages.MyAccountView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@SecuredByRole("DESIGNER_AUTHORITY")
@Route(value = DesignerAccountView.ROUTE)
public class DesignerAccountView extends AppLayout {
    public static final String ROUTE = "designers";
    public static final String TITLE = "Designer account";
    private RouterLink designerAccountLink  = new RouterLink(DesignerAccountView.TITLE,DesignerAccountView.class);
    private RouterLink itemLayoutLink = new RouterLink(ApplicationLayout.TITLE, ApplicationLayout.class);
    private RouterLink shoppingCardLayoutLink = new RouterLink(ShoppingCardView.TITLE, ShoppingCardView.class);
    private RouterLink customerAccountLink = new RouterLink(MyAccountView.TITLE, MyAccountView.class);
    public DesignerAccountView() {
        this.setPrimarySection(Section.NAVBAR);
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
        accountMenu.add(new RouterLink(DesignerDetailsView.TITLE, DesignerDetailsView.class));
        accountMenu.add(new RouterLink(BankAccountsView.TITLE, BankAccountsView.class));
        this.addToDrawer(accountMenu);
    }

}
