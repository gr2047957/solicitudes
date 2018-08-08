package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.SolicitudesService;
import com.mycompany.myapp.domain.Solicitudes;
import com.mycompany.myapp.repository.SolicitudesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Solicitudes.
 */
@Service
@Transactional
public class SolicitudesServiceImpl implements SolicitudesService {

    private final Logger log = LoggerFactory.getLogger(SolicitudesServiceImpl.class);

    private final SolicitudesRepository solicitudesRepository;

    public SolicitudesServiceImpl(SolicitudesRepository solicitudesRepository) {
        this.solicitudesRepository = solicitudesRepository;
    }

    /**
     * Save a solicitudes.
     *
     * @param solicitudes the entity to save
     * @return the persisted entity
     */
    @Override
    public Solicitudes save(Solicitudes solicitudes) {
        log.debug("Request to save Solicitudes : {}", solicitudes);        return solicitudesRepository.save(solicitudes);
    }

    /**
     * Get all the solicitudes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Solicitudes> findAll(Pageable pageable) {
        log.debug("Request to get all Solicitudes");
        return solicitudesRepository.findAll(pageable);
    }


    /**
     * Get one solicitudes by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Solicitudes> findOne(Long id) {
        log.debug("Request to get Solicitudes : {}", id);
        return solicitudesRepository.findById(id);
    }

    /**
     * Delete the solicitudes by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Solicitudes : {}", id);
        solicitudesRepository.deleteById(id);
    }
}
