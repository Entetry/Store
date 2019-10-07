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


public class ShoppingCardComponent extends VerticalLayout {
    private Label designerLabel = new Label();
    private List<ShoppingCardItemComponent> items = new ArrayList<>();
    private ShoppingCardService shoppingCardService;
    private Button orderButton = new Button("Order from this designer");
    public ShoppingCardComponent(DesignerDto designerDto,
                                 ShoppingCardService shoppingCardService){
        this.shoppingCardService=shoppingCardService;
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(designerLabel,orderButton);
        add(horizontalLayout);
        designerLabel.setText(designerDto.getDesignerName());
    }
    public void addItem(ItemDto itemDto){
        items.add(new ShoppingCardItemComponent(shoppingCardService));
        items.get(items.size()-1).setItem(itemDto);
        this.add(items.get(items.size()-1));
    }
    public String getDesignerName(){
        return designerLabel.getText();
    }

}
