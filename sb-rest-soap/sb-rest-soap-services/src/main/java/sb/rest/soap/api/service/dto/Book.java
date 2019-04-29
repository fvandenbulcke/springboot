package sb.rest.soap.api.service.dto;

import java.math.BigInteger;
import java.util.List;

import lombok.Data;

@Data
public class Book {
	
	private Integer id;
	private String title;
	private Integer year;
	private BigInteger isbn;
	private String author;
	private String editor;
	private List<Borrow> borrows;
	
	public Book() {}
	
}
