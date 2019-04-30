package sb.rest.soap.api.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import sb.api.webservice.rest.request.CreateBookDto;
import sb.rest.soap.api.repository.models.BookBo;
import sb.rest.soap.api.repository.models.BorrowBo;
import sb.rest.soap.api.service.dto.Book;
import sb.rest.soap.api.service.dto.Borrow;

@Mapper
public interface BookMapper {

	List<Book> asBookList(List<BookBo> in);

	@Mapping(target="borrows", qualifiedByName = "noBook")
	Book asBook(BookBo in);
	
	@Named("noBook")
	@Mapping(target="book", ignore=true)
	@Mapping(target="student.borrows", ignore=true)
	Borrow asBorrowWithoutBook(BorrowBo in);
	
	BookBo asBo(CreateBookDto in);
}
