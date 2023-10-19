package com.colaborativo.modulo_lyd.datos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarConductor(@NotNull Integer id,@NotBlank String nombre, @NotBlank String licencia_vigente, @NotBlank String twic_card, @NotNull Long anhos_experiencia, @NotBlank String tipo_camion, @NotNull  Estado estado) {
}
