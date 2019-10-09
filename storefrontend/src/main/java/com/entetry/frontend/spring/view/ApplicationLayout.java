package com.entetry.frontend.spring.view;

import com.entetry.frontend.spring.MainView;
import com.entetry.frontend.spring.view.itempages.ItemComponent;
import com.entetry.restclient.itemclient.RestItemClient;
import com.entetry.storecommon.dto.ImageDto;
import com.entetry.storecommon.dto.ItemDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = ApplicationLayout.ROUTE, layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
public class ApplicationLayout extends VerticalLayout implements HasUrlParameter<String>, BeforeEnterObserver {
    public static final String ROUTE = "items";
    public static final String TITLE = "Items";
    private final HorizontalLayout layout;
    private final VerticalLayout verticalLayout;
    private Div navigation;
    private Div content;
    @Autowired
    private RestItemClient restItemClient;

    public ApplicationLayout() {
        verticalLayout = new VerticalLayout();
        setPadding(false);
        setSpacing(false);
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
        final Div header = new Div();
        header.getStyle().set("flexShrink", "0");
        header.setText("HEADER. My height is 150 pixels");
        header.setClassName("header");
        header.setHeight("50px");
        header.setWidthFull();
        header.getStyle().set("background", "#42b3f5");
        setSizeFull();
        layout = new HorizontalLayout();
        layout.setHeightFull();
        layout.setSpacing(false);
        createTextLayout();
        FlexLayout footerLayout = new FlexLayout();
        final Div footer = new Div();
        footer.getStyle().set("flexShrink", "0");
        footer.setText("This is the footer area. My height is 100 pixels");
        footer.setClassName("footer");
        footer.getStyle().set("background", "#42b3f5");
        footer.setHeight("100px");
        footer.setWidthFull();
        footerLayout.add(footer);
        footerLayout.setAlignItems(Alignment.END);
        footerLayout.setWidthFull();
        verticalLayout.add(header, layout, footerLayout);
        verticalLayout.expand(layout);
        add(verticalLayout);
    }

    private void createTextLayout() {
        navigation = new Div();
        navigation.setClassName("navigation");
        navigation.setWidth("25%");
        navigation.getElement().getStyle().set("flex-shrink", "0");

        Button button = new Button();
        button.addClickListener(e -> {
            setItems();
        });
        button.setText("ADD BLOCK");
        navigation.add(button);

        content = new Div();
        content.setHeightFull();
        content.setWidth("auto");
        content.getStyle().set("display", "flex");
        content.setClassName("content");
        content.getStyle().set("flex-wrap", "wrap");
        content.getStyle().set("align-content", "flex-start");

        layout.add(navigation, content);
        layout.expand(content);
        layout.setDefaultVerticalComponentAlignment(Alignment.STRETCH);
    }

    /**
     Ignore this method for now.

     @return
     */

    private void setItems() {
        List<ItemDto> itemDtos = restItemClient.getAllItems();
        for (ItemDto itemDto : itemDtos) {
            ImageDto imageDto = itemDto.getImages().stream().findFirst().get();
            ItemComponent itemComponent = new ItemComponent(itemDto.getName(), imageDto.getUrl(), imageDto.getName());
            content.add(itemComponent);
        }

    }

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
//        if ("scroll".equals(parameter)) {
//            updateUIForScroll();
//        }
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        setItems();
    }
//
//    private void updateUIForScroll() {
//        final Button add = new Button("Add", e -> {
//            content.add(createBlock());
//        });
//        navigation.setText(null);
//        content.setText(null);
//        navigation.add(add);
//
//        makeContentScrollable();
//
//    }
//
//    private void makeContentScrollable() {
//        content.getStyle().set("flexWrap", "wrap");
//        content.getStyle().set("overflowY", "auto");
//    }
}
