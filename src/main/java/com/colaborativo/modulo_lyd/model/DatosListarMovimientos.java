package com.colaborativo.modulo_lyd.model;

import com.colaborativo.modulo_lyd.entities.Movimiento;

public record DatosListarMovimientos(Long idMovimiento, String tipo_movimiento, String descripcion) {

    public DatosListarMovimientos(Movimiento movimiento){
        this(movimiento.getIdMovimiento(),movimiento.getTipoMovimiento(),movimiento.getDescripcion());
    }

}
