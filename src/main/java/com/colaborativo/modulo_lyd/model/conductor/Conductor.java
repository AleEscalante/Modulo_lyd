package com.colaborativo.modulo_lyd.model.conductor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @(#)Conductor.java 1.0 02/11/2023
 *
 * Clase que representa a un conductor en el sistema de gestión logística y distribución.
 * Almacena información personal, métodos para actualizar y desactivar conductores.
 *
 * Cambios:
 * - Documentando todos los metodos.
 * - Mejorando el metodo actualizarDatosConductor para q reciva datos más dinámicos.
 */
@Table(name = "conductores")
@Entity(name = "Conductor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idConductor")
public class Conductor {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id_conductor;

    private String nombre_conductor;
    private String licenciaVigente;
    private String TWICCard;
    private Long experiencia;
    private Carga_peligrosa permiso_carga_peligrosa;
    private String tipo_camion;
    private String numeroChasis;

    @Enumerated(EnumType.STRING)
    private Estado estado;
    private Boolean conductorVigente;


    /**
     * Asigna valores procedentes del record DatosRegistrarConductor a un conductor
     *
     * @param datosRegistrarConductor Los datos del conductor a registrar.
     */
    public Conductor(DatosRegistrarConductor datosRegistrarConductor) {
        this.conductorVigente = true;
        this.nombre_conductor = datosRegistrarConductor.nombreConductor();
        this.licenciaVigente = datosRegistrarConductor.licenciaVigente();
        this.TWICCard = datosRegistrarConductor.twicCard();
        this.experiencia = datosRegistrarConductor.anhosExperiencia();
        this.tipo_camion = datosRegistrarConductor.tipoCamion();
        this.numeroChasis = datosRegistrarConductor.numeroChasis();
        this.permiso_carga_peligrosa = datosRegistrarConductor.permisoCargaPeligrosa();
        this.estado = datosRegistrarConductor.estado();
    }


    /**
     * Actualiza los datos del conductor con información dinámica, solo si los valores no son nulos.
     *
     * @param datosActualizarConductor Datos flexibles para la actualización del conductor.
     */
    public void actualizarDatosConductor(DatosActualizarConductor datosActualizarConductor) {

        if (datosActualizarConductor.nombreConductor() != null) {
            this.nombre_conductor = datosActualizarConductor.nombreConductor();
        }
        if (datosActualizarConductor.licenciaVigente() != null) {
            this.licenciaVigente = datosActualizarConductor.licenciaVigente();
        }
        if (datosActualizarConductor.twicCard() != null) {
            this.TWICCard = datosActualizarConductor.twicCard();
        }
        if (datosActualizarConductor.anhosExperiencia() != null) {
            this.experiencia = datosActualizarConductor.anhosExperiencia();
        }
        if (datosActualizarConductor.tipoCamion() != null) {
            this.tipo_camion = datosActualizarConductor.tipoCamion();
        }
        if (datosActualizarConductor.estado() != null) {
            this.estado = datosActualizarConductor.estado();
        }
    }

    public void desactivarConductor() {
        this.conductorVigente = false;
    }

}
