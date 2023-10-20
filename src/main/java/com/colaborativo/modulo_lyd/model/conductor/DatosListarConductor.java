package com.colaborativo.modulo_lyd.model.conductor;

public record DatosListarConductor(Integer Id_conductor, String nombre_conductor, String licencia_vigente, String TWIC_card, Long experiencia, Carga_peligrosa permiso_carga_peligrosa, String tipo_camion, Estado estado){
    public DatosListarConductor(Conductor conductor) {
        this(conductor.getId_conductor(), conductor.getNombre_conductor(), conductor.getLicencia_vigente(), conductor.getTWIC_card(), conductor.getExperiencia(), conductor.getPermiso_carga_peligrosa(), conductor.getTipo_camion(), conductor.getEstado());
    }
}
