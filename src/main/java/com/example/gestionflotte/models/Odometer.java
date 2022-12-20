package com.example.gestionflotte.models;

import com.example.gestionflotte.exception.CustomValidation;
import com.example.gestionflotte.models.common.HasFK;
import com.example.gestionflotte.models.plane.PlaneEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Odometer implements HasFK<PlaneEntity> {

    @Id
    @SequenceGenerator(name = "odometer_id_seq", sequenceName = "odometer_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "odometer_id_seq")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plane_id")
    @JsonIgnore
    PlaneEntity plane;

    @Column
    @NotNull
    private Date date = Date.valueOf(LocalDate.now());

    @Column(name = "starts")
    @Min(0)
    @NotNull
    private Double start;

    @Column(name = "ends")
    @Min(0)
    @NotNull
    private Double end;

    @Override
    public void setFK(PlaneEntity entity) throws CustomValidation {
        if (entity == null) throw new CustomValidation("plane not found");
        setPlane(entity);
    }

}
