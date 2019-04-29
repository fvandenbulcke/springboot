package sb.rest.soap.api.service.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Borrow {
	
	private Integer id;
	private Student student;
	private Book book;
	private Date startDate;
	private Date endDate;
	

	public Borrow() {}
	
	public Borrow(Student student, Book book, Date startDate, Date endDate) {
		this.student = student;
		this.book = book;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	
	
}
