package com.webward.web.rest.transform;

import com.webward.domain.ComparisonDatasource;
import com.webward.domain.ComparisonDefinition;
import com.webward.domain.ComparisonDefinitionDatabase;
import com.webward.repository.ComparisonDefinitionDatabaseRepository;
import com.webward.repository.ComparisonDefinitionRepository;
import com.webward.repository.DatasourceRepository;
import com.webward.web.rest.dto.ComparisonDefinitionDatabaseDTO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dustinosthzn on 2014/09/25.
 */
@Service
public class ComparisonDefinitionDatabaseTransformer {

    @Inject
    private ComparisonDefinitionDatabaseRepository comparisonDefinitionDatabaseRepository;

    @Inject
    private ComparisonDefinitionRepository comparisonDefinitionRepository;

    @Inject
    private DatasourceRepository datasourceRepository;

    @Inject
    private ComparisonDefinitionTransformer comparisonDefinitionTransformer;

    @Inject
    private DatasourceTransformer datasourceTransformer;


    public ComparisonDefinitionDatabase fromDTO(ComparisonDefinitionDatabaseDTO comparisonDefinitionDatabaseDTO)
    {
        ComparisonDefinitionDatabase comparisonDefinitionDatabase = new ComparisonDefinitionDatabase();
        comparisonDefinitionDatabase.setId(comparisonDefinitionDatabaseDTO.getId());
        comparisonDefinitionDatabase.setSourceQuery(comparisonDefinitionDatabaseDTO.getSourceQuery());
        comparisonDefinitionDatabase.setTargetQuery(comparisonDefinitionDatabaseDTO.getTargetQuery());
        ComparisonDefinition comparisonDefinition = comparisonDefinitionRepository.findByName(comparisonDefinitionDatabaseDTO.getComparisonDefinition().getName());
        if(comparisonDefinition==null)
        {
            comparisonDefinition = comparisonDefinitionTransformer.fromDTO(comparisonDefinitionDatabaseDTO.getComparisonDefinition());
            comparisonDefinitionRepository.save(comparisonDefinition);
        }
        ComparisonDatasource sourceDatasource = datasourceRepository.findOne(comparisonDefinitionDatabaseDTO.getSourceDatasource().getId());
        if(sourceDatasource==null)
        {
            sourceDatasource = datasourceTransformer.fromDTO(comparisonDefinitionDatabaseDTO.getSourceDatasource());
            comparisonDefinitionRepository.save(comparisonDefinition);
        }
        comparisonDefinitionDatabase.setSourceDataSource(sourceDatasource);
        ComparisonDatasource targetDatasource = datasourceRepository.findOne(comparisonDefinitionDatabaseDTO.getTargetDatasource().getId());
        if(targetDatasource==null)
        {
            targetDatasource = datasourceTransformer.fromDTO(comparisonDefinitionDatabaseDTO.getTargetDatasource());
            comparisonDefinitionRepository.save(comparisonDefinition);
        }
        comparisonDefinitionDatabase.setTargetDataSource(targetDatasource);
        comparisonDefinitionDatabase.setComparisonDefinition(comparisonDefinition);
        comparisonDefinitionDatabase.setSourceKeytomatch(comparisonDefinitionDatabaseDTO.getSourceKeytomatch());
        comparisonDefinitionDatabase.setTargetKeytomatch(comparisonDefinitionDatabaseDTO.getSourceKeytomatch());
        comparisonDefinitionDatabase.setSourceAttributetocompare(comparisonDefinitionDatabaseDTO.getSourceAttributetocompare());
        comparisonDefinitionDatabase.setTargetAttributetocompare(comparisonDefinitionDatabaseDTO.getTargetAttributetocompare());
        return comparisonDefinitionDatabase;
    }

    public ComparisonDefinitionDatabaseDTO toDTO(ComparisonDefinitionDatabase comparisonDefinitionDatabase)
    {
        ComparisonDefinitionDatabaseDTO comparisonDefinitionDatabaseDTO = new ComparisonDefinitionDatabaseDTO();
        comparisonDefinitionDatabaseDTO.setId(comparisonDefinitionDatabase.getId());
        comparisonDefinitionDatabaseDTO.setSourceQuery(comparisonDefinitionDatabase.getSourceQuery());
        comparisonDefinitionDatabaseDTO.setTargetQuery(comparisonDefinitionDatabase.getTargetQuery());
        comparisonDefinitionDatabaseDTO.setComparisonDefinition(comparisonDefinitionTransformer.toDTO(comparisonDefinitionDatabase.getComparisonDefinition()));
        comparisonDefinitionDatabaseDTO.setSourceDatasource(datasourceTransformer.toDTO(comparisonDefinitionDatabase.getSourceDataSource()));
        comparisonDefinitionDatabaseDTO.setTargetDatasource(datasourceTransformer.toDTO(comparisonDefinitionDatabase.getTargetDataSource()));
        comparisonDefinitionDatabaseDTO.setSourceKeytomatch(comparisonDefinitionDatabase.getSourceKeytomatch());
        comparisonDefinitionDatabaseDTO.setTargetKeytomatch(comparisonDefinitionDatabase.getSourceKeytomatch());
        comparisonDefinitionDatabaseDTO.setSourceAttributetocompare(comparisonDefinitionDatabase.getSourceAttributetocompare());
        comparisonDefinitionDatabaseDTO.setTargetAttributetocompare(comparisonDefinitionDatabase.getTargetAttributetocompare());
        return comparisonDefinitionDatabaseDTO;
    }

    public List<ComparisonDefinitionDatabase> fromDTO(List<ComparisonDefinitionDatabaseDTO> comparisonDefinitionDatabaseDTOs)
    {
        List<ComparisonDefinitionDatabase> returnList = new ArrayList();
        for(ComparisonDefinitionDatabaseDTO comparisonDefinitionDatabaseDTO:comparisonDefinitionDatabaseDTOs)
        {
            returnList.add(this.fromDTO(comparisonDefinitionDatabaseDTO));

        }
        return returnList;
    }

    public List<ComparisonDefinitionDatabaseDTO> toDTO(List<ComparisonDefinitionDatabase> comparisonDefinitionDatabases)
    {
        List<ComparisonDefinitionDatabaseDTO> returnList = new ArrayList();
        for(ComparisonDefinitionDatabase comparisonDefinitionDatabase:comparisonDefinitionDatabases)
        {
            returnList.add(this.toDTO(comparisonDefinitionDatabase));

        }
        return returnList;
    }
}
