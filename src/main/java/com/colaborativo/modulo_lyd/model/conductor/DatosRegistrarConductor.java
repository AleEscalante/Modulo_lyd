package com.colaborativo.modulo_lyd.model.conductor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DatosRegistrarConductor(
    @NotBlank
    String nombre_conductor,
    @NotBlank
    String licencia_vigente,
    @NotBlank
    String twic_card,
    Long anhos_experiencia,
    @NotNull
    Carga_peligrosa permiso_carga_peligrosa,
    @NotBlank
    String tipo_camion,
    @NotNull
    String numero_chasis,
    @NotNull
    Estado estado){
}
