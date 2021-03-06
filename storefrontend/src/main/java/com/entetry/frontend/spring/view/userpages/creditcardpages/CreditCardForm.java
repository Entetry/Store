package com.entetry.frontend.spring.view.userpages.creditcardpages;

import com.entetry.frontend.spring.security.SecuredByRole;
import com.entetry.frontend.spring.view.userpages.MyAccountView;
import com.entetry.restclient.customerclient.RestCustomerClient;
import com.entetry.storecommon.dto.CreditCardDto;
import com.entetry.storecommon.dto.CustomerDto;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;
@SecuredByRole("CUSTOMER_AUTHORITY")
@Route(value = CreditCardForm.ROUTE, layout = MyAccountView.class)
public class CreditCardForm extends VerticalLayout implements HasUrlParameter<String> {
    public static final String ROUTE = "payment-methods/addCard";
    public static final String TITLE = "new Card";
    private final RestCustomerClient restCustomerClient;
    private Binder<CreditCardDto> binder = new Binder<>(CreditCardDto.class);
    private TextField balance = new TextField("Balance");
    private Button saveCardButton = new Button("SAVE CARD");
    private Button createCardButton = new Button("CREATE CARD");

    @Autowired
    public CreditCardForm(RestCustomerClient restCustomerClient) {
        this.restCustomerClient = restCustomerClient;
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.add(createCardButton, saveCardButton);
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(balance, buttons);
        add(verticalLayout);
        saveCardButton.addClickListener(event -> {
            restCustomerClient.saveOrUpdateCreditCard(binder.getBean());
            UI.getCurrent().navigate(PaymentMethodsView.class);
        });
        createCardButton.addClickListener(buttonClickEvent -> {
            CustomerDto customerDto = restCustomerClient.getCustomerByUserId(((UserDetailsDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId().toString());
            CreditCardDto creditCardDto = binder.getBean();
            creditCardDto.setCustomer(customerDto);
            restCustomerClient.saveOrUpdateCreditCard(creditCardDto);
        });
        bind();
    }

    public void bind() {
        binder.forField(balance).
                withConverter(new StringToBigDecimalConverter("CREDIT CARD FORM ")).
                bind(CreditCardDto::getBalance, CreditCardDto::setBalance);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String cardId) {
        if ("new".equals(cardId)) {
            CreditCardDto creditCardDto = new CreditCardDto();
            saveCardButton.setVisible(false);
            creditCardDto.setBalance(new BigDecimal("0.00"));
            binder.setBean(creditCardDto);
        } else {
            createCardButton.setVisible(false);
            binder.setBean(restCustomerClient.getCreditCardById(cardId));
        }
    }
}
