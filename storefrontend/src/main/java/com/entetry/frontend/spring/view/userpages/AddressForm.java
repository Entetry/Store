package com.entetry.frontend.spring.view.userpages;

import com.entetry.storecommon.dto.AdressDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

@Route(value = AddressForm.ROUTE, layout = MyAccountView.class)
public class AddressForm extends VerticalLayout implements HasUrlParameter<String> {
    public static final String ROUTE = "addresses/addAddress";
    public static final String TITLE = "newAddress";
    private Binder<AdressDto> binder = new Binder<>(AdressDto.class);
    private TextField firstname = new TextField("FIRST NAME");
    private TextField lastname = new TextField("LAST NAME");
    private TextField email =  new TextField("EMAIL");
    private TextField address = new TextField("ADDRESS");
    private TextField postIndex = new TextField("POST INDEX");
    private TextField city = new TextField("CITY");
    private TextField region = new TextField("REGION");
    private TextField phone = new TextField("PHONE");
    private Button saveAddress = new Button("SAVE ADDRESS");
    private Button deleteAddress = new Button("DELETE ADDRESS");
    public AddressForm(){
        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.add(saveAddress,deleteAddress);
        verticalLayout.add(firstname,lastname,email,address,postIndex,city,region,phone,buttons);
        add(verticalLayout);
    }
    public void bind(){
        binder.bind(firstname, AdressDto::getFirstname,AdressDto::setFirstname);
        binder.bind(lastname,AdressDto::getLastname,AdressDto::setLastname);
        binder.bind(email,AdressDto::getEmail,AdressDto::setEmail);
        binder.bind(address,AdressDto::getAdress,AdressDto::setAdress);
        binder.bind(postIndex,AdressDto::getPostIndex,AdressDto::setPostIndex);
        binder.bind(city,AdressDto::getCity,AdressDto::setCity);
        binder.bind(phone,AdressDto::getPhone,AdressDto::setPhone);
    }
    public void setAddress(AdressDto address){
        binder.setBean(address);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {

    }
}
