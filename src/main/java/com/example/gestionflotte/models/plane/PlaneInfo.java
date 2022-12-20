package com.example.gestionflotte.models.plane;

import com.example.gestionflotte.models.Odometer;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "v_plane")
public class PlaneInfo extends Plane {

    @Column(name = "current_odo")
    private Double currentKm;

    @Column
    private Date begin_assurance;

    @Column
    private Date end_assurance;

    @OneToMany
    @JoinColumn(name = "plane_id")
    @JsonProperty("kmList")
    private List<Odometer> odometers;

}
