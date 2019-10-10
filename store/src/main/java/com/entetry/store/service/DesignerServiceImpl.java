package com.entetry.store.service;

import com.entetry.store.entity.Designer;
import com.entetry.store.entity.User;
import com.entetry.store.exception.DesignerNotFoundException;
import com.entetry.store.exception.UserNotFoundException;
import com.entetry.store.mapper.BankAccountMapper;
import com.entetry.store.mapper.DesignerMapper;
import com.entetry.store.persistense.DesignerRepository;
import com.entetry.store.persistense.UserRepository;
import com.entetry.storecommon.dto.BankAccountDto;
import com.entetry.storecommon.dto.DesignerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DesignerServiceImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(DesignerServiceImpl.class);
    private final DesignerRepository designerRepository;
    private final DesignerMapper designerMapper;
    private final BankAccountMapper bankAccountMapper;
    private final UserRepository userRepository;

    @Autowired
    public DesignerServiceImpl(DesignerMapper designerMapper, DesignerRepository designerRepository,
                               BankAccountMapper bankAccountMapper,UserRepository userRepository) {
        this.designerRepository = designerRepository;
        this.designerMapper = designerMapper;
        this.bankAccountMapper = bankAccountMapper;
        this.userRepository=userRepository;
    }

    @Transactional
    public void create(DesignerDto designerDto) {
        try {
            designerRepository.save(designerMapper.toDesigner(designerDto));
        } catch (Exception e) {
            LOGGER.error("an exception occurred!", e);
            throw e;
        }
    }

    @Transactional
    public DesignerDto getDesignerByUserId(String id) {
        User user = userRepository.findById(Long.parseLong(id)).orElseThrow(UserNotFoundException::new);
        return designerMapper.toDesignerDto(designerRepository.findDesignerByUser(user));
    }

    @Transactional
    public void update(DesignerDto designerDto) {
        Designer designer = designerRepository.findById(designerDto.getId()).orElseThrow(DesignerNotFoundException::new);
        Designer updatedDesigner = designerMapper.toDesigner(designerDto);
        try {
            designerRepository.save(updatedDesigner);
        } catch (Exception e) {
            LOGGER.error("an exception occured!", e);
            throw e;
        }
    }

    @Transactional
    public void delete(String id) {
        Designer designer = designerRepository.findById(Long.parseLong(id)).orElseThrow(DesignerNotFoundException::new);
        try {
            designerRepository.delete(designer);
        } catch (Exception e) {
            LOGGER.error("an exception occured!", e);
            throw e;
        }
    }

    public List<DesignerDto> getAllDesigners() {
        return StreamSupport.stream(designerRepository.findAll().spliterator(), false).map(designerMapper::toDesignerDto).collect(Collectors.toList());
    }

    @Transactional
    public void addBankAccount(BankAccountDto bankAccountDto) {
        Designer designer = designerRepository.findById(bankAccountDto.getDesigner().getId()).orElseThrow(DesignerNotFoundException::new);
        designer.addBankAccount(bankAccountMapper.toBankAccount(bankAccountDto));
        try {
            designerRepository.save(designer);
        } catch (Exception e) {
            LOGGER.error("an exception occured!", e);
            throw e;
        }
    }
}
