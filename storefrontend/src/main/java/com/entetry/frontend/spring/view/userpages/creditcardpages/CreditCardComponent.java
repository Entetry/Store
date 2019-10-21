package com.entetry.frontend.spring.view.userpages.creditcardpages;

import com.entetry.restclient.customerclient.RestCustomerClient;
import com.entetry.storecommon.dto.CreditCardDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ReadOnlyHasValue;
import com.vaadin.flow.data.converter.StringToBigDecimalConverter;
import com.vaadin.flow.data.converter.StringToLongConverter;
public class CreditCardComponent extends HorizontalLayout {
    Binder<CreditCardDto> binder = new Binder<>(CreditCardDto.class);
    private Label idLabel = new Label("ID");
    private Label balanceLabel = new Label("BALANCE");
    private Button edit = new Button("EDIT");
    private Button delete = new Button("DELETE");

    public CreditCardComponent(RestCustomerClient restCustomerClient, PaymentMethodsView paymentMethodsView) {
        VerticalLayout labels = new VerticalLayout();
        VerticalLayout buttons = new VerticalLayout();
        labels.add(idLabel, balanceLabel);
        buttons.add(edit, delete);
        add(labels, buttons);
        edit.addClickListener(e -> edit.getUI().ifPresent(ui -> ui.navigate(CreditCardForm.class, binder.getBean().getId().toString())));
        delete.addClickListener(e->{
            restCustomerClient.deleteCreditCard(binder.getBean().getId().toString());
            paymentMethodsView.setItems();
        });
        bind();
    }

    public void bind() {
        ReadOnlyHasValue<String> id = new ReadOnlyHasValue<>(text -> idLabel.setText(text));
        ReadOnlyHasValue<String> balance = new ReadOnlyHasValue<>(text -> balanceLabel.setText(text));
        binder.forField(id).withConverter(new StringToLongConverter("CREDIT CARD COMPONENT")).bind(CreditCardDto::getId, null);
        binder.forField(balance).withConverter(new StringToBigDecimalConverter("CREDIT CARD COMPONENT balance")).bind(CreditCardDto::getBalance, null);
    }

    public void setCreditCard(CreditCardDto creditCardDto) {
        binder.setBean(creditCardDto);
    }
}
