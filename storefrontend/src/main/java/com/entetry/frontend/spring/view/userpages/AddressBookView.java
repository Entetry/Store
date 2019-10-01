package com.entetry.frontend.spring.view.userpages;

import com.entetry.storecommon.dto.AdressDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

import java.util.ArrayList;
import java.util.List;

@Route(value = AddressBookView.ROUTE, layout = MyAccountView.class)
public class AddressBookView extends VerticalLayout implements HasUrlParameter<String>{
    public static final String ROUTE = "addresses";
    public static final String TITLE = "ADDRESS BOOK";
    private Label addressBookLabel = new Label("ADDRESS BOOK");
    private Button addAddressButton = new Button("ADD NEW ADDRESS");
    private List<AdressDto>  addresses = new ArrayList<>();
    public AddressBookView(){
        addAddressButton.addClickListener(e->addAddressButton.getUI().ifPresent(ui->ui.navigate(AddressForm.class,"3")));
        add(addressBookLabel,addAddressButton);
    }
//
    @Override
    public void setParameter(BeforeEvent beforeEvent, String customerId) {
    }
//        for (AdressDto adressDto : addresses){
//        AddressComponent addressComponent = new AddressComponent();
//        addressComponent.setAddress(adressDto);
//        add(addressComponent);
//    }
}
