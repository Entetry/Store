package com.entetry.frontend.spring.view.designerpages;

import com.entetry.restclient.designerclient.RestDesignerClient;
import com.entetry.storecommon.dto.BankAccountDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ReadOnlyHasValue;
import com.vaadin.flow.data.converter.StringToBigDecimalConverter;
import com.vaadin.flow.data.converter.StringToLongConverter;

public class BankAccountComponent  extends HorizontalLayout {
    Binder<BankAccountDto> binder = new Binder<>(BankAccountDto.class);
    private Label idLabel = new Label("ID");
    private Label balanceLabel = new Label("Balance");
    private Button edit = new Button("EDIT");
    private Button delete = new Button("DELETE");

    public BankAccountComponent(RestDesignerClient restDesignerClient,BankAccountsView bankAccountsView) {
        VerticalLayout labels = new VerticalLayout();
        VerticalLayout buttons = new VerticalLayout();
        labels.add(idLabel, balanceLabel);
        buttons.add(edit, delete);
        add(labels, buttons);
        edit.addClickListener(e -> edit.getUI().ifPresent(ui -> ui.navigate(BankAccountForm.class, binder.getBean().getId().toString())));
        delete.addClickListener(e->{
            restDesignerClient.deleteBankAccount(binder.getBean().getId().toString());
            bankAccountsView.setItems();
        });
        bind();
    }

    public void bind() {
        ReadOnlyHasValue<String> id = new ReadOnlyHasValue<>(text -> idLabel.setText(text));
        ReadOnlyHasValue<String> balance = new ReadOnlyHasValue<>(text -> balanceLabel.setText(text));
        binder.forField(id).withConverter(new StringToLongConverter("BANK ACCOUNT COMPONENT")).bind(BankAccountDto::getId, null);
        binder.forField(balance).withConverter(new StringToBigDecimalConverter("BANK ACCOUNT COMPONENT balance")).bind(BankAccountDto::getBalance, null);
    }

    public void setBankAccount(BankAccountDto bankAccountDto) {
        binder.setBean(bankAccountDto);
    }
}
