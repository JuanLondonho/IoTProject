package com.udea.iotProject.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.udea.iotProject.model.Data;
import org.springframework.data.jpa.repository.Query;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface DataRepository extends JpaRepository<Data,Integer> {
	
	 List<Data>findByDateBetweenAndNoiseLevelGreaterThan(LocalDateTime date1, LocalDateTime date2, int noiseL);
	 
	 List<Data>findByDateBetween(LocalDateTime date1, LocalDateTime date2);
	 
	 List<Data>findByNoiseSignal(String level);

	 @Query(value = "select * from Data where id = ?1 order by desc limit 1", nativeQuery = true)
	 Data findCurrentStatus(int id);



}