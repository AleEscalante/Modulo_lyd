package com.colaborativo.modulo_lyd.model.movimiento;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "movimientos")
@Entity(name = "Movimiento")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_movimiento")
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_movimiento;

    private String tipo_movimiento;
    private String descripcion;
    private Boolean activo;

    public Movimiento(DatosRegistroMovimiento datosRegistroMovimiento){
        this.tipo_movimiento = datosRegistroMovimiento.tipo_movimiento();
        this.descripcion = datosRegistroMovimiento.descripcion();
        this.activo = true;
    }

    public void actualizarDatos(DatosActualizarMovimiento datosActualizarMovimiento) {
        if (datosActualizarMovimiento.tipo_movimiento() != null) {
            this.tipo_movimiento = datosActualizarMovimiento.tipo_movimiento();
        }
        if (datosActualizarMovimiento.descripcion() != null) {
            this.descripcion = datosActualizarMovimiento.descripcion();
        }
        if (datosActualizarMovimiento.activo() != null && datosActualizarMovimiento.activo()) {
            activarMovimiento();
        }
    }

    public void desactivarMovimiento() {
        this.activo = false;
    }

    public void activarMovimiento() { this.activo = true; }
}
