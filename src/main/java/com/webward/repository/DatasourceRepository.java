package com.webward.repository;

import com.webward.domain.ComparisonDatasource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Datasource entity.
 */
public interface DatasourceRepository extends JpaRepository<ComparisonDatasource, Long> {

}
