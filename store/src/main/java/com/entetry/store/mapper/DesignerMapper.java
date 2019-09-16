package com.entetry.store.mapper;

import com.entetry.store.entity.BankAccount;
import com.entetry.store.entity.Designer;
import com.entetry.storecommon.dto.DesignerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DesignerMapper {
    private  final UserMapper userMapper;
    private final BankAccountMapper bankAccountMapper;
    @Autowired
    public DesignerMapper(UserMapper userMapper,BankAccountMapper bankAccountMapper){
        this.userMapper=userMapper;
        this.bankAccountMapper=bankAccountMapper;
    }
    public  DesignerDto toDesignerDto(Designer designer) {
        DesignerDto designerDto = new DesignerDto();
        designerDto.setId(designer.getId());
        designerDto.setDesignerAdress(designer.getDesignerAdress());
        designerDto.setDesignerName(designer.getDesignerName());
        designerDto.setUser(userMapper.toUserDto(designer.getUser()));
        designerDto.setBankAccounts(designer.getBankAccounts().stream().map(bankAccountMapper::toBankAccountDto).collect(Collectors.toList()));
        return designerDto;
    }
    public  Designer toDesigner(DesignerDto designerDto) {
        Designer designer = new Designer();
        designer.setId(designerDto.getId());
        designer.setDesignerName(designerDto.getDesignerName());
        designer.setDesignerAdress(designerDto.getDesignerAdress());
        designer.setUser(userMapper.toUser(designerDto.getUser()));
        designer.setBankAccounts(designerDto.getBankAccounts().stream().map(bankAccountMapper::toBankAccount).collect(Collectors.toList()));
        return designer;
    }
}
