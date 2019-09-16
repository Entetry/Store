package com.entetry.storecommon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class BankAccountDto implements Serializable {
    @JsonProperty
    private Long id;
    @JsonProperty
    private DesignerDto designer;
    @JsonProperty
    private BigDecimal balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DesignerDto getDesigner() {
        return designer;
    }

    public void setDesigner(DesignerDto designer) {
        this.designer = designer;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
