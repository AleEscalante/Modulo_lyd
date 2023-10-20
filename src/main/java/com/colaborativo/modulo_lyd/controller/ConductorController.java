package com.colaborativo.modulo_lyd.controller;

import com.colaborativo.modulo_lyd.model.conductor.Conductor;
import com.colaborativo.modulo_lyd.model.conductor.DatosActualizarConductor;
import com.colaborativo.modulo_lyd.model.conductor.DatosListarConductor;
import com.colaborativo.modulo_lyd.model.conductor.DatosRegistrarConductor;
import com.colaborativo.modulo_lyd.repository.ConductorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/conductor")
    public class ConductorController {

        @Autowired
        private ConductorRepository conductorRepository;

        @PostMapping
        @Transactional
        public void registrarConductor(@RequestBody @Valid DatosRegistrarConductor datosRegistrarConductor) {
            conductorRepository.save(new Conductor(datosRegistrarConductor));
        }

        @GetMapping
        public Page<DatosListarConductor> datosListarConductors(@PageableDefault(size = 10) Pageable paginacion) {
            return conductorRepository.findByConductorVigenteTrue(paginacion).map(DatosListarConductor::new);
        }

        @PutMapping
        @Transactional
        public void actualizarConductor(@RequestBody @Valid DatosActualizarConductor datosActualizarConductor) {
            Conductor conductor = conductorRepository.getReferenceById(datosActualizarConductor.id());
            conductor.actualizarDatosConductor(datosActualizarConductor);
        }

        // desactivo al conductor
        @DeleteMapping("/{id}") // en esta parte no se si se elimina por el id, por el TWIC_card, el nombre u otras cosa
        public void eliminarConductor(@PathVariable Integer id) {
            Conductor conductor = conductorRepository.getReferenceById(id);
            conductor.desactivarConductor();
        }


//        @DeleteMapping("/{id}")
//        @Transactional
//        public void eliminarConductor(@PathVariable Integer id) {
//            // elimino al conductor sin dejar ningun registro de q exsistio
//            conductorRepository.deleteById(id);
//        }
    }
