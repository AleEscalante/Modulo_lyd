package com.colaborativo.modulo_lyd.model.movimiento;

public record DatosListarMovimientos(Long idMovimiento, String tipo_movimiento, String descripcion) {

    public DatosListarMovimientos(Movimiento movimiento){
        this(movimiento.getIdMovimiento(),movimiento.getTipoMovimiento(),movimiento.getDescripcion());
    }

}
