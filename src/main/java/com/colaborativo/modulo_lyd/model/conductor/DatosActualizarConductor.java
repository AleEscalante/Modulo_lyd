package com.colaborativo.modulo_lyd.model.conductor;

import jakarta.validation.constraints.NotNull;

/**
 * @(#)DatosActualizarConductor.java 1.0 02/11/2023
 *
 * Gestiona la actualización de datos de un conductor en el sistema.
 *
 * Cambios:
 * - Corregido el formato del nombre de las propiedades a camelCase.
 * - Eliminando las anotaciones para que reciva datos más dinámicos.
 */
public record DatosActualizarConductor(
        @NotNull
        Integer idConductor,
        String nombreConductor,
        String licenciaVigente,
        String twicCard,
        Long anhosExperiencia,
        String tipoCamion,
        Estado estado) {
}
