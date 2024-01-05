package com.colaborativo.modulo_lyd.model;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarMovimiento(
        @NotNull Long idMovimiento,
        String tipo_movimiento,
        String descripcion,
        Boolean activo) {

}
