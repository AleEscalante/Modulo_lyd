package com.colaborativo.modulo_lyd.model.contenedores;

import com.colaborativo.modulo_lyd.model.conductor.DatosActualizarConductor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
        * @(#)Contenedores.java 1.0.0 26/11/2023
        *
        * Clase que representa a Contenedores en el sistema de gestión logística y distribución.
        * Registra la  información del contenedor ademas de métodos para actualizar y desactivar.
        *
        * Cambios:
        *
 */

@Table(name = "contenedores")
@Entity(name = "Contenedores")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "codigoContenedor")
public class Contenedores {

    @Id
    @Column(name = "Codigo_contenedor")
    private String codigoContenedor;

    private String tipo;
    private String origen;

    @Enumerated(EnumType.STRING)
    private EstadoDisponibilidad estadoDisponibilidad;

    public Contenedores(DatosRegistrarContenedores datosRegistrarContenedores) {
       this.codigoContenedor = datosRegistrarContenedores.codigoContenedor();
        this.tipo = datosRegistrarContenedores.tipo();
       this.origen = datosRegistrarContenedores.origen();
       this.estadoDisponibilidad = datosRegistrarContenedores.estado_disponibilidad();
    }
}
