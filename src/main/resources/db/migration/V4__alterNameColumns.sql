ALTER TABLE conductores CHANGE TWIC_card Twic_card VARCHAR(100);

ALTER TABLE conductores CHANGE conductor_vigente Conductor_vigente TINYINT(1) NOT NULL;