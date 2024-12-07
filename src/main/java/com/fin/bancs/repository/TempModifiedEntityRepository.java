package com.fin.bancs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fin.bancs.utils.TempModifiedEntity;

@Repository
public interface TempModifiedEntityRepository extends JpaRepository<TempModifiedEntity, String> {

	TempModifiedEntity findByModifiedKeyAndEntityName(String modifiedKey, String entityName);
}
