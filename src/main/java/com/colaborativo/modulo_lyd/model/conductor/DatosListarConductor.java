package com.colaborativo.modulo_lyd.model.conductor;

/**
 * @(#)DatosListarConductor.java 1.1.0 08/11/2023
 *
 * Representa datos de un conductor utilizado para listar información.
 *
 * Cambios:
 * - Se cambió el tipo de datos de 'experiencia' para su compatibilidad en la db.
 */
public record DatosListarConductor(
        Integer idConductor,
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
