package com.bartolay.inventory.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bartolay.inventory.enums.TransactionType;

@RestController
public class FileUploadRestController {

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void upload(@RequestParam MultipartFile file, @RequestParam TransactionType type) {
		System.err.println(file);
		switch (type) {
		case SALES_CANCEL_INVOICE:

			break;
		case SALES_INVOICE:
			break;
		case SALES_RETURN:
			break;
		case STOCK_ADJUSTMENT:
			break;
		case STOCK_OPENING:
			break;
		case STOCK_RECIEVE:
			break;
		case STOCK_TRANSFER:
			break;
		default:
			break;
		}
	}

	public void mapSalesInvoice(MultipartFile file) throws IOException {
		
		try(Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			
			CSVParser parser = CSVParser.parse(reader, CSVFormat.DEFAULT);
			
			boolean isColumnHeader  = true;
			
			for(CSVRecord record : parser) {
				if(!isColumnHeader) {
					
				} else {
					isColumnHeader = false;
				}
			}
			
		}
		
//		ssInputStream inputStream = file.getInputStream();
		
		while(inputStream.)
	}
}
