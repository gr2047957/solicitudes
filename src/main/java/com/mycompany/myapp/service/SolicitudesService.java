package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Solicitudes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Solicitudes.
 */
public interface SolicitudesService {

    /**
     * Save a solicitudes.
     *
     * @param solicitudes the entity to save
     * @return the persisted entity
     */
    Solicitudes save(Solicitudes solicitudes);

    /**
     * Get all the solicitudes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Solicitudes> findAll(Pageable pageable);


    /**
     * Get the "id" solicitudes.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Solicitudes> findOne(Long id);

    /**
     * Delete the "id" solicitudes.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
