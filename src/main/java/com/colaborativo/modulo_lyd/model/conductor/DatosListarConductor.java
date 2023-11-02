package com.colaborativo.modulo_lyd.model.conductor;

/**
 * @(#)DatosListarConductor.java 1.0 02/11/2023
 *
 * Representa datos de un conductor utilizado para listar información.
 *
 * Cambios:
 * - Corregido el formato del nombre de las propiedades a camelCase para compatibilidad con Swagger.
 */
public record DatosListarConductor(
        Integer idConductor,
        String nombreConductor,
        String licenciaVigente,
        String twicCard,
        Long experiencia,
        Carga_peligrosa permisoCargaPeligrosa,
        String tipoCamion,
        String numeroChasis,
        Estado estado
) {
    public DatosListarConductor(Conductor conductor) {
        this(
                conductor.getId_conductor(),
                conductor.getNombre_conductor(),
                conductor.getLicenciaVigente(),
                conductor.getTWICCard(),
                conductor.getExperiencia(),
                conductor.getPermiso_carga_peligrosa(),
                conductor.getTipo_camion(),
                conductor.getNumeroChasis(),
                conductor.getEstado()
        );
    }
}
