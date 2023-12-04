package com.colaborativo.modulo_lyd.model.despacho;

import java.sql.Date;
import java.time.LocalDateTime;

public record DatosRegistrarDespacho(
        Long idMovimiento,
        Long idConductor,
        Long idRegistroInventario,
        String billOfLading,
        String numeroBooking,
        String ubicacionDestino,
        LocalDateTime  Appointment,
        TipoDeCarga tipoDeCarga,
        LocalDateTime fechaDespacho) {
}
