package com.colaborativo.modulo_lyd.entities;

import com.colaborativo.modulo_lyd.enums.Estado;
import com.colaborativo.modulo_lyd.enums.PermisoCargaPeligrosa;
import com.colaborativo.modulo_lyd.model.DatosActualizarConductor;
import com.colaborativo.modulo_lyd.model.DatosRegistrarConductor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @(#)Conductor.java 1.1.0 08/11/2023
 *
 * Clase que representa a un conductor en el sistema de gestión logística y distribución.
 * Almacena información personal, métodos para actualizar y desactivar conductores.
 *
 * Cambios:
 * Se modificó el formato de las propiedades a camelCase para que sean compatibles con Swagger, específicamente en la interface del método 'get' de swagger.
 * Se cambió de nombre y se agregó la anotacion @Enumerated(EnumType.STRING) al enum PermisoCargaPeligrosa para su compatibilidad en la db
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
    @Column(name = "Id_conductor")
    private Long idConductor;

    private String nombreConductor;
    private String licenciaVigente;
    private String twicCard;
    private Integer experiencia;

    @Enumerated(EnumType.STRING)
    private PermisoCargaPeligrosa permisoCargaPeligrosa;
    private String tipoCamion;
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
        this.nombreConductor = datosRegistrarConductor.nombreConductor();
        this.licenciaVigente = datosRegistrarConductor.licenciaVigente();
        this.twicCard = datosRegistrarConductor.twicCard();
        this.experiencia = datosRegistrarConductor.experiencia();
        this.tipoCamion = datosRegistrarConductor.tipoCamion();
        this.numeroChasis = datosRegistrarConductor.numeroChasis();
        this.permisoCargaPeligrosa = datosRegistrarConductor.permisoCargaPeligrosa();
        this.estado = datosRegistrarConductor.estado();
    }


    /**
     * Actualiza los datos del conductor con información dinámica, solo si los valores no son nulos.
     *
     * @param datosActualizarConductor Datos flexibles para la actualización del conductor.
     */
    public void actualizarDatosConductor(DatosActualizarConductor datosActualizarConductor) {

        if (datosActualizarConductor.nombreConductor() != null) {
            this.nombreConductor = datosActualizarConductor.nombreConductor();
        }
        if (datosActualizarConductor.licenciaVigente() != null) {
            this.licenciaVigente = datosActualizarConductor.licenciaVigente();
        }
        if (datosActualizarConductor.twicCard() != null) {
            this.twicCard = datosActualizarConductor.twicCard();
        }
        if (datosActualizarConductor.experiencia() != null) {
            this.experiencia = datosActualizarConductor.experiencia();
        }
        if (datosActualizarConductor.tipoCamion() != null) {
            this.tipoCamion = datosActualizarConductor.tipoCamion();
        }
        if (datosActualizarConductor.permisoCargaPeligrosa() != null) {
            this.permisoCargaPeligrosa = datosActualizarConductor.permisoCargaPeligrosa();
        }
        if (datosActualizarConductor.estado() != null) {
            this.estado = datosActualizarConductor.estado();
        }
    }

    public void desactivarConductor() {
        this.conductorVigente = false;
    }

}
