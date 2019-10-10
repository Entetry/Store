package com.entetry.store.service;

import com.entetry.store.entity.BankAccount;
import com.entetry.store.exception.BankAccountNotFoundException;
import com.entetry.store.mapper.BankAccountMapper;
import com.entetry.store.persistense.BankAccountRepository;
import com.entetry.storecommon.dto.BankAccountDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final BankAccountMapper bankAccountMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(BankAccountService.class);

    public BankAccountService(BankAccountRepository bankAccountRepository, BankAccountMapper bankAccountMapper){
        this.bankAccountRepository=bankAccountRepository;
        this.bankAccountMapper=bankAccountMapper;
    }
    @Transactional
    public void create(BankAccountDto accountDto) {
        try {
            bankAccountRepository.save(bankAccountMapper.toBankAccount(accountDto));
        } catch (Exception e) {
            LOGGER.error("an exception occurred!", e);
            throw e;
        }
    }

    @Transactional
    public void update(BankAccountDto accountDto) {
        BankAccount bankAccount = bankAccountRepository.findById(accountDto.getId()).orElseThrow(BankAccountNotFoundException::new);
        BankAccount updatedAccount = bankAccountMapper.toBankAccount(accountDto);
        try {
            bankAccountRepository.save(updatedAccount);
        } catch (Exception e) {
            LOGGER.error("an exception occured!", e);
            throw e;
        }
    }

    @Transactional
    public BankAccountDto getBankAccountById(String id) {
        return bankAccountMapper.toBankAccountDto(bankAccountRepository.findById(Long.parseLong(id)).orElseThrow(BankAccountNotFoundException::new));
    }

    @Transactional
    public void delete(String id) {
        BankAccount bankAccount = bankAccountRepository.findById(Long.parseLong(id)).orElseThrow(BankAccountNotFoundException::new);
        try {
            bankAccountRepository.delete(bankAccount);
        } catch (Exception e) {
            LOGGER.error("an exception occured!", e);
            throw e;
        }
    }
}
