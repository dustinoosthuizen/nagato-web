package com.webward.web.rest.transform;

import com.webward.domain.ComparisonDefinitionType;
import com.webward.repository.ComparisonDefinitionTypeRepository;
import com.webward.web.rest.dto.ComparisonDefinitionTypeDTO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dustinosthzn on 2014/09/25.
 */
@Service
public class ComparisonDefinitionTypeTransformer {

    @Inject
    private ComparisonDefinitionTypeRepository comparisondefinitiontypeRepository;

    public ComparisonDefinitionType fromDTO(ComparisonDefinitionTypeDTO comparisonDefinitionTypeDTO)
    {
        ComparisonDefinitionType ComparisonDefinitionType = new ComparisonDefinitionType();
        ComparisonDefinitionType.setId(comparisonDefinitionTypeDTO.getId());
        ComparisonDefinitionType.setName(comparisonDefinitionTypeDTO.getName());
        return ComparisonDefinitionType;
    }

    public ComparisonDefinitionTypeDTO toDTO(ComparisonDefinitionType comparisonDefinitionType)
    {
        ComparisonDefinitionTypeDTO comparisonDefinitionTypeDTO = new ComparisonDefinitionTypeDTO();
        comparisonDefinitionTypeDTO.setId(comparisonDefinitionType.getId());
        comparisonDefinitionTypeDTO.setName(comparisonDefinitionType.getName());
        return comparisonDefinitionTypeDTO;
    }

    public List<ComparisonDefinitionType> fromDTO(List<ComparisonDefinitionTypeDTO> comparisonDefinitionTypeDTOs)
    {
        List<ComparisonDefinitionType> returnList = new ArrayList();
        for(ComparisonDefinitionTypeDTO comparisonDefinitionTypeDTO:comparisonDefinitionTypeDTOs)
        {
            returnList.add(this.fromDTO(comparisonDefinitionTypeDTO));

        }
        return returnList;
    }

    public List<ComparisonDefinitionTypeDTO> toDTO(List<ComparisonDefinitionType> ComparisonDefinitionTypes)
    {
        List<ComparisonDefinitionTypeDTO> returnList = new ArrayList();
        for(ComparisonDefinitionType ComparisonDefinitionType:ComparisonDefinitionTypes)
        {
            returnList.add(this.toDTO(ComparisonDefinitionType));

        }
        return returnList;
    }
}
