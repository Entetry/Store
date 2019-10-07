package com.entetry.frontend.spring.view.userpages;

import com.entetry.restclient.customerclient.RestCustomerClient;
import com.entetry.storecommon.dto.CustomerDto;
import com.entetry.storecommon.dto.UserDetailsDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToDateConverter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

//
@Route(value = MyDetailsView.ROUTE, layout = MyAccountView.class)
public class MyDetailsView extends FormLayout {
    public static final String ROUTE = "my-details";
    public static final String TITLE = "My details";
    private Binder<CustomerDto> binder = new Binder<>(CustomerDto.class);
    private TextField firstname = new TextField("FIRST NAME");
    private TextField lastname = new TextField("LAST NAME");
    private TextField email =  new TextField("EMAIL");
    private TextField dateOfBirth = new TextField("DATE OF BIRTH");
    private Button saveButton = new Button("SAVE CHANGES");
    private final RestCustomerClient restCustomerClient;
    private CustomerDto customerDto;
    @Autowired
    public MyDetailsView(RestCustomerClient restCustomerClient){
        this.restCustomerClient=restCustomerClient;
        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.add(saveButton);
        verticalLayout.add(firstname,lastname,email,dateOfBirth,buttons);
        add(verticalLayout);
        setItems();
        saveButton.addClickListener(event->
                restCustomerClient.updateCustomer(binder.getBean())
                );
    }
    private void bind(){
        binder.bind(firstname,CustomerDto::getFirstname,CustomerDto::setFirstname);
        binder.bind(lastname,CustomerDto::getLastname,CustomerDto::setLastname);
        binder.bind(email,customerDto -> customerDto.getUser().getEmail(),(customerDto, s) -> customerDto.getUser().setEmail(s));
        binder.forField(dateOfBirth).withConverter(new StringToDateConverter()).bind(CustomerDto::getDateOfBirth,CustomerDto::setDateOfBirth);
    }

    private void setItems(){
        customerDto=restCustomerClient.getCustomerByUserId( ((UserDetailsDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId().toString());
       binder.setBean(customerDto);
        bind();
    }
}
