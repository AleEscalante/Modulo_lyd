package com.colaborativo.modulo_lyd.model.despacho;

import com.colaborativo.modulo_lyd.model.conductor.Conductor;

import java.time.LocalDateTime;

public record DatosListarDespacho(
        Long idDespacho,
        Long idMovimiento,
        Long idConductor,
        Long idRegistroInventario,
        String billOfLading,
        String numeroBooking,
        String ubicacionDestino,
        LocalDateTime Appointment,
        TipoDeCarga tipoDeCarga,
        LocalDateTime fechaDespacho
) {
    public DatosListarDespacho(Despacho despacho) {
        this(
                despacho.getIdDespacho(),
                despacho.getIdMovimiento(),
                despacho.getIdConductor(),
                despacho.getIdRegistroInventario(),
                despacho.getBillOfLading(),
                despacho.getNumeroBooking(),
                despacho.getUbicacionDestino(),
                despacho.getAppointment(),
                despacho.getTipoDeCarga(),
                despacho.getFechaDespacho()
        );
    }
}
