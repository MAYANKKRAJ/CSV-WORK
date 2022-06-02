package com.csv.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.csv.enteties.CsvEntity;
import com.csv.repo.CsvRepository;

@Service
public class CsvService {

	@Autowired
	private CsvRepository csvRepository;

	@Value(value = "${file_path}")
	private String filePath;

	private static final Logger LOGGER = LogManager.getLogger(CsvService.class);

	@SuppressWarnings("resource")
	public String readCsvFile() throws IOException {
		LOGGER.debug("readCsvFile fuunction called : ");

		Integer countRecordsFromDb = csvRepository.countRecordsFromDb();
		int countRecordsFromCsv = 0;
		LOGGER.debug("read csv file from : " + filePath);

		BufferedReader fileReader = new BufferedReader(
				new InputStreamReader(new FileInputStream(new File(filePath)), "UTF-8"));
		CSVParser csvParser = new CSVParser(fileReader,
				CSVFormat.DEFAULT.withHeader("timestamp", "ver", "product_family", "country", "device_type", "os",
						"checkout_failure_count", "payment_api_failure_count", "purchase_count", "revenue"));
		LOGGER.debug("reading csv file : ");

		List<CsvEntity> list = new ArrayList<CsvEntity>();
		List<CSVRecord> records = csvParser.getRecords();
		countRecordsFromCsv = records.size();

		if (countRecordsFromCsv <= 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"CSV File can not be empty and null : " + filePath);
		}

		// remove old recods from list not from CSV
		records.subList(0, countRecordsFromDb).clear();

		if (!(countRecordsFromDb == countRecordsFromCsv)) {

			for (CSVRecord csv : records) {
				CsvEntity entity = new CsvEntity();
				entity.setTimestamp(csv.get("timestamp"));
				entity.setVer(csv.get("ver"));
				entity.setProduct_family(csv.get("product_family"));
				entity.setCountry(csv.get("country"));
				entity.setDevice_type(csv.get("device_type"));
				entity.setOs(csv.get("os"));
				entity.setCheckout_failure_count(csv.get("checkout_failure_count"));
				entity.setPayment_api_failure_count(csv.get("payment_api_failure_count"));
				entity.setPurchase_count(csv.get("purchase_count"));
				entity.setRevenue(csv.get("revenue"));
				list.add(entity);
			}
		} else {
			return "No Updated record found !!";
		}
		csvParser.close();
		csvRepository.saveAll(list);

		return "Record Updated successfully !!";

	}

	public CsvEntity addRowToCsv(CsvEntity csvEntity) throws IOException {
		BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath)), "UTF-8"));
		CSVParser parser = new CSVParser(fileReader,CSVFormat.DEFAULT.withHeader("timestamp", "ver", "product_family", "country", "device_type", "os","checkout_failure_count", "payment_api_failure_count", "purchase_count", "revenue"));
		
		//Get exiting records from csv
		List<CSVRecord> records = parser.getRecords();
		BufferedWriter br = new BufferedWriter(new FileWriter(new File(filePath)));
		CSVPrinter csvPrinter = new CSVPrinter(br, CSVFormat.DEFAULT);
		
		//Get exiting records from csv and save with new records
		csvPrinter.printRecords(records);
		
		csvPrinter.printRecord(csvEntity.getTimestamp(),csvEntity.getVer(),csvEntity.getProduct_family(),csvEntity.getCountry(),
				csvEntity.getDevice_type(), csvEntity.getOs(),csvEntity.getCheckout_failure_count(), csvEntity.getPayment_api_failure_count(),
				 csvEntity.getPurchase_count(), csvEntity.getRevenue()
				);
		parser.close();
		csvPrinter.flush();
		csvPrinter.close();
		return csvEntity;

	}

	@SuppressWarnings("resource")
	public String removeRecodsFromCSV(int rowNumber) throws IOException {
		BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath)), "UTF-8"));
		
		CSVParser csvParser = new CSVParser(fileReader,CSVFormat.DEFAULT.withHeader("timestamp", "ver", "product_family", "country", "device_type", "os","checkout_failure_count", "payment_api_failure_count", "purchase_count", "revenue"));
	
		//Get exiting records from csv
		List<CSVRecord> records = csvParser.getRecords();

		//Row size should greater file data
		if (!(records.size() > rowNumber)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data not present in CSV file : " + filePath);
		}
		
		BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath));
		CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
		records.remove(rowNumber);
		csvPrinter.printRecords(records);
		csvPrinter.flush();
		csvParser.close();
		return "Record deleted Successfully Form the CSV Row Number: " + rowNumber;
	}

}
