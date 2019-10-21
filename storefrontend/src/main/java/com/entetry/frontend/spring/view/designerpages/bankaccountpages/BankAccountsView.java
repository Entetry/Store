package com.entetry.frontend.spring.view.designerpages.bankaccountpages;

import com.entetry.frontend.spring.security.SecuredByRole;
import com.entetry.frontend.spring.view.designerpages.DesignerAccountView;
import com.entetry.restclient.designerclient.RestDesignerClient;
import com.entetry.storecommon.dto.BankAccountDto;
import com.entetry.storecommon.dto.DesignerDto;
import com.entetry.storecommon.dto.UserDetailsDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
@SecuredByRole("DESIGNER_AUTHORITY")
@Route(value = BankAccountsView.ROUTE, layout = DesignerAccountView.class)
public class BankAccountsView extends VerticalLayout {
    public static final String ROUTE = "bank-accounts";
    public static final String TITLE = "bank-accounts";
    private Label paymentMethodsViewLabel = new Label("Bank accounts");
    private final RestDesignerClient restDesignerClient;
    private DesignerDto designerDto;
    private Button addAccountButton = new Button("ADD NEW ACCOUNT");

    @Autowired
    public BankAccountsView(RestDesignerClient restDesignerClient){
        this.restDesignerClient=restDesignerClient;
        setItems();
        addAccountButton.addClickListener(e -> addAccountButton.getUI().ifPresent(ui -> ui.navigate(BankAccountForm.class, "new")));

    }
    public void setItems() {
        removeAll();
        add(paymentMethodsViewLabel,addAccountButton);
        designerDto = restDesignerClient.getDesignerByUserId(((UserDetailsDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId().toString());
        for (BankAccountDto bankAccountDto : designerDto.getBankAccounts()) {
            BankAccountComponent bankAccountComponent = new BankAccountComponent(restDesignerClient,this);
            bankAccountComponent.setBankAccount(bankAccountDto);
            add(bankAccountComponent);
        }
    }
}
