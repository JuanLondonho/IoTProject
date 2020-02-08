package com.udea.iotProject.service;

import com.udea.iotProject.broker.IotSender;
import com.udea.iotProject.model.Data;
import com.udea.iotProject.repository.DataRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class DataService {

    private DataRepository dataRepository;
    private IotSender iotSender;

    public DataService(IotSender iotSender, DataRepository dataRepository){
        this.dataRepository = dataRepository;
        this.iotSender= iotSender;
    }
    
    
    public void sendMessage(String message) {
        iotSender.send("iot", message.getBytes());
    }
    
    public List<Data> findAllDevices(){
        return dataRepository.findAll();
    }

    public Data findCurrentStatus(int id){
    	return dataRepository.findCurrentStatus(id);
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
    
    public List<Data> findByLevel(){
    	String lev="c";
        return dataRepository.findByNoiseSignal(lev);   
    }


    public void processMessage(String message) {
    	
    	Data dataModel = new Data();
    	LocalDateTime date= LocalDateTime.now();
    	dataModel.setDate(date);
    	String[] parts = message.split("/");
    	dataModel.setId(Integer.parseInt(parts[0]));
		dataModel.setNoiseLevel(Integer.parseInt(parts[1]));
		dataModel.setTemperature(Integer.parseInt(parts[2]));
		dataModel.setHumidity(Integer.parseInt(parts[3]));
		dataModel.setLighting(Integer.parseInt(parts[4]));
		if(Integer.parseInt(parts[1]) > 1000) {
			dataModel.setStatus("ALTO");
		}else if (Integer.parseInt(parts[1]) > 700) {
			dataModel.setStatus("MEDIO");
		}else{
			dataModel.setStatus("BAJO");
		}

		dataRepository.save(dataModel);
    }
}
