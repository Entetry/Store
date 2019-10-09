package com.entetry.frontend.spring.view.shoppingcard;

import com.entetry.frontend.spring.MainView;
import com.entetry.frontend.spring.service.ShoppingCardService;
import com.entetry.storecommon.dto.DesignerDto;
import com.entetry.storecommon.dto.ItemDto;
import com.entetry.storecommon.dto.ShoppingCard;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Route(value = ShoppingCardView.ROUTE, layout = MainView.class)
public class ShoppingCardView extends VerticalLayout implements AfterNavigationObserver {
    public static final String ROUTE = "shopping-card";
    public static final String TITLE = "ShoppingCard";
    private Label shoppingCardLabel = new Label("Shopping card");
    private List<ShoppingCardComponent> shoppingCardComponents = new ArrayList<>();
    private final ShoppingCardService shoppingCardService;
    private Button orderButton = new Button("Order");
    private HorizontalLayout horizontalLayout = new HorizontalLayout();

    public ShoppingCardView(ShoppingCardService shoppingCardService) {
        this.shoppingCardService = shoppingCardService;
        orderButton.addClickListener(event ->
                {
                    shoppingCardService.orderAllItems();
                    setItems();
                }
        );

    }

    public void setItems() {
        shoppingCardComponents.clear();
        removeAll();
        horizontalLayout.add(shoppingCardLabel, orderButton);
        add(horizontalLayout);
        ServletRequestAttributes attrt = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attrt.getRequest().getSession();
        if (session.getAttribute("Shopping Card") == null || ((ShoppingCard) session.getAttribute("Shopping Card")).getItems().size() == 0) {
            add(new Label("Your card is empty"));
        } else {
            ShoppingCard shoppingCard = (ShoppingCard) session.getAttribute("Shopping Card");
            for (DesignerDto designer : shoppingCard.getItems().stream().map(itemDto -> itemDto.getDesigner()).distinct().collect(Collectors
                    .toList())) {
                shoppingCardComponents.add(new ShoppingCardComponent(designer, shoppingCardService, this));
                add(shoppingCardComponents.get(shoppingCardComponents.size() - 1));
                System.out.println(shoppingCardComponents.get(shoppingCardComponents.size() - 1).getDesignerName());
            }
            for (ItemDto item : shoppingCard.getItems()) {
                ShoppingCardComponent cardComponent = shoppingCardComponents.stream()
                        .filter(shoppingCardComponent -> shoppingCardComponent.getDesignerName().equals(item.getDesigner().getDesignerName())).findFirst().get();
                cardComponent.addItem(item);
            }
        }
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        System.out.println("AFTER NAVIGATION");
        setItems();
    }

    public void removeComponent(Component component) {
        remove(component);
    }
}
