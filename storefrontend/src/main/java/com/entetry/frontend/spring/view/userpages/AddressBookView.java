package com.entetry.frontend.spring.view.userpages;

import com.entetry.restclient.customerclient.RestCustomerClient;
import com.entetry.storecommon.dto.AdressDto;
import com.entetry.storecommon.dto.CustomerDto;
import com.entetry.storecommon.dto.UserDetailsDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

@Route(value = AddressBookView.ROUTE, layout = MyAccountView.class)
@RouteAlias(value = "addresses",layout = MyAccountView.class)
public class AddressBookView extends VerticalLayout {
    public static final String ROUTE = "addresses";
    public static final String TITLE = "ADDRESS BOOK";
    private Label addressBookLabel = new Label("ADDRESS BOOK");
    private Button addAddressButton = new Button("ADD NEW ADDRESS");
    private final RestCustomerClient restCustomerClient;
    private CustomerDto customerDto;
    @Autowired
    public AddressBookView(RestCustomerClient restCustomerClient){
        this.restCustomerClient=restCustomerClient;
        addAddressButton.addClickListener(e->addAddressButton.getUI().ifPresent(ui->ui.navigate(AddressForm.class,"new")));
        add(addressBookLabel,addAddressButton);
        setItems();


    }

    private void setItems(){
        customerDto=restCustomerClient.getCustomerByUserId( ((UserDetailsDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId().toString());
        for (AdressDto adressDto : customerDto.getAdresses()){
            AddressComponent addressComponent = new AddressComponent();
            addressComponent.setAddress(adressDto);
            add(addressComponent);
        }
    }
}
