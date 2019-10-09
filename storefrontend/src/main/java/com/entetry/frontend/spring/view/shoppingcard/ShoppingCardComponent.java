package com.entetry.frontend.spring.view.shoppingcard;

import com.entetry.frontend.spring.service.ShoppingCardService;
import com.entetry.storecommon.dto.DesignerDto;
import com.entetry.storecommon.dto.ItemDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ShoppingCardComponent extends VerticalLayout {
    private Label designerLabel = new Label();
    private List<ShoppingCardItemComponent> items = new ArrayList<>();
    private ShoppingCardService shoppingCardService;
    private ShoppingCardView shoppingCardView;
    private Button orderButton = new Button("Order from this designer");

    public ShoppingCardComponent(DesignerDto designerDto,
                                 ShoppingCardService shoppingCardService, ShoppingCardView shoppingCardView) {
        this.shoppingCardService = shoppingCardService;
        this.shoppingCardView = shoppingCardView;
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(designerLabel, orderButton);
        add(horizontalLayout);
        designerLabel.setText(designerDto.getDesignerName());
        orderButton.addClickListener(event -> {
            List<ItemDto> designerItems = shoppingCardService.getShoppingCard().getItems().stream()
                    .filter(itemDto -> itemDto.getDesigner().getDesignerName().equals(designerLabel.getText()))
                    .collect(Collectors.toList());
            shoppingCardService.orderItems(designerItems);
            shoppingCardView.setItems();
        });
    }

    public void addItem(ItemDto itemDto) {
        items.add(new ShoppingCardItemComponent(shoppingCardService, shoppingCardView));
        items.get(items.size() - 1).setItem(itemDto);
        this.add(items.get(items.size() - 1));
    }

    public String getDesignerName() {
        return designerLabel.getText();
    }

}
