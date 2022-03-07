package com.haitoko.admin.user;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.haitoko.share.entity.User;

public class UserCsvExport {
	
	public void export(List<User> listUsers, HttpServletResponse response) throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String timestamp = dateFormat.format(new Date());
		String fileName = "HaitokoUser_" + timestamp +".csv";
		
		response.setContentType("text/csv");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=" + fileName;
		response.setHeader(headerKey, headerValue);
		
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		String[] csvHeader = {"ID", "Email", "Name", "Roles", "Status"};
		String[] dataMapping = {"id", "email", "name", "roles", "status"};
		
		csvWriter.writeHeader(csvHeader);
		
		for(User user : listUsers) {
			csvWriter.write(user, dataMapping);
		}
		
		csvWriter.close();
		
	}
}
