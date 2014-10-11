package com.webward.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.webward.domain.ComparisonDefinition;
import com.webward.domain.ComparisonDefinitionDatabase;
import com.webward.repository.ComparisonDefinitionDatabaseRepository;
import com.webward.repository.ComparisonDefinitionRepository;
import com.webward.web.rest.dto.BooleanDTO;
import com.webward.web.rest.dto.ComparisonDefinitionDTO;
import com.webward.web.rest.transform.ComparisonDefinitionTransformer;
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
 * REST controller for managing ComparisonDefinition.
 */
@RestController
@RequestMapping("/app")
public class ComparisonDefinitionResource {

    private final Logger log = LoggerFactory.getLogger(ComparisonDefinitionResource.class);

    @Inject
    private ComparisonDefinitionRepository comparisondefinitionRepository;
    @Inject
    private ComparisonDefinitionDatabaseRepository comparisondefinitionDatabaseRepository;
    @Inject
    private ComparisonDefinitionTransformer comparisonDefinitionTransformer;

    /**
     * POST  /rest/comparisondefinitions -> Create a new comparisondefinition.
     */
    @RequestMapping(value = "/rest/comparisonDefinition/create",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody ComparisonDefinitionDTO comparisonDefintionDTO) {
        log.debug("REST request to save createComparisonDefintionForm : {}", comparisonDefintionDTO);
        comparisondefinitionRepository.save(comparisonDefinitionTransformer.fromDTO(comparisonDefintionDTO));
    }

    @RequestMapping(value = "/rest/comparisonDefinition/edit",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void edit(@RequestBody ComparisonDefinitionDTO comparisonDefintionDTO) {
        log.debug("REST request to save createComparisonDefintionForm : {}", comparisonDefintionDTO);
        comparisondefinitionRepository.save(comparisonDefinitionTransformer.fromDTO(comparisonDefintionDTO));
    }

    /**
     * GET  /rest/comparisondefinitions -> get all the comparisondefinitions.
     */
    @RequestMapping(value = "rest/comparisonDefinition/findAll",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<ComparisonDefinitionDTO> getAll() {
        log.debug("REST request to get all ComparisonDefinitions");
        return comparisonDefinitionTransformer.toDTO(comparisondefinitionRepository.findAll());
    }

    /**
     * GET  /rest/comparisondefinitions/:id -> get the "id" comparisondefinition.
     */
    @RequestMapping(value = "/rest/comparisonDefinition/findById/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ComparisonDefinitionDTO> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get ComparisonDefinition : {}", id);
        ComparisonDefinitionDTO comparisondefinition = comparisonDefinitionTransformer.toDTO(comparisondefinitionRepository.findOne(id));
        if (comparisondefinition == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(comparisondefinition, HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/comparisonDefinition/findByName/{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ComparisonDefinitionDTO> findByName(@PathVariable String name, HttpServletResponse response) {
        log.debug("REST request to get ComparisonDefinition : {}", name);
        ComparisonDefinition comparisonDefinition = comparisondefinitionRepository.findByName(name);
        if (comparisonDefinition == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            ComparisonDefinitionDTO comparisonDefinitionDTO = comparisonDefinitionTransformer.toDTO(comparisonDefinition);
            return new ResponseEntity<>(comparisonDefinitionDTO, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/rest/comparisonDefinition/doesNameExist/{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<BooleanDTO> doesNameExist(@PathVariable String name, HttpServletResponse response) {
        log.debug("REST request to get ComparisonDefinition : {}", name);
        ComparisonDefinition comparisonDefinition = comparisondefinitionRepository.findByName(name);
        if (comparisonDefinition == null) {
            return new ResponseEntity<>(new BooleanDTO(false), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new BooleanDTO(true), HttpStatus.OK);
        }
    }

    /**
     * DELETE  /rest/comparisondefinitions/:id -> delete the "id" comparisondefinition.
     */
    @RequestMapping(value = "/rest/comparisonDefinition/deleteById/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete ComparisonDefinition : {}", id);
        ComparisonDefinition comparisonDefinition = comparisondefinitionRepository.findOne(id);
        if(comparisonDefinition.getComparisonDefintionType().getName().equals("Database"))
        {
            ComparisonDefinitionDatabase comparisonDefinitionDatabase = comparisondefinitionDatabaseRepository.findByComparisonDefinitionId(id);
            comparisondefinitionDatabaseRepository.delete(comparisonDefinitionDatabase);
//            comparisondefinitionDatabaseRepository.deleteByComparisonDefinitionId(id);
        }
        comparisondefinitionRepository.delete(id);
    }
}
