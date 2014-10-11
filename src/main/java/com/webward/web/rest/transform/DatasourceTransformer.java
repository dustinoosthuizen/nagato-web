package com.webward.web.rest.transform;

import com.webward.domain.ComparisonDatasource;
import com.webward.web.rest.dto.DatasourceDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dustinosthzn on 2014/09/27.
 */
@Service
public class DatasourceTransformer {

    public ComparisonDatasource fromDTO(DatasourceDTO comparisonDefinitionTypeDTO)
    {
        ComparisonDatasource datasource = new ComparisonDatasource();
        datasource.setId(comparisonDefinitionTypeDTO.getId());
        datasource.setName(comparisonDefinitionTypeDTO.getName());
        datasource.setClassname(comparisonDefinitionTypeDTO.getClassname());
        datasource.setPassword(comparisonDefinitionTypeDTO.getPassword());
        datasource.setUrl(comparisonDefinitionTypeDTO.getUrl());
        datasource.setUsername(comparisonDefinitionTypeDTO.getUsername());
        return datasource;
    }

    public DatasourceDTO toDTO(ComparisonDatasource datasource)
    {
        DatasourceDTO datasourceDTO = new DatasourceDTO();
        datasourceDTO.setId(datasource.getId());
        datasourceDTO.setName(datasource.getName());
        datasourceDTO.setClassname(datasource.getClassname());
        datasourceDTO.setPassword(datasource.getPassword());
        datasourceDTO.setUrl(datasource.getUrl());
        datasourceDTO.setUsername(datasource.getUsername());
        return datasourceDTO;
    }

    public List<ComparisonDatasource> fromDTO(List<DatasourceDTO> datasourceDTOs)
    {
        List<ComparisonDatasource> returnList = new ArrayList();
        for(DatasourceDTO datasourceDTO:datasourceDTOs)
        {
            returnList.add(this.fromDTO(datasourceDTO));

        }
        return returnList;
    }

    public List<DatasourceDTO> toDTO(List<ComparisonDatasource> datasources)
    {
        List<DatasourceDTO> returnList = new ArrayList();
        for(ComparisonDatasource datasource:datasources)
        {
            returnList.add(this.toDTO(datasource));

        }
        return returnList;
    }

}
