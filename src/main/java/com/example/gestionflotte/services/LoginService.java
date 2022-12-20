package com.example.gestionflotte.services;

import com.example.gestionflotte.exception.CustomValidation;
import com.example.gestionflotte.models.Connected;
import com.example.gestionflotte.models.User;
import com.example.gestionflotte.repos.ConnectedRepo;
import com.example.gestionflotte.repos.UserRepo;
import com.example.gestionflotte.responses.AuthenticatedDetails;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoginService {

    protected final UserRepo userRepo;
    protected final ConnectedRepo connectedRepo;

    public LoginService(UserRepo userRepo, ConnectedRepo repo) {
        this.userRepo = userRepo;
        this.connectedRepo = repo;
    }

    public void deConnect (String token) {
        if (isConnected(token)) {
            Connected connected = this.connectedRepo.findByToken(token);
            logOut(connected);
        }
    }

    private void logOut (Connected connected) {
        connected.setStatus(false);
        this.connectedRepo.save(connected);
    }

    public boolean isConnected (String token) {
        Connected connected = connectedRepo.findByToken(token);
        if(connected==null){
            return false;
        }
        if (connected.getStatus()) {
            if (connected.getExpirationDate().before(Date.valueOf(LocalDate.now()))) {
                logOut(connected);
                return false;
            }
            return true;
        }
        return false;
    }

    public AuthenticatedDetails login(User usr) throws CustomValidation {
        List<User> users = userRepo.findByLogin(usr.getLogin());
        if (users.size() == 0)
            throw new CustomValidation("User not found");
        for (User user : users) {
            if (user.getPassword().equals(usr.getPassword())) {
                return buildDetails(user);
            }
        }
        throw new CustomValidation("Wrong password");
    }

    private AuthenticatedDetails buildDetails(User user) {
        String token = generateTokenForUser(user);

        Connected connected = new Connected();
        connected.setUser(user);
        connected.setToken(token);

        this.connectedRepo.save(connected);
        return new AuthenticatedDetails(token);
    }

    public String generateTokenForUser(User user) {
        String key = user.getId()+user.getLogin()+user.getPassword();
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        key = key+now;
        return DigestUtils.sha1Hex(key.getBytes());
    }

}
