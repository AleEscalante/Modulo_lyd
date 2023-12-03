ALTER TABLE contenedores
    MODIFY  Estado_disponibilidad ENUM ('Disponible', 'No_disponible') NOT NULL;