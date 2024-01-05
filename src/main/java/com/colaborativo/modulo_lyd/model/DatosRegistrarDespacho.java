package com.colaborativo.modulo_lyd.model;

import com.colaborativo.modulo_lyd.enums.TipoDeCarga;

import java.time.LocalDateTime;

public record DatosRegistrarDespacho(
        Long idMovimiento,
//        Movimiento idMovimiento,
        Long idConductor,
        Long idRegistroInventario,
        String billOfLading,
        String numeroBooking,
        String ubicacionDestino,
        LocalDateTime  Appointment,
        TipoDeCarga tipoDeCarga,
        LocalDateTime fechaDespacho) {
}
