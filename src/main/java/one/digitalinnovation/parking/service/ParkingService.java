package one.digitalinnovation.parking.service;

import one.digitalinnovation.parking.exception.ParkingNotFoundException;
import one.digitalinnovation.parking.model.Parking;
import one.digitalinnovation.parking.repository.ParkingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private final ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Parking> findAll(){
        return parkingRepository.findAll();

    }
    private static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    @Transactional(readOnly = true)
    public Parking findById(String id) {
       return parkingRepository.findById(id).orElseThrow( () ->
               new ParkingNotFoundException(id));
    };

    @Transactional
    public Parking create(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingRepository.save(parkingCreate);
        return  parkingCreate;
    }

    @Transactional
    public void delete(String id) {
        findById(id);
        parkingRepository.deleteById(id);

    }

    @Transactional
    public Parking update(String id, Parking parkingCreate) {
        Parking parking = findById(id);
        parking.setColor(parkingCreate.getColor());
        parking.setState(parkingCreate.getState());
        parking.setModel(parkingCreate.getModel());
        parking.setLicense(parkingCreate.getLicense());
        parkingRepository.save(parking);
        return  parking;
    }

    // O meotodo checkOut faz a lógica de calcular o valor do tempo de entrada e saida do estacionamento
    @Transactional
    public Parking checkOut(String id) {
        Parking parking = findById(id); //Recuperar o estacionamento
        parking.setExitDate(LocalDateTime.now());// pegar a hora de saida
        parking.setBill(ParkingCheckOut.getBill(parking));// calcular o valor
        parkingRepository.save(parking);
        return parking;
    }
}
