package com.colaborativo.modulo_lyd.repository;

import com.colaborativo.modulo_lyd.model.conductor.Conductor;
import com.colaborativo.modulo_lyd.model.contenedores.Contenedores;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContenedoresRepository extends JpaRepository<Contenedores, String> {
}
