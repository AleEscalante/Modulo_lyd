package com.colaborativo.modulo_lyd.entities;

import com.colaborativo.modulo_lyd.model.DatosActualizarMovimiento;
import com.colaborativo.modulo_lyd.model.DatosRegistroMovimiento;
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
@EqualsAndHashCode(of = "idMovimiento")
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_movimiento")
    private Long idMovimiento;

    private String tipoMovimiento;
    private String descripcion;
    private Boolean activo;
//
//    @OneToMany(mappedBy = "movimientos", cascade = CascadeType.ALL)
//    private List<Despacho> despachos = new ArrayList<>();

    public Movimiento(DatosRegistroMovimiento datosRegistroMovimiento){
        this.tipoMovimiento = datosRegistroMovimiento.tipo_movimiento();
        this.descripcion = datosRegistroMovimiento.descripcion();
        this.activo = true;
    }

    public void actualizarDatos(DatosActualizarMovimiento datosActualizarMovimiento) {
        if (datosActualizarMovimiento.tipo_movimiento() != null) {
            this.tipoMovimiento = datosActualizarMovimiento.tipo_movimiento();
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
