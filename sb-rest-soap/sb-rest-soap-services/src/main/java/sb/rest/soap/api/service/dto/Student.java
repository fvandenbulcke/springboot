package sb.rest.soap.api.service.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Student {

	private Integer id;
	private String firstName;
	private String name;
	private String level;
	private Date creationDate;

}
