package com.webward.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * A ComparisonDefinitionType.
 */
@Entity
@Table(name = "T_COMPARISON_DEFINITION_TYPE")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ComparisonDefinitionType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Size(min = 1, max = 500)
    @Column(name = "name", length = 500)
    private String name;

//    @OneToMany(cascade=CascadeType.ALL, mappedBy="comparisonDefintionType")
//    private List<ComparisonDefinition> comparisonDefinitions;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ComparisonDefinitionType comparisondefinitiontype = (ComparisonDefinitionType) o;

        if (id != comparisondefinitiontype.id) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "ComparisonDefinitionType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

//    public List<ComparisonDefinition> getComparisonDefinitions() {
//        return comparisonDefinitions;
//    }
//
//    public void setComparisonDefinitions(List<ComparisonDefinition> comparisonDefinitions) {
//        this.comparisonDefinitions = comparisonDefinitions;
//    }
}
