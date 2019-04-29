package sb.api.webservice.rest;

import java.math.BigInteger;
import java.util.List;

import lombok.Data;

@Data
public class BookDto {

	private Integer id;
	private String title;
	private Integer year;
	private BigInteger isbn;
	private String author;
	private String editor;
	private List<BorrowDto> borrows;
	
	public BookDto() {}
	
	public BookDto(Integer id, String title, Integer year, BigInteger isbn, String author, String editor,List<BorrowDto> borrows) {
		this.id = id;
		this.title = title;
		this.year = year;
		this.isbn = isbn;
		this.author = author;
		this.editor = editor;
		this.borrows = borrows;
	}
}
