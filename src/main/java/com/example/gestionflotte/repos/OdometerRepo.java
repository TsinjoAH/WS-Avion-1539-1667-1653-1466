package com.example.gestionflotte.repos;

import com.example.gestionflotte.models.Odometer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OdometerRepo extends JpaRepository<Odometer, Long> {

    Odometer findFirstByPlaneIdOrderByEndDesc (Long id);
    List<Odometer> findByPlaneId (Long id);

    Long deleteByPlaneId (Long id);

}
