package sb.rest.soap.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import sb.api.webservice.rest.BookDto;
import sb.rest.soap.api.service.dto.Book;

@Mapper
public interface BookMapper {
	
	BookDto asBook(Book in);
	List<BookDto> asBookList(List<Book> in);
	
	
}
