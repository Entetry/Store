package com.entetry.frontend.spring.view.designerpages;

import com.entetry.frontend.spring.security.SecuredByRole;
import com.entetry.restclient.designerclient.RestDesignerClient;
import com.entetry.storecommon.dto.DesignerDto;
import com.entetry.storecommon.dto.UserDetailsDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.context.SecurityContextHolder;
@SecuredByRole("DESIGNER_AUTHORITY")
@Route(value = DesignerDetailsView.ROUTE, layout = DesignerAccountView.class)
public class DesignerDetailsView  extends VerticalLayout{
    public static final String ROUTE = "designer-details";
    public static final String TITLE = "Designer details";
    private TextField designerName = new TextField("DESIGNER NAME");
    private TextField designerAddress = new TextField("ADDRESS");
    private Binder<DesignerDto> binder = new Binder<>(DesignerDto.class);
    private Button saveButton = new Button("SAVE CHANGES");
    private final RestDesignerClient restDesignerClient;
    public DesignerDetailsView(RestDesignerClient restDesignerClient){
        this.restDesignerClient=restDesignerClient;
        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.add(saveButton);
        verticalLayout.add(designerName,designerAddress, buttons);
        add(verticalLayout);
        setItems();
        saveButton.addClickListener(event ->
                restDesignerClient.updateDesigner(binder.getBean())
        );
    }
    public void bind(){
        binder.forField(designerName).bind(DesignerDto::getDesignerName,DesignerDto::setDesignerName);
        binder.forField(designerAddress).bind(DesignerDto::getDesignerAdress,DesignerDto::setDesignerAdress);

    }

    private void setItems()
    {
        binder.setBean(restDesignerClient.getDesignerByUserId(((UserDetailsDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId().toString()));
        bind();
    }
}
