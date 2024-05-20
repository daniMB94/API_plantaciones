package com.example.api_plantanciones.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    Lugar donde está instalado dentro del cultivo
    private String installationPlace;
    private Date installationDate;

    @ManyToOne
    @JoinColumn(name = "plantation_id")
    @JsonIgnore
    private Plantation plantation;

    @OneToMany(mappedBy = "sensor")
    @JsonIgnore
    private List<Register> registers = new ArrayList<Register>();

    public Sensor() {
    }

    public Sensor(Long id, String installationPlace, Date installationDate, Plantation plantation, List<Register> registers) {
        this.id = id;
        this.installationPlace = installationPlace;
        this.installationDate = installationDate;
        this.plantation = plantation;
        this.registers = registers;
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

    public Plantation getPlantation() {
        return plantation;
    }

    public void setPlantation(Plantation plantation) {
        this.plantation = plantation;
    }

    public List<Register> getRegisters() {
        return registers;
    }

    public void setRegisters(List<Register> registers) {
        this.registers = registers;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", installationPlace='" + installationPlace + '\'' +
                ", installationDate=" + installationDate +
                ", plantation=" + plantation +
                ", registers=" + registers +
                '}';
    }
}
