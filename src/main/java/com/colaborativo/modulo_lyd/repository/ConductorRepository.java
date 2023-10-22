package com.colaborativo.modulo_lyd.repository;

import com.colaborativo.modulo_lyd.model.conductor.Conductor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConductorRepository extends JpaRepository<Conductor, Integer> {
        Page<Conductor> findByConductorVigenteTrue(Pageable paginacion);
        boolean existsByLicenciaVigente(String licencia_vigente);
        boolean existsByTWICCard(String twic_card);
        boolean existsByNumeroChasis(String numero_chasis);
}