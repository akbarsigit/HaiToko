package com.haitoko.admin.customer;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.haitoko.share.entity.Customer;

public class CustomerCsvExport {
	
	public void exportCustomer(List<Customer> listCustomer, HttpServletResponse response) throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String timestamp = dateFormat.format(new Date());
		String fileName = "HaitokoCustomer_" + timestamp +".csv";
		
		response.setContentType("text/csv");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=" + fileName;
		response.setHeader(headerKey, headerValue);
		
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		String[] csvHeader = {"ID", "Email", "Name" ,"Phone_Number", "Address", "City", "Province", "Status", "created_time"};
		String[] dataMapping = {"id", "email", "name", "phoneNumber", "address", "city", "state", "status", "createdTime"};
		
		csvWriter.writeHeader(csvHeader);
		
		for(Customer myCustomer : listCustomer) {
			csvWriter.write(myCustomer, dataMapping);
		}
		
		csvWriter.close();
		
	}
}
