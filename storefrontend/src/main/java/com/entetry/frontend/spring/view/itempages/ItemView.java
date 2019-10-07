package com.entetry.frontend.spring.view.itempages;

import com.entetry.frontend.spring.MainView;
import com.entetry.frontend.spring.service.ShoppingCardService;
import com.entetry.restclient.itemclient.RestItemClient;
import com.entetry.storecommon.dto.ItemDto;
import com.entetry.storecommon.dto.SizeDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ReadOnlyHasValue;
import com.vaadin.flow.data.converter.StringToBigDecimalConverter;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Route(value = ItemView.ROUTE, layout = MainView.class)
public class ItemView extends VerticalLayout implements HasUrlParameter<String> {
    public static final String ROUTE = "items";
    public static final String TITLE = "Item";
    private Label nameLabel =new Label();
    private Image image = new Image();
    private ComboBox<SizeDto> sizes = new ComboBox<>();
    private Label subcategoryLabel = new Label();
    private Label priceLabel = new Label();
    private Label sexLabel = new Label();
    private Label designerLabel = new Label();
    private Button orderButton = new Button("Buy");
   private Binder<ItemDto> binder = new Binder<>();
    private final RestItemClient restItemClient;
    private final ShoppingCardService shoppingCardService;
    @Autowired
    public ItemView(RestItemClient restItemClient,ShoppingCardService shoppingCardService){
        this.restItemClient= restItemClient;
        this.shoppingCardService= shoppingCardService;
        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSizeFull();
        verticalLayout.add(designerLabel,nameLabel,sizes,sexLabel,subcategoryLabel,priceLabel);
        image.setWidth("100%");
        setVisible(true);
        getStyle().set("margin", "4px");
        setWidth("30%");
    horizontalLayout.add(image,verticalLayout);
        add(horizontalLayout,orderButton);
        System.out.println("BEFORE BIND");
        orderButton.addClickListener(
                event-> {shoppingCardService.addToShoppingCard(binder.getBean());
        });
        bind();
    }
    public void bind(){
        System.out.println("BIND");
        ReadOnlyHasValue<String> name = new ReadOnlyHasValue<>(text->nameLabel.setText(text));
        ReadOnlyHasValue<String> subcategory = new ReadOnlyHasValue<>(text->subcategoryLabel.setText(text));
        ReadOnlyHasValue<String> price = new ReadOnlyHasValue<>(text->priceLabel.setText(text));
        ReadOnlyHasValue<String> sex = new ReadOnlyHasValue<>(text->sexLabel.setText(text));
        ReadOnlyHasValue<String> designer = new ReadOnlyHasValue<>(text->designerLabel.setText(text));
        binder.forField(name).bind(ItemDto::getName,null);
        binder.forField(price).withConverter(new StringToBigDecimalConverter("ItemView price")).bind(ItemDto::getPrice,null);
        binder.forField(subcategory).bind(item->item.getSubcategory().getName(),null);
        binder.forField(sex).bind(ItemDto::getSex,null);
        binder.forField(designer).bind(itemDto -> itemDto.getDesigner().getDesignerName(),null);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String itemName) {
        System.out.println("SET PARAMETR");
        binder.setBean(restItemClient.getItemByName(itemName));
        System.out.println(binder.getBean().getImages().get(0).getUrl());
        image.setSrc(binder.getBean().getImages().get(0).getUrl());
        sizes.setItemLabelGenerator(SizeDto::getSize);
        sizes.setItems(binder.getBean().getItemSizes().stream().map(itemSizeDto -> itemSizeDto.getSize()).collect(Collectors.toList()));

    }

}
