package com.webward.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A ComparisonDefinition.
 */
@Entity
@Table(name = "T_COMPARISON_DEFINITION")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ComparisonDefinition implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Size(min = 1, max = 500)
    @Column(name = "name", length = 500)
    private String name;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "type", referencedColumnName = "id")
    private ComparisonDefinitionType comparisonDefintionType;

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

    public ComparisonDefinitionType getComparisonDefintionType() {
        return comparisonDefintionType;
    }

    public void setComparisonDefintionType(ComparisonDefinitionType comparisonDefintionType) {
        this.comparisonDefintionType = comparisonDefintionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ComparisonDefinition comparisondefinition = (ComparisonDefinition) o;

        if (id != comparisondefinition.id) {
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
        return "ComparisonDefinition{" +
                "id=" + id +
                ", name='" + name + '\'' +
               // ", sampleDateAttribute=" + comparisonDefintionType!=null?comparisonDefintionType.getName():"" +
                '}';
    }
}
