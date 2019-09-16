package com.entetry.store.mapper;

import com.entetry.store.entity.BankAccount;
import com.entetry.store.entity.Designer;
import com.entetry.storecommon.dto.BankAccountDto;
import com.entetry.storecommon.dto.DesignerDto;
import org.springframework.stereotype.Component;

@Component
public class BankAccountMapper {
    private final UserMapper userMapper;
    public BankAccountMapper(UserMapper userMapper){
        this.userMapper=userMapper;
    }
    public BankAccountDto toBankAccountDto(BankAccount bankAccount){
        BankAccountDto bankAccountDto= new BankAccountDto();
        bankAccountDto.setId(bankAccount.getId());
        bankAccountDto.setBalance(bankAccount.getBalance());
        DesignerDto designerDto = new DesignerDto();
        designerDto.setUser(userMapper.toUserDto(bankAccount.getDesigner().getUser()));
        designerDto.setDesignerName(bankAccount.getDesigner().getDesignerName());
        designerDto.setDesignerAdress(bankAccount.getDesigner().getDesignerAdress());
        designerDto.setId(bankAccount.getDesigner().getId());
        bankAccountDto.setDesigner(designerDto);
        return bankAccountDto;
    }
    public BankAccount toBankAccount(BankAccountDto bankAccountDto){
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(bankAccountDto.getId());
        bankAccount.setBalance(bankAccountDto.getBalance());
        Designer designer = new Designer();
        designer.setUser(userMapper.toUser(bankAccountDto.getDesigner().getUser()));
        designer.setDesignerName(bankAccountDto.getDesigner().getDesignerName());
        designer.setDesignerAdress(bankAccountDto.getDesigner().getDesignerAdress());
        designer.setId(bankAccountDto.getDesigner().getId());
        bankAccount.setDesigner(designer);
        return bankAccount;
    }
}
