package one.digitalinnovation.parking.service;

import one.digitalinnovation.parking.exception.ParkingNotFoundException;
import one.digitalinnovation.parking.model.Parking;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private static Map<String, Parking> parkingMap = new HashMap();

    static{
        var id1 = getUUID();
        var id = getUUID();

        Parking parking1=new Parking(id1,"RXT-2222","PE","Polo","Branco");
        Parking parking=new Parking(id,"DMS-1111","PE","UNO","PRETO");
        parkingMap.put(id1,parking1);
        parkingMap.put(id,parking);
    }

    public List<Parking> findAll(){
        return parkingMap.values().stream().collect(Collectors.toList());
    }
    private static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public Parking findById(String id) {
        Parking parking = parkingMap.get(id);
        if(parking == null){
            throw new ParkingNotFoundException(id);
        }
        return parking;
    };

    public Parking create(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingMap.put(uuid, parkingCreate);
        return  parkingCreate;
    }

    public void delete(String id) {
        findById(id);
        parkingMap.remove(id);

    }

    public Parking update(String id, Parking parkingCreate) {
        Parking parking = findById(id);
        parking.setColor(parkingCreate.getColor()); //Alterando só a cor
        parkingMap.replace(id, parking);
        return  parking;
    }
}
