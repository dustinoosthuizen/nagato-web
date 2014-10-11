package com.webward.repository;

import com.webward.domain.ComparisonDefinition;
import com.webward.domain.ComparisonDefinitionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Spring Data JPA repository for the ComparisonDefinition entity.
 */
public interface ComparisonDefinitionRepository extends JpaRepository<ComparisonDefinition, Long> {

    @Query("select c from ComparisonDefinition c")
    List<ComparisonDefinition> findAll();

    @Query("select c from ComparisonDefinition c where c.name=?1")
    ComparisonDefinition findByName(String name);


}
