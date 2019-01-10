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
	
	public BookDto() {}
	
	public BookDto(Integer id, String title, Integer year, Integer isbn, String author, String editor) {
		this.id = id;
		this.title = title;
		this.year = year;
		this.isbn = isbn;
		this.author = author;
		this.editor = editor;
	}
}
