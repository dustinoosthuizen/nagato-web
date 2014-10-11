package com.webward.web.rest.dto;

/**
 * Created by dustinosthzn on 2014/09/25.
 */
public class ComparisonDefinitionDTO {

    private Long id;
    public String name;
    public ComparisonDefinitionTypeDTO type;

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

    public ComparisonDefinitionTypeDTO getType() {
        return type;
    }

    public void setType(ComparisonDefinitionTypeDTO type) {
        this.type = type;
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
        return "ComparisonDefinitionDTO{" +
                "id=" + id +
                "name=" + name +
                ", type='" + type + '\'' +
                '}';
    }
}
