package com.colaborativo.modulo_lyd.model;

import com.colaborativo.modulo_lyd.enums.EstadoDisponibilidad;

public record DatosRegistrarContenedores(
        String codigoContenedor,
        String tipo,
        String origen,
        EstadoDisponibilidad estado_disponibilidad) {
}
