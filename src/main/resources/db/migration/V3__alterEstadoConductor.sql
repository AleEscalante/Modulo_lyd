ALTER TABLE conductores
    MODIFY  Estado ENUM ('Disponible', 'No_disponible', 'En_transito') NOT NULL;