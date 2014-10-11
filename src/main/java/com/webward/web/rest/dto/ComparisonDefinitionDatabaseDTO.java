package com.webward.web.rest.dto;

import com.webward.domain.ComparisonDefinition;

/**
 * Created by dustinosthzn on 2014/09/25.
 */
public class ComparisonDefinitionDatabaseDTO {

    public Long id;
    public ComparisonDefinitionDTO comparisonDefinition;
    public String sourceQuery;
    public String targetQuery;
    public DatasourceDTO sourceDatasource;
    public DatasourceDTO targetDatasource;
    private String sourceKeytomatch;
    private String targetKeytomatch;
    private String sourceAttributetocompare;
    private String targetAttributetocompare;


    public String getSourceQuery() {
        return sourceQuery;
    }

    public void setSourceQuery(String sourceQuery) {
        this.sourceQuery = sourceQuery;
    }

    public String getTargetQuery() {
        return targetQuery;
    }

    public void setTargetQuery(String targetQuery) {
        this.targetQuery = targetQuery;
    }

    public ComparisonDefinitionDTO getComparisonDefinition() {
        return comparisonDefinition;
    }

    public void setComparisonDefinition(ComparisonDefinitionDTO comparisonDefinition) {
        this.comparisonDefinition = comparisonDefinition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DatasourceDTO getSourceDatasource() {
        return sourceDatasource;
    }

    public void setSourceDatasource(DatasourceDTO sourceDatasource) {
        this.sourceDatasource = sourceDatasource;
    }

    public DatasourceDTO getTargetDatasource() {
        return targetDatasource;
    }

    public void setTargetDatasource(DatasourceDTO targetDatasource) {
        this.targetDatasource = targetDatasource;
    }

    public String getSourceKeytomatch() {
        return sourceKeytomatch;
    }

    public void setSourceKeytomatch(String sourceKeytomatch) {
        this.sourceKeytomatch = sourceKeytomatch;
    }

    public String getTargetKeytomatch() {
        return targetKeytomatch;
    }

    public void setTargetKeytomatch(String targetKeytomatch) {
        this.targetKeytomatch = targetKeytomatch;
    }

    public String getSourceAttributetocompare() {
        return sourceAttributetocompare;
    }

    public void setSourceAttributetocompare(String sourceAttributetocompare) {
        this.sourceAttributetocompare = sourceAttributetocompare;
    }

    public String getTargetAttributetocompare() {
        return targetAttributetocompare;
    }

    public void setTargetAttributetocompare(String targetAttributetocompare) {
        this.targetAttributetocompare = targetAttributetocompare;
    }
}
