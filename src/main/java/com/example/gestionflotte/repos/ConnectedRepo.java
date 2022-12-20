package com.example.gestionflotte.repos;

import com.example.gestionflotte.models.Connected;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectedRepo extends JpaRepository<Connected, Long> {
    Connected findByToken(String token);

}
