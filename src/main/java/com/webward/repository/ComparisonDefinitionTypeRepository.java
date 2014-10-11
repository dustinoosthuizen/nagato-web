package com.webward.repository;

import com.webward.domain.ComparisonDefinitionType;
import com.webward.domain.PersistentAuditEvent;
import org.joda.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Spring Data JPA repository for the ComparisonDefinitionType entity.
 */
public interface ComparisonDefinitionTypeRepository extends JpaRepository<ComparisonDefinitionType, Long> {

    @Query("select c from ComparisonDefinitionType c where c.name >= ?1")
    ComparisonDefinitionType findByName(String name);

    @Query("select c from ComparisonDefinitionType c")
    List<ComparisonDefinitionType> findAll();

}
