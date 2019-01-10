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
	
	public Book() {}
	
	public Book(Integer id, String title, Integer year, Integer isbn, String author, String editor) {
		this.id = id;
		this.title = title;
		this.year = year;
		this.isbn = isbn;
		this.author = author;
		this.editor = editor;
	}
	
}
