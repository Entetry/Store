package com.entetry.frontend.spring.view.designerpages;

import com.entetry.frontend.spring.security.SecuredByRole;
import com.entetry.restclient.designerclient.RestDesignerClient;
import com.entetry.storecommon.dto.BankAccountDto;
import com.entetry.storecommon.dto.DesignerDto;
import com.entetry.storecommon.dto.UserDetailsDto;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToBigDecimalConverter;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.context.SecurityContextHolder;
import java.math.BigDecimal;
@SecuredByRole("DESIGNER_AUTHORITY")
@Route(value = BankAccountForm.ROUTE, layout = DesignerAccountView.class)
public class BankAccountForm extends VerticalLayout implements HasUrlParameter<String> {
    public static final String ROUTE = "bank-accounts/addAccount";
    public static final String TITLE = "new Account";
    private Binder<BankAccountDto> binder = new Binder<>(BankAccountDto.class);
    private TextField balance = new TextField("Balance");
    private Button saveCardButton = new Button("SAVE ACCOUNT");
    private Button createCardButton = new Button("CREATE ACCOUNT");
    private final RestDesignerClient restDesignerClient;
    public BankAccountForm(RestDesignerClient restDesignerClient) {
        this.restDesignerClient=restDesignerClient;
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.add(createCardButton, saveCardButton);
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(balance, buttons);
        add(verticalLayout);
        bind();
        saveCardButton.addClickListener(event -> {
            restDesignerClient.saveOrUpdateBankAccount(binder.getBean());
            UI.getCurrent().navigate(BankAccountsView.class);
        });
        createCardButton.addClickListener(buttonClickEvent -> {
            DesignerDto designerDto = restDesignerClient.getDesignerByUserId(((UserDetailsDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId().toString());
            BankAccountDto bankAccountDto = binder.getBean();
            bankAccountDto.setDesigner(designerDto);
            restDesignerClient.saveOrUpdateBankAccount(bankAccountDto);
            UI.getCurrent().navigate(BankAccountsView.class);
        });
    }

    public void bind() {
        binder.forField(balance).
                withConverter(new StringToBigDecimalConverter("CREDIT CARD FORM ")).
                bind(BankAccountDto::getBalance, BankAccountDto::setBalance);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String accountId) {
        if ("new".equals(accountId)) {
            BankAccountDto bankAccountDto = new BankAccountDto();
            saveCardButton.setVisible(false);
            bankAccountDto.setBalance(new BigDecimal("0.00"));
            binder.setBean(bankAccountDto);
        } else {
            createCardButton.setVisible(false);
             binder.setBean(restDesignerClient.getBankAccountById(accountId));
        }
    }
}

