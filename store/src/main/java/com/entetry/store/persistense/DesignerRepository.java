package com.entetry.store.persistense;

import com.entetry.store.entity.Designer;
import com.entetry.store.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignerRepository extends CrudRepository<Designer, Long> {
}
