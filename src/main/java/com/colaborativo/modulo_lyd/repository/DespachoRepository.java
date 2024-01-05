package com.colaborativo.modulo_lyd.repository;

import com.colaborativo.modulo_lyd.entities.Despacho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DespachoRepository extends JpaRepository<Despacho, Long> {
    boolean existsByBillOfLading(String billOfLading);

}
