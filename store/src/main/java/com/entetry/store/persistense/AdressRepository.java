package com.entetry.store.persistense;

import com.entetry.store.entity.Adress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdressRepository extends CrudRepository<Adress, Long> {
}
