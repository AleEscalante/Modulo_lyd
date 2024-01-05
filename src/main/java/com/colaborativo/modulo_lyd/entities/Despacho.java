package com.colaborativo.modulo_lyd.entities;

import com.colaborativo.modulo_lyd.model.DatosRegistrarDespacho;
import com.colaborativo.modulo_lyd.enums.TipoDeCarga;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * {@code @(#)Despacho.java} 1.0.0 26/11/2023
 *
 * Clase que representa a Despacho en el sistema de gestión logística y distribución.
 * Registra información a Despacho.
 *
 * Cambios:
 *
 */
@Table(name = "despacho")
@Entity(name = "Despacho")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idDespacho")
public class Despacho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_despacho")
    private Long idDespacho;
//
//    @ManyToOne
//    @JoinColumn(name = "Id_movimiento")
//    private Movimiento idMovimiento;
    @Column(name = "Id_movimiento")
    private Long idMovimiento;

    @Column(name = "Id_conductor")
    private Long idConductor;

    @Column(name = "Id_registro_inventario")
    private Long idRegistroInventario;

    private String billOfLading;
    private String numeroBooking;
    private String ubicacionDestino;
    private LocalDateTime  Appointment;

    @Enumerated(EnumType.STRING)
    private TipoDeCarga tipoDeCarga;
    private LocalDateTime fechaDespacho;
    public Despacho(DatosRegistrarDespacho datosRegistrarDespacho) {
        this.idMovimiento = datosRegistrarDespacho.idMovimiento();
        this.idConductor = datosRegistrarDespacho.idConductor();
        this.idRegistroInventario = datosRegistrarDespacho.idRegistroInventario();
        this.billOfLading = datosRegistrarDespacho.billOfLading();
        this.numeroBooking = datosRegistrarDespacho.numeroBooking();
        this.ubicacionDestino = datosRegistrarDespacho.ubicacionDestino();
        this.Appointment = datosRegistrarDespacho.Appointment();
        this.tipoDeCarga = datosRegistrarDespacho.tipoDeCarga();
        this.fechaDespacho = datosRegistrarDespacho.fechaDespacho();
    }

}
