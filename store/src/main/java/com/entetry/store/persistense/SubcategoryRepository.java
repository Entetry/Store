package com.entetry.store.persistense;

import com.entetry.store.entity.Subcategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoryRepository extends CrudRepository<Subcategory, Long> {
}
