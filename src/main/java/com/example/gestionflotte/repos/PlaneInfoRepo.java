package com.example.gestionflotte.repos;

import com.example.gestionflotte.models.plane.PlaneInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaneInfoRepo extends JpaRepository<PlaneInfo, Long> {
}
