package com.entetry.frontend.spring.view;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ItemView extends VerticalLayout {
    private Label nameLabel;
    private Image image;
    public ItemView(String itemName,String url,String imageName){
    nameLabel = new Label();
    nameLabel.setText(itemName);
    image = new Image(url,imageName);
    image.setWidth("100%");
    setVisible(true);
    getStyle().set("margin", "4px");
    setWidth("30%");
    add(image,nameLabel);

    }

    public String getLabelValue() {
        return nameLabel.getText();
    }

    public void setLabelValue(String value) {
        nameLabel.setText(value);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
