package com.entetry.store.service;

import com.entetry.store.entity.CreditCard;
import com.entetry.store.exception.CreditCardNotFoundException;
import com.entetry.store.mapper.CreditCardMapper;
import com.entetry.store.persistense.CreditCardRepository;
import com.entetry.storecommon.dto.CreditCardDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreditCardServiceImpl {
    private final CreditCardMapper creditCardMapper;
    private final CreditCardRepository creditCardRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(DesignerServiceImpl.class);

    @Autowired
    public CreditCardServiceImpl(CreditCardMapper creditCardMapper, CreditCardRepository creditCardRepository) {
        this.creditCardMapper = creditCardMapper;
        this.creditCardRepository = creditCardRepository;
    }

    @Transactional
    public void create(CreditCardDto cardDto) {
        try {
            creditCardRepository.save(creditCardMapper.toCreditCard(cardDto));
        } catch (Exception e) {
            LOGGER.error("an exception occurred!", e);
            throw e;
        }
    }

    @Transactional
    public void update(CreditCardDto cardDto) {
        CreditCard creditCard = creditCardRepository.findById(cardDto.getId()).orElseThrow(CreditCardNotFoundException::new);
        CreditCard updatedCard = creditCardMapper.toCreditCard(cardDto);
        try {
            creditCardRepository.save(updatedCard);
        } catch (Exception e) {
            LOGGER.error("an exception occured!", e);
            throw e;
        }
    }

    @Transactional
    public CreditCardDto getCreditCardById(String id) {
        return creditCardMapper.toCreditCardDto(creditCardRepository.findById(Long.parseLong(id)).orElseThrow(CreditCardNotFoundException::new));
    }

    @Transactional
    public void delete(String id) {
        CreditCard creditCard = creditCardRepository.findById(Long.parseLong(id)).orElseThrow(CreditCardNotFoundException::new);
        try {
            creditCardRepository.delete(creditCard);
        } catch (Exception e) {
            LOGGER.error("an exception occured!", e);
            throw e;
        }
    }
}
