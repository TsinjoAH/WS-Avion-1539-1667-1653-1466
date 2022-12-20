package com.example.gestionflotte.models.plane;

import com.example.gestionflotte.models.common.HasID;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.binary.Base64;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Plane implements HasID {

    @Id
    @SequenceGenerator(name = "plane_id_seq", sequenceName = "plane_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plane_id_seq")
    private Long id;

    @Column(unique = true)
    @NotNull
    private String licensePlate;

    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private byte[] img;

    public Plane() {
    }

    public Plane(Long id, String licensePlate) {
        setId(id);
        setLicensePlate(licensePlate);
    }


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getImgBase64() {
        return new String(getImg());
    }
}
