package com.example.api_plantanciones.model;


import jakarta.persistence.*;

@Entity
@Table
public class Plantation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String typeOfCrop;
    private String latitude;
    private String longitude;

    public Plantation() {
    }

    public Plantation(Long id, String name, String typeOfCrop, String latitude, String longitude) {
        this.id = id;
        this.name = name;
        this.typeOfCrop = typeOfCrop;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeOfCrop() {
        return typeOfCrop;
    }

    public void setTypeOfCrop(String typeOfCrop) {
        this.typeOfCrop = typeOfCrop;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
