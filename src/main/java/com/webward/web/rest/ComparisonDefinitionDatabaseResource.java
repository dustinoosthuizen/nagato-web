package com.webward.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.webward.repository.ComparisonDefinitionDatabaseRepository;
import com.webward.service.DatabaseQueryService;
import com.webward.web.rest.dto.ComparisonDefinitionDatabaseDTO;
import com.webward.web.rest.dto.CompileDatabaseQueryRequestDTO;
import com.webward.web.rest.dto.CompileDatabaseQueryResultDTO;
import com.webward.web.rest.transform.ComparisonDefinitionDatabaseTransformer;
import com.webward.web.rest.transform.DatasourceTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * REST controller for managing ComparisonDefinitionDatabase.
 */
@RestController
@RequestMapping("/app")
public class ComparisonDefinitionDatabaseResource {

    private final Logger log = LoggerFactory.getLogger(ComparisonDefinitionDatabaseResource.class);

    @Inject
    private ComparisonDefinitionDatabaseRepository comparisondefinitiondatabaseRepository;

    @Inject
    private DatabaseQueryService databaseQueryService;

    @Inject
    private ComparisonDefinitionDatabaseTransformer comparisonDefinitionDatabaseTransformer;

    @Inject
    private DatasourceTransformer datasourceTransformer;

    /**
     * POST  /rest/comparisondefinitiondatabases -> Create a new comparisondefinitiondatabase.
     */
    @RequestMapping(value = "/rest/comparisonDefinitionDatabase/create",

            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody ComparisonDefinitionDatabaseDTO comparisondefinitiondatabaseDTO) {
        log.debug("REST request to save ComparisonDefinitionDatabase : {}", comparisondefinitiondatabaseDTO);
        comparisondefinitiondatabaseRepository.save(comparisonDefinitionDatabaseTransformer.fromDTO(comparisondefinitiondatabaseDTO));
    }

    /**
     * POST  /rest/comparisondefinitiondatabases -> Create a new comparisondefinitiondatabase.
     */
    @RequestMapping(value = "/rest/comparisonDefinitionDatabase/edit",

            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void edit(@RequestBody ComparisonDefinitionDatabaseDTO comparisondefinitiondatabaseDTO) {
        log.debug("REST request to save ComparisonDefinitionDatabase : {}", comparisondefinitiondatabaseDTO);
        comparisondefinitiondatabaseRepository.save(comparisonDefinitionDatabaseTransformer.fromDTO(comparisondefinitiondatabaseDTO));
    }

    /**
     * GET  /rest/comparisondefinitiondatabases -> get all the comparisondefinitiondatabases.
     */
    @RequestMapping(value = "/rest/comparisonDefinitionDatabase/findAll",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<ComparisonDefinitionDatabaseDTO> getAll() {
        log.debug("REST request to get all ComparisonDefinitionDatabases");
        return comparisonDefinitionDatabaseTransformer.toDTO(comparisondefinitiondatabaseRepository.findAll());
    }

    /**
     * GET  /rest/comparisondefinitiondatabases/:id -> get the "id" comparisondefinitiondatabase.
     */
    @RequestMapping(value = "/rest/comparisonDefinitionDatabase/findById/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ComparisonDefinitionDatabaseDTO> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get ComparisonDefinitionDatabase : {}", id);
        ComparisonDefinitionDatabaseDTO comparisondefinitiondatabaseDTO = comparisonDefinitionDatabaseTransformer.toDTO(comparisondefinitiondatabaseRepository.findOne(id));
        if (comparisondefinitiondatabaseDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(comparisondefinitiondatabaseDTO, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/comparisondefinitiondatabases/:id -> delete the "id" comparisondefinitiondatabase.
     */
    @RequestMapping(value = "/rest/comparisonDefinitionDatabase/deleteById/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete ComparisonDefinitionDatabase : {}", id);
        comparisondefinitiondatabaseRepository.delete(id);
    }

    @RequestMapping(value = "/rest/comparisonDefinitionDatabase/findByComparisonDefinitionId/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ComparisonDefinitionDatabaseDTO> getByComparisonDefinitionId(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get ComparisonDefinitionDatabase getByDefinitionId : {}", id);
        ComparisonDefinitionDatabaseDTO comparisondefinitiondatabaseDTO = comparisonDefinitionDatabaseTransformer.toDTO(comparisondefinitiondatabaseRepository.findByComparisonDefinitionId(id));
        if (comparisondefinitiondatabaseDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(comparisondefinitiondatabaseDTO, HttpStatus.OK);
    }

    /**
     * POST  /rest/comparisondefinitiondatabases -> Create a new comparisondefinitiondatabase.
     */
    @RequestMapping(value = "/rest/comparisonDefinitionDatabase/compileDatabaseQuery",

            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CompileDatabaseQueryResultDTO> compileDatabaseQuery(@RequestBody CompileDatabaseQueryRequestDTO compileDatabaseQueryRequestDTO) {

        log.debug("REST request to save compileDatabaseQuery : {}", compileDatabaseQueryRequestDTO);
        List<String> attribute = databaseQueryService.getColumnNames(compileDatabaseQueryRequestDTO.getQuery(),datasourceTransformer.fromDTO(compileDatabaseQueryRequestDTO.getDatasource()));
        CompileDatabaseQueryResultDTO compileDatabaseQueryResultDTO = new CompileDatabaseQueryResultDTO();
        compileDatabaseQueryResultDTO.setAttributes(attribute);
        compileDatabaseQueryResultDTO.setSuccess(true);
        return new ResponseEntity<>(compileDatabaseQueryResultDTO, HttpStatus.OK);
    }

}
