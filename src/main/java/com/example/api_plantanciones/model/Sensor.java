package com.example.api_plantanciones.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    Lugar donde está instalado dentro del cultivo
    private String installationPlace;
    private Date installationDate;

    public Sensor() {
    }

    public Sensor(Long id, String installationPlace, Date installationDate) {
        this.id = id;
        this.installationPlace = installationPlace;
        this.installationDate = installationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstallationPlace() {
        return installationPlace;
    }

    public void setInstallationPlace(String installationPlace) {
        this.installationPlace = installationPlace;
    }

    public Date getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(Date installationDate) {
        this.installationDate = installationDate;
    }
}
