package com.example.gestionflotte.models;

import com.example.gestionflotte.responses.AuthenticatedDetails;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Connected {

    @Id
    @SequenceGenerator(name = "connected_id_seq", sequenceName = "connected_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "connected_id_seq")
    private Long id;

    @Column
    private String token;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private Boolean status = true;

    @Column
    private Date expirationDate = Date.valueOf(java.time.LocalDate.now().plusDays(1));

    public AuthenticatedDetails extractDetails (){
        return new AuthenticatedDetails(token);
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
