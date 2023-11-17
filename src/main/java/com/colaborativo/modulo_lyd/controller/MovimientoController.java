package com.colaborativo.modulo_lyd.controller;

import com.colaborativo.modulo_lyd.model.movimiento.*;
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

        URI url = uriComponentsBuilder.path("/movimientos/{id}").buildAndExpand(movimiento.getId_movimiento()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaMovimientos);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListarMovimientos>> listadoMovimientos(@PageableDefault(size = 5) Pageable paginacion) {
        return ResponseEntity.ok(movimientoRepository.findByActivoTrue(paginacion).map(DatosListarMovimientos::new));
    }

    @GetMapping("/{id_movimiento}")
    public ResponseEntity<DatosRespuestaMovimientos> retornarDatosMovimiento(@PathVariable Long id_movimiento) {
        Movimiento movimiento = movimientoRepository.getReferenceById(id_movimiento);
        var datosMovimiento = new DatosRespuestaMovimientos(movimiento);
        return ResponseEntity.ok(datosMovimiento);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaMovimientos> actualizarMovimiento(@RequestBody @Valid DatosActualizarMovimiento datosActualizarMovimiento) {
        Movimiento movimiento = movimientoRepository.getReferenceById(datosActualizarMovimiento.id_movimiento());
        movimiento.actualizarDatos(datosActualizarMovimiento);

        return ResponseEntity.ok(new DatosRespuestaMovimientos(movimiento));
    }

    @DeleteMapping("/{id_movimiento}")
    @Transactional
    public ResponseEntity<Movimiento> eliminarMovimiento(@PathVariable Long id_movimiento) {
        Movimiento movimiento = movimientoRepository.getReferenceById(id_movimiento);
        movimiento.desactivarMovimiento();

        return ResponseEntity.noContent().build();
    }

}