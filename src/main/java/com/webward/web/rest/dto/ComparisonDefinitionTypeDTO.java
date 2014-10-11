package com.webward.web.rest.dto;

/**
 * Created by dustinosthzn on 2014/09/25.
 */
public class ComparisonDefinitionTypeDTO {
    public Long id;
    public String name;

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

        ComparisonDefinitionDTO comparisondefinition = (ComparisonDefinitionDTO) o;

        if (name != comparisondefinition.name) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (name.hashCode());
    }

    @Override
    public String toString() {
        return "ComparisonDefinitionTypeDTO{" +
                "id=" + id +
                "name=" + name +
                '}';
    }
}
