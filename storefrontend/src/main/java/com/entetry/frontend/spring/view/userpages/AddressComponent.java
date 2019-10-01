package com.entetry.frontend.spring.view.userpages;

import com.entetry.storecommon.dto.AdressDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ReadOnlyHasValue;

public class AddressComponent extends HorizontalLayout {
    Binder<AdressDto> adressDtoBinder = new Binder<>(AdressDto.class);
    private Label firstnameLabel = new Label("FIRST NAME");
    private Label lastnameLabel = new Label("LAST NAME");
    private Label emailLabel =  new Label("EMAIL");
    private Label addressLabel = new Label("ADDRESS");
    private Label postIndexLabel = new Label("POST INDEX");
    private Label cityLabel = new Label("CITY");
    private Label regionLabel = new Label("REGION");
    private Label phoneLabel = new Label("PHONE");
    private Button edit = new Button("EDIT");
    private Button delete = new Button("DELETE");
    public AddressComponent(){
        VerticalLayout labels = new VerticalLayout();
        VerticalLayout buttons = new VerticalLayout();
        labels.add(firstnameLabel,lastnameLabel,emailLabel,addressLabel,postIndexLabel,cityLabel,regionLabel,phoneLabel);
        buttons.add(edit,delete);
        add(labels,buttons);
    }
    public void bind(){
        ReadOnlyHasValue<String> firstname = new ReadOnlyHasValue<>(text->firstnameLabel.setText(text));
        ReadOnlyHasValue<String> lastname = new ReadOnlyHasValue<>(text->lastnameLabel.setText(text));
        ReadOnlyHasValue<String> email = new ReadOnlyHasValue<>(text->emailLabel.setText(text));
        ReadOnlyHasValue<String> address = new ReadOnlyHasValue<>(text->addressLabel.setText(text));
        ReadOnlyHasValue<String> postIndex = new ReadOnlyHasValue<>(text->postIndexLabel.setText(text));
        ReadOnlyHasValue<String> city = new ReadOnlyHasValue<>(text->cityLabel.setText(text));
        ReadOnlyHasValue<String>  region = new ReadOnlyHasValue<>(text-> regionLabel.setText(text));
        ReadOnlyHasValue<String> phone = new ReadOnlyHasValue<>(text->phoneLabel.setText(text));
        adressDtoBinder.forField(firstname).bind(AdressDto::getFirstname,null);
        adressDtoBinder.forField(lastname).bind(AdressDto::getFirstname,null);
        adressDtoBinder.forField(email).bind(AdressDto::getEmail,null);
        adressDtoBinder.forField(address).bind(AdressDto::getAdress,null);
        adressDtoBinder.forField(postIndex).bind(AdressDto::getPostIndex,null);
        adressDtoBinder.forField(city).bind(AdressDto::getCity,null);
        adressDtoBinder.forField(region).bind(AdressDto::getRegion,null);
    }
    public void setAddress(AdressDto address){
        adressDtoBinder.setBean(address);
    }
}
