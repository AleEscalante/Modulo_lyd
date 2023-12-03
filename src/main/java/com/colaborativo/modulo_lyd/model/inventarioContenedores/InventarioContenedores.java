package com.colaborativo.modulo_lyd.model.inventarioContenedores;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @(#)InventarioContenedores.java 1.0.0 26/11/2023
 *
 * Clase que representa a Inventario_contenedores en el sistema de gestión logística y distribución.
 * Registra información a un Inventario de Contenedores ademas de métodos para actualizar y desactivar.
 *
 * Cambios:
 *
 */

@Table(name = "inventario_contenedores")
@Entity(name = "InventarioContenedores")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idRegistroInventario")
public class InventarioContenedores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_registro_inventario")
    private Long idRegistroInventario;

    private String codigoContenedor;
    private String localizacion;

    @Enumerated(EnumType.STRING)
    private EstadoDeCarga estadoDeCarga;
    private LocalDateTime entradaLugar;
    private LocalDateTime salidaDeLugar;

    public InventarioContenedores(DatosRegistrarInventarioContenedores datosRegistrarInventarioContenedores) {
        this.codigoContenedor = datosRegistrarInventarioContenedores.codigo_contenedor();
        this.localizacion = datosRegistrarInventarioContenedores.localizacion();
        this.estadoDeCarga = datosRegistrarInventarioContenedores.estado_de_carga();
        this.entradaLugar = datosRegistrarInventarioContenedores.entrada_lugar();
        this.salidaDeLugar = datosRegistrarInventarioContenedores.salida_de_lugar();
    }

}
