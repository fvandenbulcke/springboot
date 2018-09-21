package sb.integration.java.dsl.transformer;

import org.springframework.stereotype.Service;

import sb.api.webservice.rest.BookDto;

@Service
public class BookTransformer {
	
	public BookDto createBook(String author, String title) {
		BookDto bookDto = new BookDto();
		bookDto.setAuthor(author);
		bookDto.setTitle(title);
		return bookDto;
	}

}
