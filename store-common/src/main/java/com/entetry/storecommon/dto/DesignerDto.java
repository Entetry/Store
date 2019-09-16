package com.entetry.storecommon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DesignerDto implements Serializable {
    @JsonProperty
    private Long id;
    @JsonProperty
    private String designerName;
    @JsonProperty
    private UserDto user;
    @JsonProperty
    private String designerAdress;
    @JsonProperty
    private List<BankAccountDto> bankAccounts =new ArrayList<>();

    public List<BankAccountDto> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccountDto> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignerName() {
        return designerName;
    }

    public void setDesignerName(String designerName) {
        this.designerName = designerName;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getDesignerAdress() {
        return designerAdress;
    }

    public void setDesignerAdress(String designerAdress) {
        this.designerAdress = designerAdress;
    }
}
