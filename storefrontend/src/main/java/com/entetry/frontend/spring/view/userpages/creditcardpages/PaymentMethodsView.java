package com.entetry.frontend.spring.view.userpages.creditcardpages;

import com.entetry.frontend.spring.security.SecuredByRole;
import com.entetry.frontend.spring.view.userpages.MyAccountView;
import com.entetry.restclient.customerclient.RestCustomerClient;
import com.entetry.storecommon.dto.CreditCardDto;
import com.entetry.storecommon.dto.CustomerDto;
import com.entetry.storecommon.dto.UserDetailsDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
@SecuredByRole("CUSTOMER_AUTHORITY")
@Route(value = PaymentMethodsView.ROUTE, layout = MyAccountView.class)
public class PaymentMethodsView extends VerticalLayout {
    public static final String ROUTE = "payment-methods";
    public static final String TITLE = "Payment methods";
    private Label paymentMethodsViewLabel = new Label("Credit cards");
    private final RestCustomerClient restCustomerClient;
    private CustomerDto customerDto;
    private Button addCreditCardButton = new Button("ADD NEW CARD");

    @Autowired
    public PaymentMethodsView(RestCustomerClient restCustomerClient) {
        this.restCustomerClient = restCustomerClient;
        setItems();
        addCreditCardButton.addClickListener(e -> addCreditCardButton.getUI().ifPresent(ui -> ui.navigate(CreditCardForm.class, "new")));
    }

    public void setItems() {
        removeAll();
        add(addCreditCardButton);
        add(paymentMethodsViewLabel);
        customerDto = restCustomerClient.getCustomerByUserId(((UserDetailsDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId().toString());
        for (CreditCardDto creditCardDto : customerDto.getCreditCards()) {
            CreditCardComponent creditCardComponent = new CreditCardComponent(restCustomerClient,this);
            creditCardComponent.setCreditCard(creditCardDto);
            add(creditCardComponent);
        }
    }
}
