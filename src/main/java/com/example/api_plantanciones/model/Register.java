package com.example.api_plantanciones.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Table
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double temperature;
    private Double humidity;
    private Date dateOfDataRegistration;
    private LocalTime timeOfDataRegistration;

    @ManyToOne
    @JoinColumn(name = "sensor_id")
    @JsonIgnore
    private Sensor sensor;

    public Register() {
    }

    public Register(Long id, Double temperature, Double humidity, Date dateOfDataRegistration, LocalTime timeOfDataRegistration, Sensor sensor) {
        this.id = id;
        this.temperature = temperature;
        this.humidity = humidity;
        this.dateOfDataRegistration = dateOfDataRegistration;
        this.timeOfDataRegistration = timeOfDataRegistration;
        this.sensor = sensor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
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

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return "Register{" +
                "id=" + id +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", dateOfDataRegistration=" + dateOfDataRegistration +
                ", timeOfDataRegistration=" + timeOfDataRegistration +
                ", sensor=" + sensor +
                '}';
    }
}

