package com.colaborativo.modulo_lyd.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.colaborativo.modulo_lyd.model.conductor.Conductor;

/**
 * @(#)ConductorRepository.java 1.0 02/11/2023
 *
 * Funcionalidad de la resolución automática de consultas de Spring Data JPA.
 *
 * Cambios:
 * - Agregando las funciones findBy y existBy para escoger y verificar cada registro.
 */
public interface ConductorRepository extends JpaRepository<Conductor, Integer> {
    Page<Conductor> findByConductorVigenteTrue(Pageable paginacion);

    boolean existsByLicenciaVigente(String licencia_vigente);

    boolean existsByTWICCard(String twic_card);

    boolean existsByNumeroChasis(String numero_chasis);
}