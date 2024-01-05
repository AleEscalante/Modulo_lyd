package com.colaborativo.modulo_lyd.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.colaborativo.modulo_lyd.entities.Conductor;

/**
 * @(#)ConductorRepository.java 1.1.0 08/11/2023
 *
 * Funcionalidad de la resolución automática de consultas de Spring Data JPA.
 *
 * Cambios:
 * Se ajustó la función 'existsByTwicCard' para que funcione correctamente después de cambiar el nombre en la clase 'Conductor'.
 */
public interface ConductorRepository extends JpaRepository<Conductor, Long> {
    Page<Conductor> findByConductorVigenteTrue(Pageable paginacion);

    boolean existsByLicenciaVigente(String licenciaVigente);

    boolean existsByTwicCard(String twicCard);

    boolean existsByNumeroChasis(String numeroChasis);
}