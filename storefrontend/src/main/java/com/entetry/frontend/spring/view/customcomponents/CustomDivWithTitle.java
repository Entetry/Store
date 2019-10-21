package com.entetry.frontend.spring.view.customcomponents;

import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.html.Div;
public  class CustomDivWithTitle extends CustomField<String> {
    private final Div data = new Div();
    public CustomDivWithTitle(String label){
        setLabel(label);
        add(data);
    }
    @Override
    public String generateModelValue() {
        return data.getText();
    }

    @Override
    public void setPresentationValue(String newPresentationValue) {
        data.setText(newPresentationValue);
    }

}
