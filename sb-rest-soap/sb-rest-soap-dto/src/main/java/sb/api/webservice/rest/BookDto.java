package sb.api.webservice.rest;

import lombok.Data;

@Data
public class BookDto {

	private Integer id;
	private String title;
	private String author;
	private Integer year;
	private Integer isbn;
	private String editor;
	
}
