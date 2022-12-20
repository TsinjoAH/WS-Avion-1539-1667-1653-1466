package com.example.gestionflotte.models;

import com.example.gestionflotte.exception.CustomValidation;
import com.example.gestionflotte.models.common.HasFK;
import com.example.gestionflotte.models.plane.PlaneEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
public class Assurance implements HasFK<PlaneEntity> {

    @Id
    @SequenceGenerator(name = "assurance_id_seq", sequenceName = "assurance_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "assurance_id_seq")
    private long id;

    @OneToOne
    @JoinColumn(name = "plane_id")
    PlaneEntity plane;

    @Column
    @NotNull
    private Date debut;

    @Column
    @NotNull
    private Date fin;

    @Override
    public void setFK(PlaneEntity planeEntity) throws CustomValidation {
        if(planeEntity !=null){
            this.plane= planeEntity;
        }else{
            throw new CustomValidation("Plane not found");
        }
    }

    @Override
    public void setId(Long id) {
        this.id=id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public PlaneEntity getPlane() {
        return plane;
    }

    public void SetPlane(PlaneEntity plane) {
        this.plane = plane;
    }
}
