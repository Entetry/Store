package com.entetry.frontend.spring.service;

import com.entetry.storecommon.dto.ShoppingCard;
import com.entetry.storecommon.dto.ItemDto;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Service
public class ShoppingCardService {
    public void addToShoppingCard(ItemDto itemDto){
        getShoppingCard().addItem(itemDto);
    }
    public void deleteItemFromShoppingCard(ItemDto itemDto){
        getShoppingCard().getItems().remove(itemDto);
    }
    public ShoppingCard getShoppingCard(){
        ServletRequestAttributes attrt = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attrt.getRequest().getSession();
        if(session.getAttribute("Shopping Card")==null){
            session.setAttribute("Shopping Card",new ShoppingCard());
            return (ShoppingCard) session.getAttribute("Shopping Card");

        }
        else {
            return (ShoppingCard) session.getAttribute("Shopping Card");
        }
    }
    public void orderAllItems(){

    }
}
