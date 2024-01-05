package com.colaborativo.modulo_lyd.model;

import com.colaborativo.modulo_lyd.enums.Estado;
import com.colaborativo.modulo_lyd.enums.PermisoCargaPeligrosa;
import jakarta.validation.constraints.NotNull;

/**
 * @(#)DatosActualizarConductor.java 1.1.0 08/11/2023
 *
 * Gestiona la actualización de datos de un conductor en el sistema.
 *
 * Cambios:
 * - Se cambió el tipo de datos de 'experiencia' para su compatibilidad a la db.
 */
public record DatosActualizarConductor(
        @NotNull
        Long idConductor,
        String nombreConductor,
        String licenciaVigente,
        String twicCard,
        Integer experiencia,
        PermisoCargaPeligrosa permisoCargaPeligrosa,
        String tipoCamion,
        Estado estado) {
}