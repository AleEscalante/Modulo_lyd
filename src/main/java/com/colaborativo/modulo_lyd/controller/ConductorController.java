package com.colaborativo.modulo_lyd.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.colaborativo.modulo_lyd.model.conductor.Conductor;
import com.colaborativo.modulo_lyd.model.conductor.DatosActualizarConductor;
import com.colaborativo.modulo_lyd.model.conductor.DatosListarConductor;
import com.colaborativo.modulo_lyd.model.conductor.DatosRegistrarConductor;
import com.colaborativo.modulo_lyd.repository.ConductorRepository;

/**
 * @(#)ConductorController.java 1.0 02/11/2023
 *
 * Controlador para la gestión de pedidos en el módulo de logística y distribución.
 *
 * Cambios:
 * - Documentando todos los metodos.
 * - Agregando los mensajes de retorno en errores para el metodo post y put.
 */

@RestController
@RequestMapping("/conductor")
public class ConductorController {

    @Autowired
    private ConductorRepository conductorRepository;

    /**
     * Registra un nuevo conductor en la base de datos a partir de los datos proporcionados por el record DatosRegistrarConductor.
     * Este método internamente verifica si existen registros duplicados con sus respectivos metodos en ConductorRepository
     *
     * @param datosRegistrarConductor Los datos del conductor a registrar.
     * @return ResponseEntity que indica si el registro fue exitoso o devuelve una lista de errores de validación.
     */
    @PostMapping
    public ResponseEntity<?> registrarConductor(@RequestBody @Valid DatosRegistrarConductor datosRegistrarConductor) {
        // XXX: Revisar este lista, ayudenme haciendo esto en un metodo para poder validar el metodo post y put.
        List<String> listErrorKeys = Stream.of(
                        conductorRepository.existsByLicenciaVigente(datosRegistrarConductor.licenciaVigente()) //condicion
                                ? "Error: Ya existe un conductor con esta licencia vigente." // este se activa si la condicion es true
                                : null, // y este retorna esto si es false
                        conductorRepository.existsByTWICCard(datosRegistrarConductor.twicCard())
                                ? "Error: Ya existe un conductor con este twicCard."
                                : null,
                        conductorRepository.existsByNumeroChasis(datosRegistrarConductor.numeroChasis())
                                ? "Error: Ya existe un conductor con este Numero_chasis."
                                : null)
                .filter(lista -> lista != null).collect(Collectors.toList());  // filtra los elementos restantes diferentes de nulos y los convierte en una lista

        if (!listErrorKeys.isEmpty()) { // si listErrorKeys no esta vacia
            return ResponseEntity.status(HttpStatus.CONFLICT).body(listErrorKeys);
        } else {
            conductorRepository.save(new Conductor(datosRegistrarConductor));
            return ResponseEntity.ok("Registro exitoso");
        }
    }


    /**
     * Obtiene una página de datos de conductores vigentes mediante el metodo findByConductorVigenteTrue de ConductorRepository.
     * ( Este metodo no se puede buscar por id)
     *
     * @param paginacion Opcion de paginación.
     * @return Una página de datos de conductores Vigentes.
     */
    @GetMapping
    public Page<DatosListarConductor> datosListarConductors(@PageableDefault(size = 10) Pageable paginacion) {
        return conductorRepository.findByConductorVigenteTrue(paginacion).map(DatosListarConductor::new);
    }

    /**
     * Actualiza los datos de un conductor en la base de datos utilizando su ID y los nuevos datos proporcionados en el record DatosActualizarConductor.
     * Este método llama internamente a actualizarDatosConductor para realizar la actualización.
     *
     * @param datosActualizarConductor Los nuevos datos del conductor.
     * @return
     */
    @PutMapping
    //@Transactional
    public ResponseEntity<?> actualizarConductor(@RequestBody @Valid DatosActualizarConductor datosActualizarConductor) {

        Conductor conductor = conductorRepository.getReferenceById(datosActualizarConductor.idConductor());

        if (conductorRepository.existsById(datosActualizarConductor.idConductor())) {

            List<String> listErrorKeys = Stream.of(
                            conductorRepository.existsByLicenciaVigente(datosActualizarConductor.licenciaVigente())
                                    ? "Error: Ya existe un conductor con esta licencia vigente."
                                    : null,
                            conductorRepository.existsByTWICCard(datosActualizarConductor.twicCard())
                                    ? "Error: Ya existe un conductor con este twicCard."
                                    : null)
                    .filter(lista -> lista != null).collect(Collectors.toList());

            if (!listErrorKeys.isEmpty()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(listErrorKeys);
            } else {
                conductor.actualizarDatosConductor(datosActualizarConductor);
                return ResponseEntity.ok("Conductor actualizado exitosamente");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conductor no encontrado");
        }
    }

    /**
     * Hace un Borrado Superficial (Soft Delete o Logical Delete).
     * Este método busca internamente el ID del conductor Vigente en ConductorRepository y lo desactiva con el método desactivarConductor de Conductor.
     *
     * @param id Identificador del conducto a buscar
     */
    @DeleteMapping("/{id}")
    public void eliminarConductor(@PathVariable Integer id) {
        Conductor conductor = conductorRepository.getReferenceById(id);
        conductor.desactivarConductor();
    }


//        @DeleteMapping("/{idConductor}")
//        @Transactional
//        public void eliminarConductor(@PathVariable Integer idConductor) {
//            // elimino al conductor sin dejar ningun registro en la db
//            conductorRepository.deleteById(idConductor);
//        }
}
