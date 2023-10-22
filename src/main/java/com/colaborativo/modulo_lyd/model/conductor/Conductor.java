package com.colaborativo.modulo_lyd.model.conductor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "conductores")
@Entity(name = "Conductor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "Id_conductor")
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


    public Conductor(DatosRegistrarConductor datosRegistrarConductor) {

        this.conductorVigente = true; // se vuelve un conductor Vigente por defecto
        this.nombre_conductor = datosRegistrarConductor.nombre_conductor();
        this.licenciaVigente = datosRegistrarConductor.licencia_vigente();
        this.TWICCard = datosRegistrarConductor.twic_card();
        this.experiencia = datosRegistrarConductor.anhos_experiencia();
        this.tipo_camion = datosRegistrarConductor.tipo_camion();
        this.numeroChasis = datosRegistrarConductor.numero_chasis();
        this.permiso_carga_peligrosa = datosRegistrarConductor.permiso_carga_peligrosa();
        this.estado = datosRegistrarConductor.estado();
    }

//prueba para q no se actualiza sin nada que este nula
    public void actualizarDatosConductor(DatosActualizarConductor datosActualizarConductor) {
        if (datosActualizarConductor.nombre_conductor() != null) {
            this.nombre_conductor = datosActualizarConductor.nombre_conductor();
        }
        if (datosActualizarConductor.licencia_vigente() != null) {
            this.licenciaVigente = datosActualizarConductor.licencia_vigente();
        }
        if (datosActualizarConductor.twic_card() != null) { // este no se si se debe de actualizar
            this.TWICCard = datosActualizarConductor.twic_card();
        }
        if (datosActualizarConductor.anhos_experiencia() != null) { // no se si pedian los años, pero eso es lo q entendi
            this.experiencia = datosActualizarConductor.anhos_experiencia();
        }
        if (datosActualizarConductor.tipo_camion() != null) {
            this.tipo_camion = datosActualizarConductor.tipo_camion();
        }
        if (datosActualizarConductor.estado() != null) {
            this.estado = datosActualizarConductor.estado();
        }
    }
    public void desactivarConductor() {
        this.conductorVigente = false;
    }

}
