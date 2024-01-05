package com.colaborativo.modulo_lyd.model;

import com.colaborativo.modulo_lyd.enums.EstadoDeCarga;

import java.time.LocalDateTime;

public record DatosRegistrarInventarioContenedores(
        String codigo_contenedor,
        String localizacion,
        EstadoDeCarga estado_de_carga,
        LocalDateTime entrada_lugar,
        LocalDateTime  salida_de_lugar
) {
}
