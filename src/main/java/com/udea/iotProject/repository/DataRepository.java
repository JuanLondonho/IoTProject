package com.udea.iotProject.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.udea.iotProject.model.Data;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface DataRepository extends JpaRepository<Data,Integer> {
	
	 List<Data>findByDateBetweenAndRuidoGreaterThan(LocalDateTime date1, LocalDateTime date2, int noiseL);
	 
	 List<Data>findByDateBetween(LocalDateTime date1, LocalDateTime date2);
	 
	 List<Data>findByNoiseLevel(String level);
	
}
