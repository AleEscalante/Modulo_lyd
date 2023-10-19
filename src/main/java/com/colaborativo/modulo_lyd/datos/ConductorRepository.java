package com.colaborativo.modulo_lyd.datos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConductorRepository extends JpaRepository<Conductor, Integer> {
        Page<Conductor> findByConductorVigenteTrue(Pageable paginacion);
}