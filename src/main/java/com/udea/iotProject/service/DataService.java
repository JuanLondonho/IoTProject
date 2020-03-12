package com.udea.iotProject.service;

import com.udea.iotProject.broker.IotSender;
import com.udea.iotProject.model.Data;
import com.udea.iotProject.model.DeviceStatus;
import com.udea.iotProject.repository.DataRepository;
import com.udea.iotProject.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class DataService {

	private DeviceRepository deviceRepository;
    private DataRepository dataRepository;
    private IotSender iotSender;

    public DataService(IotSender iotSender, DataRepository dataRepository, DeviceRepository deviceRepository){
        this.dataRepository = dataRepository;
        this.deviceRepository = deviceRepository;
        this.iotSender= iotSender;
    }
    
    
    public void sendMessage(String message) {
        iotSender.send("iot", message.getBytes());
    }

    public void sendPrivateMessage(String device, String message) {
        iotSender.send(""+device, message.getBytes());
    }
    
    public List<Data> findAllDevices(){
        return dataRepository.findAll();
    }

    public List<DeviceStatus> findCurrentStatus(){
    	return deviceRepository.deviceStatus();
	}
    
    
    public List<Data> findByDate(String dateIn, String dateE){
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    	LocalDateTime date1 = LocalDateTime.parse(dateIn, formatter);
    	LocalDateTime date2 = LocalDateTime.parse(dateE, formatter);
        return dataRepository.findByDateBetween(date1, date2);   
    }
    
    public List<Data> findByDateNoise(String dateIn, String dateE){
    	int noise=1000;
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    	LocalDateTime date1 = LocalDateTime.parse(dateIn, formatter);
    	LocalDateTime date2 = LocalDateTime.parse(dateE, formatter);
        return dataRepository.findByDateBetweenAndNoiseLevelGreaterThan(date1, date2, noise);   
    }


    public void processMessage(String message) {
    	
    	Data dataModel = new Data();
		DeviceStatus device = new DeviceStatus();
    	LocalDateTime date= LocalDateTime.now();
    	dataModel.setDate(date);
    	device.setDate(date);
    	String[] parts = message.split("/");
    	dataModel.setDeviceName(parts[0]);
    	device.setDeviceName(parts[0]);
		dataModel.setNoiseLevel(Integer.parseInt(parts[1]));
		device.setNoiseLevel(Integer.parseInt(parts[1]));
		dataModel.setTemperature(Integer.parseInt(parts[2]));
		device.setTemperature(Integer.parseInt(parts[2]));
		dataModel.setHumidity(Integer.parseInt(parts[3]));
		device.setHumidity(Integer.parseInt(parts[3]));
		dataModel.setLighting(Integer.parseInt(parts[4]));
		device.setLighting(Integer.parseInt(parts[4]));
		String status;
		if(Integer.parseInt(parts[1]) > 1000) {
			status = "ALTO";
		}else if (Integer.parseInt(parts[1]) > 700) {
			status = "MEDIO";
		}else{
			status = "BAJO";
		}

		dataModel.setStatus(status);
		device.setStatus(status);
		device.setSala(Integer.parseInt(parts[5]));

		dataRepository.save(dataModel);
		deviceRepository.save(device);

    }
}
