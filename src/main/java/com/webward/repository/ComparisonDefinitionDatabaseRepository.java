package com.webward.repository;

import com.webward.domain.ComparisonDefinition;
import com.webward.domain.ComparisonDefinitionDatabase;
        import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Spring Data JPA repository for the ComparisonDefinitionDatabase entity.
 */
public interface ComparisonDefinitionDatabaseRepository extends JpaRepository<ComparisonDefinitionDatabase, Long> {

    @Query("select c from ComparisonDefinitionDatabase c where c.comparisonDefinition.id=?1")
    ComparisonDefinitionDatabase findByComparisonDefinitionId(Long comparisonDefinitionId);

    @Query("delete from ComparisonDefinitionDatabase c where c.comparisonDefinition.id=?1")
    void deleteByComparisonDefinitionId(Long comparisonDefinition);


}
