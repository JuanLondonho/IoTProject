package com.udea.iotProject.model;



import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "data")
public class Data {
    @Id
    private Integer id;

    @Id
    private  LocalDateTime date;
	
    private Integer noiseLevel;

	private Integer temperature;

	private Integer humidity;

	private Integer lighting;

	private String status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Integer getNoiseLevel() {
		return noiseLevel;
	}

	public void setNoiseLevel(Integer noiseLevel) {
		this.noiseLevel = noiseLevel;
	}

	public Integer getTemperature() {
		return temperature;
	}

	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
	}

	public Integer getHumidity() {
		return humidity;
	}

	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}

	public Integer getLighting() {
		return lighting;
	}

	public void setLighting(Integer lighting) {
		this.lighting = lighting;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}

