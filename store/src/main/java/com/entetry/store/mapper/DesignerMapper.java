package com.entetry.store.mapper;

import com.entetry.store.entity.Designer;
import com.entetry.storecommon.dto.DesignerDto;

public class DesignerMapper {
    public static DesignerDto toDesignerDto(Designer designer) {
        DesignerDto designerDto = new DesignerDto();
        designerDto.setId(designer.getId());
        designerDto.setDesignerAdress(designer.getDesignerAdress());
        designerDto.setDesignerName(designer.getDesignerName());
        designerDto.setUser(UserMapper.toUserDto(designer.getUser()));
        return designerDto;
    }

    public static Designer toDesigner(DesignerDto designerDto) {
        Designer designer = new Designer();
        designer.setId(designerDto.getId());
        designer.setDesignerName(designerDto.getDesignerName());
        designer.setDesignerAdress(designerDto.getDesignerAdress());
        designer.setUser(UserMapper.toUser(designerDto.getUser()));
        return designer;
    }
}
