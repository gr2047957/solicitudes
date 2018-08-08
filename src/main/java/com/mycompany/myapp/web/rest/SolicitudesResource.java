package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Solicitudes;
import com.mycompany.myapp.service.SolicitudesService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Solicitudes.
 */
@RestController
@RequestMapping("/api")
public class SolicitudesResource {

    private final Logger log = LoggerFactory.getLogger(SolicitudesResource.class);

    private static final String ENTITY_NAME = "solicitudes";

    private final SolicitudesService solicitudesService;

    public SolicitudesResource(SolicitudesService solicitudesService) {
        this.solicitudesService = solicitudesService;
    }

    /**
     * POST  /solicitudes : Create a new solicitudes.
     *
     * @param solicitudes the solicitudes to create
     * @return the ResponseEntity with status 201 (Created) and with body the new solicitudes, or with status 400 (Bad Request) if the solicitudes has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/solicitudes")
    @Timed
    public ResponseEntity<Solicitudes> createSolicitudes(@Valid @RequestBody Solicitudes solicitudes) throws URISyntaxException {
        log.debug("REST request to save Solicitudes : {}", solicitudes);
        if (solicitudes.getId() != null) {
            throw new BadRequestAlertException("A new solicitudes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Solicitudes result = solicitudesService.save(solicitudes);
        return ResponseEntity.created(new URI("/api/solicitudes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /solicitudes : Updates an existing solicitudes.
     *
     * @param solicitudes the solicitudes to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated solicitudes,
     * or with status 400 (Bad Request) if the solicitudes is not valid,
     * or with status 500 (Internal Server Error) if the solicitudes couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/solicitudes")
    @Timed
    public ResponseEntity<Solicitudes> updateSolicitudes(@Valid @RequestBody Solicitudes solicitudes) throws URISyntaxException {
        log.debug("REST request to update Solicitudes : {}", solicitudes);
        if (solicitudes.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Solicitudes result = solicitudesService.save(solicitudes);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, solicitudes.getId().toString()))
            .body(result);
    }

    /**
     * GET  /solicitudes : get all the solicitudes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of solicitudes in body
     */
    @GetMapping("/solicitudes")
    @Timed
    public ResponseEntity<List<Solicitudes>> getAllSolicitudes(Pageable pageable) {
        log.debug("REST request to get a page of Solicitudes");
        Page<Solicitudes> page = solicitudesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/solicitudes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /solicitudes/:id : get the "id" solicitudes.
     *
     * @param id the id of the solicitudes to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the solicitudes, or with status 404 (Not Found)
     */
    @GetMapping("/solicitudes/{id}")
    @Timed
    public ResponseEntity<Solicitudes> getSolicitudes(@PathVariable Long id) {
        log.debug("REST request to get Solicitudes : {}", id);
        Optional<Solicitudes> solicitudes = solicitudesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(solicitudes);
    }

    /**
     * DELETE  /solicitudes/:id : delete the "id" solicitudes.
     *
     * @param id the id of the solicitudes to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/solicitudes/{id}")
    @Timed
    public ResponseEntity<Void> deleteSolicitudes(@PathVariable Long id) {
        log.debug("REST request to delete Solicitudes : {}", id);
        solicitudesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
