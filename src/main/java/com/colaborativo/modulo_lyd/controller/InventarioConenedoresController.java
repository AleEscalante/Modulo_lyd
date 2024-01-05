package com.colaborativo.modulo_lyd.controller;

import com.colaborativo.modulo_lyd.model.DatosRegistrarInventarioContenedores;
import com.colaborativo.modulo_lyd.entities.InventarioContenedores;
import com.colaborativo.modulo_lyd.repository.ContenedoresRepository;
import com.colaborativo.modulo_lyd.repository.InventarioContenedoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventarioContenedores")
public class InventarioConenedoresController {

    @Autowired
    InventarioContenedoresRepository inventarioContenedoresRepository;

    @Autowired
    ContenedoresRepository contenedoresRepository;
    @PostMapping()
    public ResponseEntity<String> crearInventarioContenedores(@RequestBody DatosRegistrarInventarioContenedores datosRegistrarInventarioContenedores) {
        if (contenedoresRepository.existsById(datosRegistrarInventarioContenedores.codigo_contenedor())) {
            inventarioContenedoresRepository.save(new InventarioContenedores(datosRegistrarInventarioContenedores));
            return ResponseEntity.ok("Inventario de contenedor creado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Codigo_contenedor no encontrado.");
        }
    }
}
