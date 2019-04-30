package sb.rest.soap.api.rest;

import java.util.List;

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

import sb.api.webservice.rest.request.CreateBookDto;
import sb.rest.soap.api.service.BookService;
import sb.rest.soap.api.service.dto.Book;
import sb.rest.soap.api.service.exception.LibraryException;


@RestController(value="bookController")
@RequestMapping(path="/api/v1")
public class BookController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private BookService bookService;
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET,path="/book", produces = "application/json")
	public @ResponseBody List<Book> getAllBooks() {
		LOGGER.info("Web service call to get all books");
		return bookService.getAll();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET,path="/book/{bookId}", produces = "application/json")
	public @ResponseBody Book getBookById(
			@PathVariable(required = true) Integer bookId) throws LibraryException {
		LOGGER.info("Web service call to get book by id");
		return bookService.getById(bookId);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST,path="/book", produces = "application/json")
	public @ResponseBody List<Book> create(
			@RequestBody(required = true) CreateBookDto bookDto) {
		LOGGER.info("Web service call to create book");
		return bookService.create(bookDto);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT,path="/book/{bookId}", produces = "application/json")
	public @ResponseBody List<Book> updateTitle(
			@PathVariable(required = true) Integer bookId,
			@RequestBody(required = true) String title) throws LibraryException{
		LOGGER.info("Web service call to update title book");
		return bookService.updateTitle(bookId, title);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.DELETE,path="/book/{bookId}", produces = "application/json")
	public @ResponseBody List<Book> delete(
			@PathVariable(required = true) Integer bookId) {
		LOGGER.info("Web service call to delete book");
		return bookService.delete(bookId);
	}

}
