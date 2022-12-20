package com.example.gestionflotte.repos;

import com.example.gestionflotte.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    List<User> findByLogin (String login);
}
