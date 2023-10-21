package com.colaborativo.modulo_lyd.model.conductor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarConductor(@NotNull Integer id, @NotBlank String nombre_conductor, @NotBlank String licencia_vigente, @NotBlank String twic_card, @NotNull Long anhos_experiencia, @NotBlank String tipo_camion, @NotNull  Estado estado) {
}
