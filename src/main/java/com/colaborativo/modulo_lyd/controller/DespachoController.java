package com.colaborativo.modulo_lyd.controller;

import com.colaborativo.modulo_lyd.model.DatosListarDespacho;
import com.colaborativo.modulo_lyd.model.DatosRegistrarDespacho;
import com.colaborativo.modulo_lyd.entities.Despacho;
import com.colaborativo.modulo_lyd.repository.ConductorRepository;
import com.colaborativo.modulo_lyd.repository.DespachoRepository;
import com.colaborativo.modulo_lyd.repository.InventarioContenedoresRepository;
import com.colaborativo.modulo_lyd.repository.MovimientoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/despacho")
public class DespachoController {

    @Autowired
    private DespachoRepository despachoRepository;

    @Autowired
    private ConductorRepository conductorRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private InventarioContenedoresRepository inventarioContenedoresRepository;

    @PostMapping()
    public ResponseEntity<?> crearDespacho(@RequestBody @Valid DatosRegistrarDespacho datosRegistrarDespacho) {

        //logica para q valide y cree un despacho con los conductores vigentes, movimientos activos y sin duplicacion de billOfLading
        List<String> listErrorKeys = Stream.of(
                        despachoRepository.existsByBillOfLading(datosRegistrarDespacho.billOfLading())
                                ?"Error: Ya existe un despacho con este billOfLading"
                                :"No existe un despacho con este billOfLading",
                        conductorRepository.findById(datosRegistrarDespacho.idConductor())
                                .map(conductor -> conductor.getConductorVigente()
                                    ? "Conductor encontrado." //no se debe de cambiar a null porq transformará el optional y siempre me dara el error de orElse
                                    : "Error: El conductor no está vigente.")
                                .orElse("Error: Conductor no encontrado."),
                        movimientoRepository.findById(datosRegistrarDespacho.idMovimiento())
                                .map(movimiento -> movimiento.getActivo()
                                        ? "Movimiento encontrado."
                                        : "Error: El movimiento no está activo.")
                                .orElse("Error: Movimiento no encontrado."),
                        inventarioContenedoresRepository.existsById(datosRegistrarDespacho.idRegistroInventario())
                                ? "Contenedor encontrado"
                                : "Error: Registro de inventario no encontrado."
                )
                .filter(listaError -> listaError.startsWith("Error:"))  // Filtrar solo los mensajes que comienzan con "Error:"
                .collect(Collectors.toList());

        if (!listErrorKeys.isEmpty()) {
            String errorMessage = String.join("\n", listErrorKeys);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        } else {
            despachoRepository.save(new Despacho(datosRegistrarDespacho));
            return ResponseEntity.ok("Despacho creado con éxito");
        }
    }

    @GetMapping
    public ResponseEntity<Page<DatosListarDespacho>> listadoDespacho(@PageableDefault(size = 5) Pageable paginacion) {
        return ResponseEntity.ok(despachoRepository.findAll(paginacion).map(DatosListarDespacho::new));
    }

    @GetMapping("/{idDespacho}")
    public ResponseEntity<DatosListarDespacho> datosDespacho(@PathVariable Long idDespacho) {
        Despacho despacho = despachoRepository.getReferenceById(idDespacho);
        var datosDespacho = new DatosListarDespacho(despacho);
        return ResponseEntity.ok(datosDespacho);
    }

//    @GetMapping("/{idDespacho}")
//    public Despacho obtenerDespacho(@PathVariable Long idDespacho) {
//        return despachoRepository.findById(idDespacho).orElse(null);
//    }

    @DeleteMapping("/{idDespacho}")
    @Transactional
    public void eliminarConductor(@PathVariable Long idDespacho) {
        conductorRepository.deleteById(idDespacho);
    }

}

