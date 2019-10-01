package com.entetry.frontend.spring.view.userpages;

import com.entetry.storecommon.dto.CustomerDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToDateConverter;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

//
@Route(value = MyDetailsView.ROUTE, layout = MyAccountView.class)
public class MyDetailsView extends FormLayout implements HasUrlParameter<String> {
    public static final String ROUTE = "my-details";
    public static final String TITLE = "My details";
    private Binder<CustomerDto> binder = new Binder<>(CustomerDto.class);
    private TextField firstname = new TextField("FIRST NAME");
    private TextField lastname = new TextField("LAST NAME");
    private TextField email =  new TextField("EMAIL");
    private TextField dateOfBirth = new TextField("DATE OF BIRTH");
    private Button button = new Button("SAVE CHANGES");
    public MyDetailsView(){
        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.add(button);
        verticalLayout.add(firstname,lastname,email,dateOfBirth,buttons);
        add(verticalLayout);
    }
    public void bind(){
        binder.bind(firstname,CustomerDto::getFirstname,CustomerDto::setFirstname);
        binder.bind(lastname,CustomerDto::getLastname,CustomerDto::setLastname);
        binder.bind(email,customerDto -> customerDto.getUser().getEmail(),(customerDto, s) -> customerDto.getUser().setEmail(s));
        binder.forField(dateOfBirth).withConverter(new StringToDateConverter()).bind(CustomerDto::getDateOfBirth,CustomerDto::setDateOfBirth);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String customerId) {
    }
}
