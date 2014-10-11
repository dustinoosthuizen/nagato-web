package com.webward.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.webward.domain.ComparisonDefinitionType;
import com.webward.repository.ComparisonDefinitionTypeRepository;
import com.webward.web.rest.dto.ComparisonDefinitionTypeDTO;
import com.webward.web.rest.transform.ComparisonDefinitionTypeTransformer;
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
 * REST controller for managing ComparisonDefinitionType.
 */
@RestController
@RequestMapping("/app")
public class ComparisonDefinitionTypeResource {

    private final Logger log = LoggerFactory.getLogger(ComparisonDefinitionTypeResource.class);

    @Inject
    private ComparisonDefinitionTypeRepository comparisondefinitiontypeRepository;

    @Inject
    private ComparisonDefinitionTypeTransformer comparisonDefinitionTypeTransformer;

    /**
     * POST  /rest/comparisondefinitiontypes -> Create a new comparisondefinitiontype.
     */
    @RequestMapping(value = "/rest/comparisondefinitiontypes",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody ComparisonDefinitionTypeDTO comparisondefinitiontypeDTO) {
        log.debug("REST request to save ComparisonDefinitionType : {}", comparisondefinitiontypeDTO);
        comparisondefinitiontypeRepository.save(comparisonDefinitionTypeTransformer.fromDTO(comparisondefinitiontypeDTO));
    }

    /**
     * GET  /rest/comparisondefinitiontypes -> get all the comparisondefinitiontypes.
     */
    @RequestMapping(value = "/rest/comparisondefinitiontypes",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<ComparisonDefinitionTypeDTO> getAll() {
        log.debug("REST request to get all ComparisonDefinitionTypes");
        return comparisonDefinitionTypeTransformer.toDTO(comparisondefinitiontypeRepository.findAll());
    }

    /**
     * GET  /rest/comparisondefinitiontypes/:id -> get the "id" comparisondefinitiontype.
     */
    @RequestMapping(value = "/rest/comparisondefinitiontypes/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ComparisonDefinitionTypeDTO> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get ComparisonDefinitionType : {}", id);
        ComparisonDefinitionTypeDTO comparisondefinitiontype = comparisonDefinitionTypeTransformer.toDTO(comparisondefinitiontypeRepository.findOne(id));

        if (comparisondefinitiontype == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(comparisondefinitiontype, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/comparisondefinitiontypes/:id -> delete the "id" comparisondefinitiontype.
     */
    @RequestMapping(value = "/rest/comparisondefinitiontypes/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete ComparisonDefinitionType : {}", id);
        comparisondefinitiontypeRepository.delete(id);
    }
}
