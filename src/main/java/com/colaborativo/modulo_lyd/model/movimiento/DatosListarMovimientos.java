package com.colaborativo.modulo_lyd.model.movimiento;

public record DatosListarMovimientos(Long id_movimiento, String tipo_movimiento, String descripcion) {

    public DatosListarMovimientos(Movimiento movimiento){
        this(movimiento.getId_movimiento(),movimiento.getTipo_movimiento(),movimiento.getDescripcion());
    }

}
