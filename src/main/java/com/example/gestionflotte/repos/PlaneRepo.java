package com.example.gestionflotte.repos;

import com.example.gestionflotte.models.plane.PlaneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaneRepo extends JpaRepository<PlaneEntity, Long> {

    List<PlaneEntity> findByLicensePlate (String licensePlate);

    @Query(nativeQuery = true, value = "select * from plane join v_assurance va on plane.id = va.plane_id where mois = ?1")
    List<PlaneEntity> findExpiredAboutXMonth(int month);

}
