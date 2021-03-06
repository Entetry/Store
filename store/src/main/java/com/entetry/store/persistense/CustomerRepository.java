package com.entetry.store.persistense;

import com.entetry.store.entity.Customer;
import com.entetry.store.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findCustomerByUser(User user);
}
