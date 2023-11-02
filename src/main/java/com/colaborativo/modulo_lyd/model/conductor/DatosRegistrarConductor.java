package com.colaborativo.modulo_lyd.model.conductor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @(#)DatosRegistrarConductor.java 1.0 02/11/2023
 *
 * Gestiona los registros de datos de un conductor en el sistema.
 *
 * Cambios:
 * - Corregido el formato del nombre de las propiedades a camelCase.
 */
public record DatosRegistrarConductor(
        @NotBlank
        String nombreConductor,
        @NotBlank
        String licenciaVigente,
        @NotBlank
        String twicCard,
        Long anhosExperiencia,
        @NotNull
        Carga_peligrosa permisoCargaPeligrosa,
        @NotBlank
        String tipoCamion,
        @NotBlank
        String numeroChasis,
        @NotNull
        Estado estado) {
}
