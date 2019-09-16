package com.entetry.store.service;

import com.entetry.store.mapper.UserMapper;
import com.entetry.store.persistense.UserRepository;
import com.entetry.storecommon.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
@Service
public class UserServiceImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final LocalContainerEntityManagerFactoryBean entityManagerFactory;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, LocalContainerEntityManagerFactoryBean factoryBean) {
        this.userRepository = userRepository;
        this.entityManagerFactory = factoryBean;
    }

    public void create(UserDto userDto) {
        EntityManager entityManager = entityManagerFactory.getNativeEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            userRepository.save(UserMapper.toUser(userDto));
            transaction.commit();
        } catch (Exception e) {
            LOGGER.error("an exception occured!", e);
            transaction.rollback();
        }
        entityManager.close();
    }
}
