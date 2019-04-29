package sb.api.webservice.rest;

import java.util.Date;

import lombok.Data;
import sb.api.webservice.soap.StudentDetails;

@Data
public class BorrowDto {
	
	private Integer id;
	private StudentDetails student;
	private BookDto book;
	private Date startDate;
	private Date endDate;
	
	public BorrowDto() {
	}
	
}
