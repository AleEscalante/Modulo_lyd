package com.colaborativo.modulo_lyd.controller;

import com.colaborativo.modulo_lyd.model.conductor.Conductor;
import com.colaborativo.modulo_lyd.model.conductor.DatosActualizarConductor;
import com.colaborativo.modulo_lyd.model.conductor.DatosListarConductor;
import com.colaborativo.modulo_lyd.model.conductor.DatosRegistrarConductor;
import com.colaborativo.modulo_lyd.repository.ConductorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
    @RequestMapping("/conductor")
    public class ConductorController {

        @Autowired
        private ConductorRepository conductorRepository;
        @PostMapping
        public ResponseEntity<?> registrarConductor(@RequestBody @Valid DatosRegistrarConductor datosRegistrarConductor) {

            // Realiza todas las validaciones necesarias y las pongo en una lista usando Stream
            List<String> errores = Stream.of(
                            conductorRepository.existsByLicenciaVigente(datosRegistrarConductor.licencia_vigente()) //condicion
                                    ? "Error: Ya existe un conductor con esta licencia vigente." // este se activa si la condicion es true o false
                                    : null, // y este retorna esto si es false
                            conductorRepository.existsByTWICCard(datosRegistrarConductor.twic_card())
                                    ? "Error: Ya existe un conductor con este twic_card."
                                    : null,
                            conductorRepository.existsByNumeroChasis(datosRegistrarConductor.numero_chasis())
                                    ? "Error: Ya existe un conductor con este Numero_chasis."
                                    : null)
                    .filter(error -> error != null).collect(Collectors.toList());  // filtra los elementos restantes diferentes de nulos y se lo agrega a una nueva lista

            if (!errores.isEmpty()) { // si errores no esta vacia o si hay errores en la lista
                return ResponseEntity.status(HttpStatus.CONFLICT).body(errores);
            } else {
                conductorRepository.save(new Conductor(datosRegistrarConductor));
                return ResponseEntity.ok("Registro exitoso");
            }
        }

        @GetMapping
        public Page<DatosListarConductor> datosListarConductors(@PageableDefault(size = 20) Pageable paginacion) {
            return conductorRepository.findByConductorVigenteTrue(paginacion).map(DatosListarConductor::new);
        }

        @PutMapping
//        @Transactional
        public void actualizarConductor(@RequestBody @Valid DatosActualizarConductor datosActualizarConductor) {
            Conductor conductor = conductorRepository.getReferenceById(datosActualizarConductor.id());
            conductor.actualizarDatosConductor(datosActualizarConductor);
        }

        // desactivo al conductor
        @DeleteMapping("/{id}") // en esta parte no se si se elimina por el id, por el TWIC_card, el nombre_conductor u otras cosa
        public void eliminarConductor(@PathVariable Integer id) {
            Conductor conductor = conductorRepository.getReferenceById(id);
            conductor.desactivarConductor();
        }


//        @DeleteMapping("/{id}")
//        @Transactional
//        public void eliminarConductor(@PathVariable Integer id) {
//            // elimino al conductor sin dejar ningun registro en la db
//            conductorRepository.deleteById(id);
//        }
    }
