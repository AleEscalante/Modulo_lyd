package com.colaborativo.modulo_lyd.model.movimiento;

public record DatosRespuestaMovimientos(Long id_movimiento, String tipo_movimiento, String descripcion) {
    public DatosRespuestaMovimientos(Movimiento movimiento){
        this(movimiento.getId_movimiento(), movimiento.getTipo_movimiento(), movimiento.getDescripcion());
    }
}
