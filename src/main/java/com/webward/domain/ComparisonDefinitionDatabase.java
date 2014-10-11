package com.webward.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


import javax.persistence.*;
import java.io.Serializable;

/**
 * A ComparisonDefinitionDatabase.
 */
@Entity
@Table(name = "T_COMPARISON_DEFINITION_DATABASE")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ComparisonDefinitionDatabase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "comparison_definition", referencedColumnName = "id")
    private ComparisonDefinition comparisonDefinition;

    @Column(name = "sourceQuery", columnDefinition="TEXT")
    private String sourceQuery;

    @Column(name = "targetQuery", columnDefinition="TEXT")
    private String targetQuery;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "source_datasource", referencedColumnName = "id")
    private ComparisonDatasource sourceDataSource;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "target_datasource", referencedColumnName = "id")
    private ComparisonDatasource targetDataSource;

    @Column(name = "source_keytomatch")
    private String sourceKeytomatch;

    @Column(name = "target_keytomatch")
    private String targetKeytomatch;

    @Column(name = "source_attributetocompare")
    private String sourceAttributetocompare;

    @Column(name = "target_attributetocompare")
    private String targetAttributetocompare;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ComparisonDefinition getComparisonDefinition() {
        return comparisonDefinition;
    }

    public void setComparisonDefinition(ComparisonDefinition comparisonDefinition) {
        this.comparisonDefinition = comparisonDefinition;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ComparisonDefinitionDatabase comparisondefinitiondatabase = (ComparisonDefinitionDatabase) o;

        if (id != comparisondefinitiondatabase.id) {
            return false;
        }

        return true;
    }

    public ComparisonDatasource getSourceDataSource() {
        return sourceDataSource;
    }

    public void setSourceDataSource(ComparisonDatasource sourceDataSource) {
        this.sourceDataSource = sourceDataSource;
    }

    public ComparisonDatasource getTargetDataSource() {
        return targetDataSource;
    }

    public void setTargetDataSource(ComparisonDatasource targetDataSource) {
        this.targetDataSource = targetDataSource;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "ComparisonDefinitionDatabase{" +
                "id=" + id +
                ", sourceQuery='" + sourceQuery + '\'' +
                ", targetQuery=" + targetQuery +
                '}';
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
