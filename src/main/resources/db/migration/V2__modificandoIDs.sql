-- Eliminar restricciones de clave foránea
ALTER TABLE operaciones_por_usuarios
    DROP FOREIGN KEY fk_Operaciones_por_usuarios_despacho1,
    DROP FOREIGN KEY fk_Operaciones_por_usuarios_inventario_contenedores1,
    DROP FOREIGN KEY fk_Operaciones_por_usuarios_registro_incidencias1,
    DROP FOREIGN KEY fk_Operaciones_por_usuarios_registro_movidas1,
    DROP FOREIGN KEY fk_Operaciones_por_usuarios_usuarios1;

ALTER TABLE registro_movidas
    DROP FOREIGN KEY fk_registro_movidas_despacho1;

ALTER TABLE despacho
    DROP FOREIGN KEY fk_despacho_conductores1,
    DROP FOREIGN KEY fk_despacho_inventario_contenedores1,
    DROP FOREIGN KEY fk_despacho_movimientos;

ALTER TABLE inventario_contenedores
    DROP FOREIGN KEY fk_inventario_contenedores_contenedores1;

ALTER TABLE registro_incidencias
    DROP FOREIGN KEY fk_registro_incidencias_incidencias1,
    DROP FOREIGN KEY fk_registro_incidencias_registro_movidas1;

-- Modificar tipos de datos a BIGINT
ALTER TABLE conductores
    MODIFY COLUMN Id_conductor BIGINT NOT NULL AUTO_INCREMENT;

-- ALTER TABLE contenedores
-- MODIFY COLUMN Codigo_contenedor VARCHAR(50);

ALTER TABLE inventario_contenedores
    MODIFY COLUMN Id_registro_inventario BIGINT NOT NULL AUTO_INCREMENT;

ALTER TABLE movimientos
    MODIFY COLUMN Id_movimiento BIGINT NOT NULL AUTO_INCREMENT;

ALTER TABLE despacho
    MODIFY COLUMN Id_despacho BIGINT NOT NULL AUTO_INCREMENT,
    MODIFY COLUMN Id_movimiento BIGINT,
    MODIFY COLUMN Id_conductor BIGINT,
    MODIFY COLUMN Id_registro_inventario BIGINT;

ALTER TABLE incidencias
    MODIFY COLUMN Id_incidencia BIGINT NOT NULL AUTO_INCREMENT;

ALTER TABLE registro_incidencias
    MODIFY COLUMN Id_registro_incidencia BIGINT NOT NULL AUTO_INCREMENT,
    MODIFY COLUMN Id_incidencia BIGINT,
    MODIFY COLUMN Id_movida BIGINT;

ALTER TABLE registro_movidas
    MODIFY COLUMN Id_movida BIGINT NOT NULL AUTO_INCREMENT,
    MODIFY COLUMN Id_despacho BIGINT;

ALTER TABLE usuarios
    MODIFY COLUMN Id_usuario BIGINT NOT NULL AUTO_INCREMENT;

ALTER TABLE operaciones_por_usuarios
    MODIFY COLUMN Id_registro BIGINT NOT NULL AUTO_INCREMENT,
    MODIFY COLUMN Id_usuario BIGINT,
    MODIFY COLUMN Id_registro_inventario BIGINT,
    MODIFY COLUMN Id_despacho BIGINT,
    MODIFY COLUMN Id_registro_incidencia BIGINT,
    MODIFY COLUMN Id_movida BIGINT;

-- Agregar nuevas restricciones de clave foránea
ALTER TABLE inventario_contenedores
    ADD CONSTRAINT fk_inventario_contenedores_contenedores1
        FOREIGN KEY (`Codigo_contenedor`)
            REFERENCES contenedores (`Codigo_contenedor`);

ALTER TABLE despacho
    ADD CONSTRAINT fk_despacho_conductores1
        FOREIGN KEY (`Id_conductor`)
            REFERENCES conductores (`Id_conductor`),

    ADD CONSTRAINT fk_despacho_inventario_contenedores1
        FOREIGN KEY (`Id_registro_inventario`)
            REFERENCES inventario_contenedores (`Id_registro_inventario`),

    ADD CONSTRAINT fk_despacho_movimientos
        FOREIGN KEY (`Id_movimiento`)
            REFERENCES movimientos (`Id_movimiento`);

ALTER TABLE registro_movidas
    ADD CONSTRAINT fk_registro_movidas_despacho1
        FOREIGN KEY (`Id_despacho`)
            REFERENCES despacho (`Id_despacho`);

ALTER TABLE registro_incidencias
    ADD CONSTRAINT fk_registro_incidencias_incidencias1
        FOREIGN KEY (`Id_incidencia`)
            REFERENCES incidencias (`Id_incidencia`),

    ADD CONSTRAINT fk_registro_incidencias_registro_movidas1
        FOREIGN KEY (`Id_movida`)
            REFERENCES registro_movidas (`Id_movida`);

ALTER TABLE operaciones_por_usuarios
    ADD CONSTRAINT fk_Operaciones_por_usuarios_despacho1
        FOREIGN KEY (`Id_despacho`)
            REFERENCES despacho (`Id_despacho`),

    ADD CONSTRAINT fk_Operaciones_por_usuarios_inventario_contenedores1
        FOREIGN KEY (`Id_registro_inventario`)
            REFERENCES inventario_contenedores (`Id_registro_inventario`),

    ADD CONSTRAINT fk_Operaciones_por_usuarios_registro_incidencias1
        FOREIGN KEY (`Id_registro_incidencia`)
            REFERENCES registro_incidencias (`Id_registro_incidencia`),

    ADD CONSTRAINT fk_Operaciones_por_usuarios_registro_movidas1
        FOREIGN KEY (`Id_movida`)
            REFERENCES registro_movidas (`Id_movida`),

    ADD CONSTRAINT fk_Operaciones_por_usuarios_usuarios1
        FOREIGN KEY (`Id_usuario`)
            REFERENCES usuarios (`Id_usuario`);