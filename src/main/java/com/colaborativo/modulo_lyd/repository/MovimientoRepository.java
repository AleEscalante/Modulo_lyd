package com.colaborativo.modulo_lyd.repository;

import com.colaborativo.modulo_lyd.entities.Movimiento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    Page<Movimiento> findByActivoTrue(Pageable paginacion);
}
