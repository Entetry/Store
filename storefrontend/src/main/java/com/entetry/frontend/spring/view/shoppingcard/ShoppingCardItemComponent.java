package com.entetry.frontend.spring.view.shoppingcard;

import com.entetry.frontend.spring.service.ShoppingCardService;
import com.entetry.storecommon.dto.ItemDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ReadOnlyHasValue;
import com.vaadin.flow.data.converter.StringToBigDecimalConverter;
import com.vaadin.flow.data.converter.StringToIntegerConverter;

public class ShoppingCardItemComponent extends HorizontalLayout {
    private Binder<ItemDto> binder = new Binder<>(ItemDto.class);
    private Label nameLabel = new Label();
    private Image image = new Image();
    private Label priceLabel = new Label();
    private Label sizeLabel = new Label();
    private TextField quantityField = new TextField();
    private Button deleteItemButton = new Button("Delete");
    private Checkbox orderItemCheckBox = new Checkbox();
    private ItemDto itemDto;
    private ShoppingCardService shoppingCardService;

    public ShoppingCardItemComponent(ShoppingCardService shoppingCardService, ShoppingCardView shoppingCardView) {
        this.shoppingCardService = shoppingCardService;
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(nameLabel, sizeLabel, priceLabel);
        add(image, verticalLayout, quantityField, deleteItemButton);
        deleteItemButton.addClickListener(event -> {
            shoppingCardService.deleteItemFromShoppingCard(itemDto);
            shoppingCardView.setItems();
        });
        bind();
    }

    private void bind() {
        ReadOnlyHasValue<String> name = new ReadOnlyHasValue<>(text -> nameLabel.setText(text));
        ReadOnlyHasValue<String> price = new ReadOnlyHasValue<>(text -> priceLabel.setText(text));
        ReadOnlyHasValue<String> size = new ReadOnlyHasValue<>(text -> sizeLabel.setText(text));
        binder.bind(name, ItemDto::getName, null);
        binder.forField(price).withConverter(new StringToBigDecimalConverter("Shopping card item "))
                .bind(ItemDto::getPrice, null);
        binder.forField(size).bind(itemDto -> itemDto.getItemSizes().stream().findFirst().get().getSize().getSize(), null);
        binder.forField(quantityField).withConverter(new StringToIntegerConverter("error"))
                .bind(itemDto -> itemDto.getItemSizes().stream().findFirst().get().getQuantity(),
                        (itemDto, integer) -> itemDto.getItemSizes().stream().findFirst().get().setQuantity(integer));

    }

    public void setItem(ItemDto itemDto) {
        this.itemDto = itemDto;
        binder.setBean(itemDto);
        image.setSrc(binder.getBean().getImages().get(0).getUrl());
    }
}
