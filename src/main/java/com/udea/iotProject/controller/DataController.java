package com.udea.iotProject.controller;

import java.util.*;

import com.udea.iotProject.model.DeviceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMethod;

import com.udea.iotProject.model.Data;
import com.udea.iotProject.service.DataService;


//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.CrossOrigin;
	

@RestController
@RequestMapping("/iot")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class DataController {

    @Autowired
    private DataService dataService;

  /*  @ApiOperation(value = "View a list of noise level", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })*/
    
    @GetMapping(path="/data")
    public List<Data> getAllData() {
        return dataService.findAllDevices();	
    }
    
   @GetMapping(path="/dateReport")
   public List<Data> getAllDates(@RequestParam (name="date1")String date1, @RequestParam (name="date2") String date2) {	  
       return dataService.findByDate(date1, date2);
    }
   	
   @GetMapping(path="/dateNoise")
   public List<Data> getAllDateNoiseLevel(@RequestParam (name="date1")String date1, @RequestParam (name="date2") String date2) {	
	   return dataService.findByDateNoise(date1, date2);
    }

    @PostMapping(path="/turnOnDevices")
    public void sendMessage() {
        dataService.sendMessage("-99");

    }

    @PostMapping(path="/sendGlobalMessage")
    public void sendMessage(@RequestParam (name="message") String message) {
       dataService.sendMessage(message);

  	}

    @PostMapping(path="/sendPrivateMessage")
    public void sendPrivateMessage(@RequestParam(name="DeviceName") String device, @RequestParam (name="message") String message) {
        dataService.sendPrivateMessage(device ,message);

    }

  	@GetMapping(path = "/currentStatus")
    public List<DeviceStatus> getCurrentStatus(){
        return dataService.findCurrentStatus();
    }
}