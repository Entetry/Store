package com.entetry.frontend.spring.view;

import com.entetry.frontend.spring.MainView;
import com.entetry.restclient.itemclient.RestItemClient;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.springframework.beans.factory.annotation.Autowired;
@Route(value = UserLayout.ROUTE, layout = MainView.class)
@RouteAlias(value = "users", layout = MainView.class)
public class UserLayout extends VerticalLayout implements HasUrlParameter<String>,RouterLayout{
    public static final String ROUTE = "users";
    public static final String TITLE = "Users";
    private final HorizontalLayout layout;
    private final VerticalLayout verticalLayout;
    private Div navigation;
    private Div content;
    @Autowired
    private RestItemClient restItemClient;
    public UserLayout() {
        verticalLayout = new VerticalLayout();
        setPadding(false);
        setSpacing(false);
        setSizeFull();
        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.STRETCH);
        final Div header = new Div();
        header.getStyle().set("flexShrink", "0");
        header.setText("HEADER. My height is 150 pixels");
        header.setClassName("header");
        header.setHeight("50px");
        header.setWidthFull();
        header.getStyle().set("background","#42b3f5");
        setSizeFull();
        layout = new HorizontalLayout();
        layout.setHeightFull();
        layout.setSpacing(false);
        createTextLayout();
        HorizontalLayout footerLayout = new HorizontalLayout();
        final Div footer = new Div();
        footer.getStyle().set("flexShrink", "0");
        footer.setText("This is the footer area. My height is 100 pixels");
        footer.setClassName("footer");
        footer.getStyle().set("background","#42b3f5");
        footer.setHeight("100px");
        footer.setWidthFull();
        footerLayout.add(footer);
        footerLayout.setAlignItems(FlexComponent.Alignment.END);
        footerLayout.setWidthFull();
        footerLayout.setDefaultVerticalComponentAlignment(Alignment.END);
        verticalLayout.add(header,layout,footerLayout);
        verticalLayout.expand(layout);
        add(verticalLayout);
    }

    private void createNavigationLayout(){
        navigation = new Div();
        navigation.setClassName("navigation");
        navigation.setWidth("25%");
        navigation.getElement().getStyle().set("flex-shrink", "0");
    }
    private void createTextLayout() {
        createNavigationLayout();
        content = new Div();
        content.setHeightFull();
        content.setWidth("auto");
        content.getStyle().set("display", "flex");
        content.setClassName("content");
        content.getStyle().set("flex-wrap", "wrap");
        content.getStyle().set("align-content", "flex-start");

        layout.add(navigation, content);
        layout.expand(content);
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.STRETCH);
    }

    /**
     * Ignore this method for now.
     *
     * @return
     */



    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
//        if ("scroll".equals(parameter)) {
//            updateUIForScroll();
//        }
    }
}
