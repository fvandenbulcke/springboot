package sb.rest.soap.api.rest;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import sb.api.webservice.rest.BookDto;
import sb.rest.soap.api.mapper.BookMapper;
import sb.rest.soap.api.service.IBookService;
import sb.rest.soap.api.service.dto.Book;
import sb.rest.soap.api.service.exception.LibraryException;


@RestController(value="bookController")
@RequestMapping(path="/api/v1")
public class BookController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private IBookService bookService;
	
	private BookMapper mapper = Mappers.getMapper(BookMapper.class);
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET,path="/book", produces = "application/json")
	public @ResponseBody List<BookDto> getAllBooks() {
		LOGGER.info("Web service call to get all books");
		List<Book> books = bookService.getAll();
		return getBookList(books);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET,path="/book/{bookId}", produces = "application/json")
	public @ResponseBody BookDto getBookById(
			@PathVariable(required = true) Integer bookId) {
		LOGGER.info("Web service call to get book by id");
		Book book = bookService.getById(bookId);
		return mapper.asBook(book);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST,path="/book", produces = "application/json")
	public @ResponseBody List<BookDto> create(
			@RequestBody(required = true) BookDto bookDto) {
		LOGGER.info("Web service call to create book");
		List<Book> books = bookService.create(bookDto.getTitle(),bookDto.getAuthor(),bookDto.getYear(),bookDto.getIsbn(),bookDto.getEditor());
		return getBookList(books);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT,path="/book/{bookId}", produces = "application/json")
	public @ResponseBody List<BookDto> updateTitle(
			@PathVariable(required = true) Integer bookId,
			@RequestBody(required = true) String title) throws LibraryException{
		LOGGER.info("Web service call to update title book");
		List<Book> books = bookService.updateTitle(bookId, title);
		return getBookList(books);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.DELETE,path="/book/{bookId}", produces = "application/json")
	public @ResponseBody List<BookDto> delete(
			@PathVariable(required = true) Integer bookId) {
		LOGGER.info("Web service call to delete book");
		List<Book> books = bookService.delete(bookId);
		return getBookList(books);
	}

	private List<BookDto> getBookList(List<Book> books) {
		return mapper.asBookList(books);
	}
	
}
