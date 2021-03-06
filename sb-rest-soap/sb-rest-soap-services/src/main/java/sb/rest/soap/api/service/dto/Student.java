package sb.rest.soap.api.service.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Student {

	private Integer id;
	private String firstName;
	private String name;
	private String level;
	private Date creationDate;
	private List<Borrow> borrows;
	
	public Student() {}

	public Student(Integer id, String firstName, String name, String level, Date creationDate) {
		this.id = id;
		this.firstName = firstName;
		this.name = name;
		this.level = level;
		this.creationDate = creationDate;
	}
	
}
