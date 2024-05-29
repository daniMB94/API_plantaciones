package com.example.api_plantanciones.service;

import com.example.api_plantanciones.dto.sensor.SensorHumTemAvgHist;
import com.example.api_plantanciones.model.Plantation;
import com.example.api_plantanciones.model.Register;
import com.example.api_plantanciones.model.Sensor;
import com.example.api_plantanciones.repository.PlantationRepository;
import com.example.api_plantanciones.repository.SensorRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SensorServiceImpl implements SensorService {

//    Inyectamos el repositorio
    private final SensorRepository repository;
//    También hemos inyectado el repositorio de Plantation porque se usará en el endpoint /api/sensors/new
    private final PlantationRepository plantationRepository;

    public SensorServiceImpl(SensorRepository repository, PlantationRepository plantationRepository) {
        this.repository = repository;
        this.plantationRepository = plantationRepository;
    }

    @Override
    public List<Sensor> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Sensor save(Sensor sensor, Long id) {

        Optional<Plantation> optionalPlantation = this.plantationRepository.findById(id);

        if (optionalPlantation.isPresent()) {
            Plantation plantation = optionalPlantation.get();

            // Asigna la Plantation al Sensor
            sensor.setPlantation(plantation);

            // Guarda el Sensor en la base de datos
            return repository.save(sensor);
        } else {
            // Maneja el caso en que la Plantation no se encuentra en la base de datos
            throw new IllegalArgumentException("Plantation not found with id: " + id);
        }
    }

    @Override
    public Sensor update(Long id, Sensor sensor) {
        Optional<Sensor> optionalSensor = this.repository.findById(id);

//        Solo se actualizará la fecha de instalación y el lugar de instalación
        if(optionalSensor.isPresent()) {
            Sensor updatedSensor = optionalSensor.get();
            updatedSensor.setInstallationDate(sensor.getInstallationDate());
            updatedSensor.setInstallationPlace(sensor.getInstallationPlace());

            return this.repository.save(updatedSensor);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public String tempYHumeMediaPorFecha(Long sensorId, Date initialDate, Date finalDate) {
        Optional<Sensor> optionalSensor = this.repository.findById(sensorId);

        if(optionalSensor.isPresent()) {
            Sensor sensor = optionalSensor.get();
            List<Register> registros = sensor.getRegisters();
            Long sumaTemp = 0L;
            Long sumaHume = 0L;
            Integer contador = 0;
            for(Register registro : registros){
                if(registro.getDateOfDataRegistration().after(initialDate)){
                    sumaTemp += registro.getTemperature();
                    sumaHume += registro.getHumidity();
                    contador += 1;
                }

            }
            Long mediaTemp = sumaTemp/contador;
            Long mediaHume = sumaHume/contador;
            return "Entre el " + initialDate + " y " + finalDate + " la temperatura media ha sido " + mediaTemp + " grados centígrados y la humedad media ha sido del " + mediaHume + "%";
        }
    return "";
    }

    @Override
    public SensorHumTemAvgHist temYHumeMediaHistorica(Long sensorId) {
        Optional<Sensor> optionalSensor = this.repository.findById(sensorId);

        if(optionalSensor.isPresent()) {
            Sensor sensor = optionalSensor.get();
            List<Register> registros = sensor.getRegisters();
            Long sumaTemp = 0L;
            Long sumaHume = 0L;
            Integer contador = 0;
            for(Register registro : registros){
                sumaTemp += registro.getTemperature();
                sumaHume += registro.getHumidity();
                contador += 1;
            }
            Long mediaTemp = sumaTemp/contador;
            Long mediaHume = sumaHume/contador;
            return new SensorHumTemAvgHist(sensorId, mediaTemp, mediaHume);
        }
        return null;
    }
}
