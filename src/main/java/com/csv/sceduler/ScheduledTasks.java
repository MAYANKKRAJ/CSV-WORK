package com.csv.sceduler;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.csv.service.CsvService;

@Configuration
@EnableScheduling
public class ScheduledTasks {
	
	@Autowired
	private CsvService csvService;

	//Registring the Task to be scheduled at fixed time
	@Scheduled(fixedDelay = 2*60*1000)  //3*1000 = 3sec
	public void scheduleFixedDelayTask() throws IOException {
		String response = csvService.readCsvFile();
		System.out.println(response);
	}
}
