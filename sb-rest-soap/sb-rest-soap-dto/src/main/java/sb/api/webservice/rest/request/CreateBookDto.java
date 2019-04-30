package sb.api.webservice.rest.request;

import java.math.BigInteger;

import lombok.Data;

@Data
public class CreateBookDto {
	
	private String title;
	private Integer year;
	private BigInteger isbn;
	private String author;
	private String editor;
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder()
				.append(title)
				.append(" - ").append(author)
				.append(" - ").append(year)
				.append(" - ").append(isbn)
				.append(" - ").append(editor);
		return sb.toString();
	}

	
}
