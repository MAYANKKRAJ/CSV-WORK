package com.csv.controller;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csv.enteties.CsvEntity;
import com.csv.service.CsvService;

@RestController
@EnableScheduling
@RequestMapping("/v1/csv/")
public class CsvController {

	private static final Logger LOGGER = LogManager.getLogger(CsvController.class);

	@Autowired
	private CsvService csvService;

	@GetMapping("/read-csv")
	public String readCsvFile() throws IOException {
		LOGGER.debug("Csv Controller end point called : ");
		return csvService.readCsvFile();
	}

	@PostMapping("/add-records-to-csv")
	public CsvEntity addRowToCsv(@RequestBody CsvEntity csvEntity) throws IOException {
		return csvService.addRowToCsv(csvEntity);
	}

	@DeleteMapping("/remove/{row}")
	public String removeRowFromCsv(@PathVariable int row) throws IOException {
		LOGGER.debug("Csv Controller end point called : ");
		return csvService.removeRecodsFromCSV(row);
	}
}
