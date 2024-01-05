package com.colaborativo.modulo_lyd.model;

import com.colaborativo.modulo_lyd.entities.Conductor;
import com.colaborativo.modulo_lyd.enums.Estado;
import com.colaborativo.modulo_lyd.enums.PermisoCargaPeligrosa;

/**
 * @(#)DatosListarConductor.java 1.1.0 08/11/2023
 *
 * Representa datos de un conductor utilizado para listar información.
 *
 * Cambios:
 * - se cambio el tipo de dato de ID.
 */
public record DatosListarConductor(
        Long idConductor,
        String nombreConductor,
        String licenciaVigente,
        String twicCard,
        Integer experiencia,
        PermisoCargaPeligrosa permisoCargaPeligrosa,
        String tipoCamion,
        String numeroChasis,
        Estado estado
) {
    public DatosListarConductor(Conductor conductor) {
        this(
                conductor.getIdConductor(),
                conductor.getNombreConductor(),
                conductor.getLicenciaVigente(),
                conductor.getTwicCard(),
                conductor.getExperiencia(),
                conductor.getPermisoCargaPeligrosa(),
                conductor.getTipoCamion(),
                conductor.getNumeroChasis(),
                conductor.getEstado()
        );
    }
}
