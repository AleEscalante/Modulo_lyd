package com.colaborativo.modulo_lyd.controller;

import com.colaborativo.modulo_lyd.entities.Movimiento;
import com.colaborativo.modulo_lyd.model.DatosActualizarMovimiento;
import com.colaborativo.modulo_lyd.model.DatosListarMovimientos;
import com.colaborativo.modulo_lyd.model.DatosRegistroMovimiento;
import com.colaborativo.modulo_lyd.model.DatosRespuestaMovimientos;
import com.colaborativo.modulo_lyd.repository.MovimientoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @PostMapping
    public ResponseEntity<?> registrarMovimiento(@RequestBody @Valid DatosRegistroMovimiento datosRegistroMovimiento,
                                                 UriComponentsBuilder uriComponentsBuilder) {
        Movimiento movimiento = movimientoRepository.save(new Movimiento(datosRegistroMovimiento));
        DatosRespuestaMovimientos datosRespuestaMovimientos = new DatosRespuestaMovimientos(movimiento);

        URI url = uriComponentsBuilder.path("/movimientos/{idMovimiento}").buildAndExpand(movimiento.getIdMovimiento()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaMovimientos);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListarMovimientos>> listadoMovimientos(@PageableDefault(size = 5) Pageable paginacion) {
        return ResponseEntity.ok(movimientoRepository.findByActivoTrue(paginacion).map(DatosListarMovimientos::new));
    }

    @GetMapping("/{idMovimiento}")
    public ResponseEntity<DatosRespuestaMovimientos> retornarDatosMovimiento(@PathVariable Long idMovimiento) {
        Movimiento movimiento = movimientoRepository.getReferenceById(idMovimiento);
        var datosMovimiento = new DatosRespuestaMovimientos(movimiento);
        return ResponseEntity.ok(datosMovimiento);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaMovimientos> actualizarMovimiento(@RequestBody @Valid DatosActualizarMovimiento datosActualizarMovimiento) {
        Movimiento movimiento = movimientoRepository.getReferenceById(datosActualizarMovimiento.idMovimiento());
        movimiento.actualizarDatos(datosActualizarMovimiento);

        return ResponseEntity.ok(new DatosRespuestaMovimientos(movimiento));
    }

    @DeleteMapping("/{idMovimiento}")
    @Transactional
    public ResponseEntity<Movimiento> eliminarMovimiento(@PathVariable Long idMovimiento) {
        Movimiento movimiento = movimientoRepository.getReferenceById(idMovimiento);
        movimiento.desactivarMovimiento();

        return ResponseEntity.noContent().build();
    }

}