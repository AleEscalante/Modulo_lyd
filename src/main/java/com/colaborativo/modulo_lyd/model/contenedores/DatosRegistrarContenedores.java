package com.colaborativo.modulo_lyd.model.contenedores;

public record DatosRegistrarContenedores(
        String codigoContenedor,
        String tipo,
        String origen,
        EstadoDisponibilidad estado_disponibilidad) {
}
