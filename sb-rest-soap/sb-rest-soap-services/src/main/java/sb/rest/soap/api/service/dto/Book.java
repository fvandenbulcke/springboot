package sb.rest.soap.api.service.dto;

import lombok.Data;

@Data
public class Book {
	
	private Integer id;
	private String title;
	private Integer year;
	private Integer isbn;
	private String author;
	private String editor;
}
