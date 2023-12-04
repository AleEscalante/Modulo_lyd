package com.colaborativo.modulo_lyd.controller;

import com.colaborativo.modulo_lyd.model.conductor.Conductor;
import com.colaborativo.modulo_lyd.model.conductor.DatosRegistrarConductor;
import com.colaborativo.modulo_lyd.model.contenedores.Contenedores;
import com.colaborativo.modulo_lyd.model.contenedores.DatosRegistrarContenedores;
import com.colaborativo.modulo_lyd.repository.ConductorRepository;
import com.colaborativo.modulo_lyd.repository.ContenedoresRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/Contenedores")
public class ContenedoresController {
    @Autowired
    private ContenedoresRepository contenedoresRepository;

    @PostMapping
    public ResponseEntity<String> registrarContenedor(@RequestBody @Valid DatosRegistrarContenedores datosRegistrarContenedores) {
            contenedoresRepository.save(new Contenedores(datosRegistrarContenedores));
            return ResponseEntity.ok("Registro de Contenedor exitoso");
    }

}
