package com.example.gestionflotte.repos;

import com.example.gestionflotte.models.Assurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssuranceRepo extends JpaRepository<Assurance,Long> {
    List<Assurance> findByPlaneId(Long id);
    @Query(value = "SELECT a.id,a.plane_id,a.debut,a.fin FROM v_assurance a WHERE mois = ?1",nativeQuery = true)
    List<Assurance> expiredAboutXMonth(int x);
}
