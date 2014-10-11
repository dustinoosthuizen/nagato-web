package com.webward.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.webward.domain.ComparisonDatasource;
import com.webward.repository.DatasourceRepository;
import com.webward.web.rest.dto.DatasourceDTO;
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
 * REST controller for managing Datasource.
 */
@RestController
@RequestMapping("/app")
public class DatasourceResource {

    private final Logger log = LoggerFactory.getLogger(DatasourceResource.class);

    @Inject
    private DatasourceRepository datasourceRepository;

    @Inject
    private DatasourceTransformer datasourceTransformer;

    /**
     * POST  /rest/datasources -> Create a new datasource.
     */
    @RequestMapping(value = "/rest/datasource/create",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody DatasourceDTO datasourceDTO) {
        ComparisonDatasource datasource = datasourceTransformer.fromDTO(datasourceDTO);
        log.debug("REST request to save Datasource : {}", datasource);
        datasourceRepository.save(datasource);
    }

    /**
     * GET  /rest/datasources -> get all the datasources.
     */
    @RequestMapping(value = "/rest/datasource/findAll",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<DatasourceDTO> getAll() {
        log.debug("REST request to get all Datasources");
        List<DatasourceDTO> datasourceDTOs = datasourceTransformer.toDTO(datasourceRepository.findAll());
        return datasourceDTOs;
    }

    /**
     * GET  /rest/datasources/:id -> get the "id" datasource.
     */
    @RequestMapping(value = "/rest/datasource/findById/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DatasourceDTO> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Datasource : {}", id);
        ComparisonDatasource datasource = datasourceRepository.findOne(id);
        DatasourceDTO datasourceDTO = datasourceTransformer.toDTO(datasource);
        if (datasource == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(datasourceDTO, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/datasources/:id -> delete the "id" datasource.
     */
    @RequestMapping(value = "/rest/datasource/deleteById/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void deleteById(@PathVariable Long id) {
        log.debug("REST request to delete Datasource : {}", id);
        datasourceRepository.delete(id);
    }
}
