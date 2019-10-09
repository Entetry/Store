package com.entetry.store.service;

import com.entetry.store.entity.Adress;
import com.entetry.store.exception.AddressNotFoundException;
import com.entetry.store.mapper.AdressMapper;
import com.entetry.store.persistense.AdressRepository;
import com.entetry.storecommon.dto.AdressDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressServiceImpl {
    private final AdressMapper adressMapper;
    private final AdressRepository adressRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(DesignerServiceImpl.class);

    @Autowired
    public AddressServiceImpl(AdressMapper adressMapper, AdressRepository adressRepository) {
        this.adressMapper = adressMapper;
        this.adressRepository = adressRepository;
    }

    @Transactional
    public void create(AdressDto adressDto) {
        try {
            adressRepository.save(adressMapper.toAdress(adressDto));
        } catch (Exception e) {
            LOGGER.error("an exception occurred!", e);
            throw e;
        }
    }

    @Transactional
    public void update(AdressDto adressDto) {
        Adress address = adressRepository.findById(adressDto.getId()).orElseThrow(AddressNotFoundException::new);
        Adress updatedAddress = adressMapper.toAdress(adressDto);
        try {
            adressRepository.save(updatedAddress);
        } catch (Exception e) {
            LOGGER.error("an exception occured!", e);
            throw e;
        }
    }

    @Transactional
    public AdressDto getAddressById(String id) {
        return adressMapper.toAdressDto(adressRepository.findById(Long.parseLong(id)).orElseThrow(AddressNotFoundException::new));
    }

    @Transactional
    public void delete(String id) {
        Adress adress = adressRepository.findById(Long.parseLong(id)).orElseThrow(AddressNotFoundException::new);
        try {
            adressRepository.delete(adress);
        } catch (Exception e) {
            LOGGER.error("an exception occured!", e);
            throw e;
        }
    }

}
