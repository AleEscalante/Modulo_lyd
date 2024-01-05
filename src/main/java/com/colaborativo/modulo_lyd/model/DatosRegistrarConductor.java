package com.colaborativo.modulo_lyd.model;

import com.colaborativo.modulo_lyd.enums.Estado;
import com.colaborativo.modulo_lyd.enums.PermisoCargaPeligrosa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @(#)DatosRegistrarConductor.java 1.1.0 08/11/2023
 * <p>
 * Gestiona los registros de datos de un conductor en el sistema.
 * <p>
 * Cambios:
 * <p>
 * - Se cambió el tipo de datos de 'experiencia' para su compatibilidad en la db.
 */
public record DatosRegistrarConductor(
        @NotBlank
        String nombreConductor,
        @NotBlank
        String licenciaVigente,
        @NotBlank
        String twicCard,
        Integer experiencia,
        @NotNull
        PermisoCargaPeligrosa permisoCargaPeligrosa,
        @NotBlank
        String tipoCamion,
        @NotBlank
        String numeroChasis,
        @NotNull
        Estado estado) {
}
