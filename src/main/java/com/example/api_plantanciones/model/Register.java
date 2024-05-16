package com.example.api_plantanciones.model;


import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Table
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long temperature;
    private Long humidity;
    private Date dateOfDataRegistration;
    private LocalTime timeOfDataRegistration;

    public Register() {
    }

    public Register(Long id, Long temperature, Long humidity, Date dateOfDataRegistration, LocalTime timeOfDataRegistration) {
        this.id = id;
        this.temperature = temperature;
        this.humidity = humidity;
        this.dateOfDataRegistration = dateOfDataRegistration;
        this.timeOfDataRegistration = timeOfDataRegistration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTemperature() {
        return temperature;
    }

    public void setTemperature(Long temperature) {
        this.temperature = temperature;
    }

    public Long getHumidity() {
        return humidity;
    }

    public void setHumidity(Long humidity) {
        this.humidity = humidity;
    }

    public Date getDateOfDataRegistration() {
        return dateOfDataRegistration;
    }

    public void setDateOfDataRegistration(Date dateOfDataRegistration) {
        this.dateOfDataRegistration = dateOfDataRegistration;
    }

    public LocalTime getTimeOfDataRegistration() {
        return timeOfDataRegistration;
    }

    public void setTimeOfDataRegistration(LocalTime timeOfDataRegistration) {
        this.timeOfDataRegistration = timeOfDataRegistration;
    }
}

