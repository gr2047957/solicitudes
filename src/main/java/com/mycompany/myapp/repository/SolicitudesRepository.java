package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Solicitudes;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Solicitudes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SolicitudesRepository extends JpaRepository<Solicitudes, Long> {

}
