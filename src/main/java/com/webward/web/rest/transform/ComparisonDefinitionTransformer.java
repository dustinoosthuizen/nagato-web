package com.webward.web.rest.transform;

import com.webward.domain.ComparisonDefinition;
import com.webward.domain.ComparisonDefinitionType;
import com.webward.repository.ComparisonDefinitionRepository;
import com.webward.repository.ComparisonDefinitionTypeRepository;
import com.webward.web.rest.dto.ComparisonDefinitionDTO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dustinosthzn on 2014/09/25.
 */
@Service
public class ComparisonDefinitionTransformer {

    @Inject
    private ComparisonDefinitionRepository comparisondefinitionRepository;
    @Inject
    private ComparisonDefinitionTypeRepository comparisondefinitiontypeRepository;
    @Inject
    private ComparisonDefinitionTypeTransformer comparisonDefinitionTypeTransformer;

    public ComparisonDefinition fromDTO(ComparisonDefinitionDTO comparisonDefinitionDTO)
    {
        ComparisonDefinition comparisonDefinition = new ComparisonDefinition();
        comparisonDefinition.setId(comparisonDefinitionDTO.getId());
        comparisonDefinition.setName(comparisonDefinitionDTO.getName());
        ComparisonDefinitionType comparisonDefinitionType = comparisondefinitiontypeRepository.findByName(comparisonDefinitionDTO.getType().getName());
        if(comparisonDefinitionType==null)
        {
             comparisonDefinitionType = comparisonDefinitionTypeTransformer.fromDTO(comparisonDefinitionDTO.getType());
             comparisondefinitiontypeRepository.save(comparisonDefinitionType);
        }
        comparisonDefinition.setComparisonDefintionType(comparisonDefinitionType);
        return comparisonDefinition;
    }

    public ComparisonDefinitionDTO toDTO(ComparisonDefinition comparisonDefinition)
    {
        ComparisonDefinitionDTO comparisonDefinitionDTO = new ComparisonDefinitionDTO();
        comparisonDefinitionDTO.setId(comparisonDefinition.getId());
        comparisonDefinitionDTO.setName(comparisonDefinition.getName());
        comparisonDefinitionDTO.setType(comparisonDefinitionTypeTransformer.toDTO(comparisonDefinition.getComparisonDefintionType()));
        return comparisonDefinitionDTO;
    }

    public List<ComparisonDefinition> fromDTO(List<ComparisonDefinitionDTO> comparisonDefinitionDTO)
    {
        List<ComparisonDefinition> returnList = new ArrayList();
        for(ComparisonDefinitionDTO comparisonDefinitionDto:comparisonDefinitionDTO)
        {
            returnList.add(this.fromDTO(comparisonDefinitionDto));

        }
        return returnList;
    }

    public List<ComparisonDefinitionDTO> toDTO(List<ComparisonDefinition> comparisonDefinitions)
    {
        List<ComparisonDefinitionDTO> returnList = new ArrayList();
        for(ComparisonDefinition comparisonDefinition:comparisonDefinitions)
        {
            returnList.add(this.toDTO(comparisonDefinition));

        }
        return returnList;
    }
}
