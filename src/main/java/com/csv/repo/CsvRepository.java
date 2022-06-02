package com.csv.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.csv.enteties.CsvEntity;

@Repository
public interface CsvRepository extends JpaRepository<CsvEntity, Integer> {
	
	@Query(value = "select count(*) from csv_entity" ,nativeQuery = true)
	Integer countRecordsFromDb();
	

}
