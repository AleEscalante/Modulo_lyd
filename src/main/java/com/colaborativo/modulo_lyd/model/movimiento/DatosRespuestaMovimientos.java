package com.colaborativo.modulo_lyd.model.movimiento;

public record DatosRespuestaMovimientos(Long idMovimiento, String tipo_movimiento, String descripcion) {
    public DatosRespuestaMovimientos(Movimiento movimiento){
        this(movimiento.getIdMovimiento(), movimiento.getTipoMovimiento(), movimiento.getDescripcion());
    }
}
