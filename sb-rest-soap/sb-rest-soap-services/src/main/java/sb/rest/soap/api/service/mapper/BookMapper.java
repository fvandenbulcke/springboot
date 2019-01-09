package sb.rest.soap.api.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import sb.rest.soap.api.repository.models.BookBo;
import sb.rest.soap.api.service.dto.Book;

@Mapper
public interface BookMapper {
	
	Book asBook(BookBo in);
	List<Book> asBookList(List<BookBo> in);
	
	
}
