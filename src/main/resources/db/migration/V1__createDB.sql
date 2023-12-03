CREATE DATABASE IF NOT EXISTS modulo_lyd;

USE modulo_lyd;

CREATE TABLE conductores
(
    `Id_conductor`            INT                                                 NOT NULL AUTO_INCREMENT,
    `Nombre_conductor`        VARCHAR(100)                                        NOT NULL,
    `Licencia_vigente`        VARCHAR(100)                                        NOT NULL,
    `TWIC_card`               VARCHAR(100)                                        NOT NULL,
    `Experiencia`             INT                                                 NOT NULL,
    `Permiso_carga_peligrosa` ENUM ('Si', 'No')                                   NOT NULL,
    `Tipo_camion`             VARCHAR(100)                                        NOT NULL,
    `Numero_chasis`           VARCHAR(100)                                        NOT NULL,
    `Estado`                  ENUM ('Disponible', 'No disponible', 'En tránsito') NOT NULL,
    PRIMARY KEY (`Id_conductor`),
    UNIQUE INDEX `Licencia_vigente` (`Licencia_vigente` ASC) VISIBLE,
    UNIQUE INDEX `TWIC_card` (`TWIC_card` ASC) VISIBLE,
    UNIQUE INDEX `Numero_chasis` (`Numero_chasis` ASC) VISIBLE
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE contenedores
(
    `Codigo_contenedor`     VARCHAR(50)                          NOT NULL,
    `Tipo`                  VARCHAR(30)                          NOT NULL,
    `Origen`                VARCHAR(30)                          NOT NULL,
    `Estado_disponibilidad` ENUM ('Disponible', 'No disponible') NOT NULL,
    PRIMARY KEY (`Codigo_contenedor`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE inventario_contenedores
(
    `Id_registro_inventario` INT                       NOT NULL AUTO_INCREMENT,
    `Codigo_contenedor`      VARCHAR(50)               NOT NULL,
    `Localizacion`           VARCHAR(45)               NOT NULL,
    `Estado_de_carga`        ENUM ('Vacío', 'Cargado') NOT NULL,
    `Entrada_a_lugar`        DATETIME                  NOT NULL,
    `Salida_de_lugar`        DATETIME                  NOT NULL,
    PRIMARY KEY (`Id_registro_inventario`),
    INDEX `fk_inventario_contenedores_contenedores1_idx` (`Codigo_contenedor` ASC) VISIBLE,
    CONSTRAINT `fk_inventario_contenedores_contenedores1`
        FOREIGN KEY (`Codigo_contenedor`)
            REFERENCES contenedores (`Codigo_contenedor`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE movimientos
(
    `Id_movimiento`   INT          NOT NULL AUTO_INCREMENT,
    `Tipo_movimiento` VARCHAR(50)  NOT NULL,
    `Descripcion`     VARCHAR(200) NOT NULL,
    PRIMARY KEY (`Id_movimiento`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE despacho
(
    `Id_despacho`            INT                                    NOT NULL AUTO_INCREMENT,
    `Id_movimiento`          INT                                    NOT NULL,
    `Id_conductor`           INT                                    NOT NULL,
    `Id_registro_inventario` INT                                    NOT NULL,
    `Bill_of_lading`         VARCHAR(45)                            NOT NULL,
    `Numero_booking`         VARCHAR(45)                            NOT NULL,
    `Ubicacion_destino`      VARCHAR(45)                            NOT NULL,
    `Appointment`            DATETIME                               NOT NULL,
    `Tipo_de_carga`          ENUM ('Pesada', 'Ligera', 'Peligrosa') NOT NULL,
    `Fecha_despacho`         DATETIME                               NOT NULL,
    PRIMARY KEY (`Id_despacho`),
    UNIQUE INDEX `Bill_of_lading_UNIQUE` (`Bill_of_lading` ASC) VISIBLE,
    UNIQUE INDEX `Numero_booking_UNIQUE` (`Numero_booking` ASC) VISIBLE,
    INDEX `fk_despacho_movimientos_idx` (`Id_movimiento` ASC) VISIBLE,
    INDEX `fk_despacho_conductores1_idx` (`Id_conductor` ASC) VISIBLE,
    INDEX `fk_despacho_inventario_contenedores1_idx` (`Id_registro_inventario` ASC) VISIBLE,
    CONSTRAINT `fk_despacho_conductores1`
        FOREIGN KEY (`Id_conductor`)
            REFERENCES conductores (`Id_conductor`),
    CONSTRAINT `fk_despacho_inventario_contenedores1`
        FOREIGN KEY (`Id_registro_inventario`)
            REFERENCES inventario_contenedores (`Id_registro_inventario`),
    CONSTRAINT `fk_despacho_movimientos`
        FOREIGN KEY (`Id_movimiento`)
            REFERENCES movimientos (`Id_movimiento`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE incidencias
(
    `Id_incidencia`     INT          NOT NULL AUTO_INCREMENT,
    `Responsabilidad`   VARCHAR(100) NOT NULL,
    `Nombre_incidencia` VARCHAR(100) NOT NULL,
    `Descripcion`       VARCHAR(200) NOT NULL,
    PRIMARY KEY (`Id_incidencia`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE registro_movidas
(
    `Id_movida`     INT                                                                                             NOT NULL AUTO_INCREMENT,
    `Id_despacho`   INT                                                                                             NOT NULL,
    `Fecha_salida`  DATETIME                                                                                        NOT NULL,
    `Estado_movida` ENUM ('Dispatched', 'Done', 'Issue/Inconvenience', 'Not dispatched yet', 'Driver pre-assigned') NOT NULL,
    PRIMARY KEY (`Id_movida`),
    INDEX `fk_registro_movidas_despacho1_idx` (`Id_despacho` ASC) VISIBLE,
    CONSTRAINT `fk_registro_movidas_despacho1`
        FOREIGN KEY (`Id_despacho`)
            REFERENCES despacho (`Id_despacho`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE registro_incidencias
(
    `Id_registro_incidencia` INT                                                NOT NULL AUTO_INCREMENT,
    `Id_incidencia`          INT                                                NOT NULL,
    `Id_movida`              INT                                                NOT NULL,
    `Fecha_incidencia`       DATETIME                                           NOT NULL,
    `Descripcion`            VARCHAR(300)                                       NOT NULL,
    `Responsable`            VARCHAR(100)                                       NOT NULL,
    `Estado`                 ENUM ('Sin resolución', 'En progreso', 'Resuelta') NOT NULL,
    `Coste`                  FLOAT                                              NOT NULL,
    `Acciones_correctivas`   VARCHAR(300)                                       NULL DEFAULT NULL,
    `Impacto`                ENUM ('Bajo', 'Medio', 'Alto')                     NOT NULL,
    `Justificacion`          VARCHAR(300)                                       NOT NULL,
    PRIMARY KEY (`Id_registro_incidencia`),
    INDEX `fk_registro_incidencias_incidencias1_idx` (`Id_incidencia` ASC) VISIBLE,
    INDEX `fk_registro_incidencias_registro_movidas1_idx` (`Id_movida` ASC) VISIBLE,
    CONSTRAINT `fk_registro_incidencias_incidencias1`
        FOREIGN KEY (`Id_incidencia`)
            REFERENCES incidencias (`Id_incidencia`),
    CONSTRAINT `fk_registro_incidencias_registro_movidas1`
        FOREIGN KEY (`Id_movida`)
            REFERENCES registro_movidas (`Id_movida`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE usuarios
(
    `Id_usuario`     INT               NOT NULL AUTO_INCREMENT,
    `Nombre`         VARCHAR(100)      NOT NULL,
    `Correo_empresa` VARCHAR(100)      NOT NULL,
    `Rol`            VARCHAR(50)       NOT NULL,
    `esActivo`       ENUM ('Si', 'No') NOT NULL,
    PRIMARY KEY (`Id_usuario`),
    UNIQUE INDEX `Correo_empresa` (`Correo_empresa` ASC) VISIBLE
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE operaciones_por_usuarios
(
    `Id_registro`            INT          NOT NULL AUTO_INCREMENT,
    `Id_usuario`             INT          NOT NULL,
    `Fecha_registro`         DATETIME     NOT NULL,
    `Tipo_operacion`         VARCHAR(250) NOT NULL,
    `Id_registro_inventario` INT          NULL DEFAULT NULL,
    `Id_despacho`            INT          NULL DEFAULT NULL,
    `Id_registro_incidencia` INT          NULL DEFAULT NULL,
    `Id_movida`              INT          NULL DEFAULT NULL,
    PRIMARY KEY (`Id_registro`),
    INDEX `fk_Operaciones_por_usuarios_usuarios1_idx` (`Id_usuario` ASC) VISIBLE,
    INDEX `fk_Operaciones_por_usuarios_inventario_contenedores1_idx` (`Id_registro_inventario` ASC) VISIBLE,
    INDEX `fk_Operaciones_por_usuarios_despacho1_idx` (`Id_despacho` ASC) VISIBLE,
    INDEX `fk_Operaciones_por_usuarios_registro_incidencias1_idx` (`Id_registro_incidencia` ASC) VISIBLE,
    INDEX `fk_Operaciones_por_usuarios_registro_movidas1_idx` (`Id_movida` ASC) VISIBLE,
    CONSTRAINT `fk_Operaciones_por_usuarios_despacho1`
        FOREIGN KEY (`Id_despacho`)
            REFERENCES despacho (`Id_despacho`),
    CONSTRAINT `fk_Operaciones_por_usuarios_inventario_contenedores1`
        FOREIGN KEY (`Id_registro_inventario`)
            REFERENCES inventario_contenedores (`Id_registro_inventario`),
    CONSTRAINT `fk_Operaciones_por_usuarios_registro_incidencias1`
        FOREIGN KEY (`Id_registro_incidencia`)
            REFERENCES registro_incidencias (`Id_registro_incidencia`),
    CONSTRAINT `fk_Operaciones_por_usuarios_registro_movidas1`
        FOREIGN KEY (`Id_movida`)
            REFERENCES registro_movidas (`Id_movida`),
    CONSTRAINT `fk_Operaciones_por_usuarios_usuarios1`
        FOREIGN KEY (`Id_usuario`)
            REFERENCES usuarios (`Id_usuario`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;