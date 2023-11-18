package com.colaborativo.modulo_lyd.model.movimiento;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroMovimiento(

    @NotBlank
    String tipo_movimiento,
    @NotBlank
    String descripcion
){}