package com.entetry.store.persistense;

import com.entetry.store.entity.Size;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends CrudRepository<Size, Long> {
}
