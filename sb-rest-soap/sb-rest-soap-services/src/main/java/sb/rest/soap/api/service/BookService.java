package sb.rest.soap.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sb.api.webservice.rest.request.CreateBookDto;
import sb.rest.soap.api.repository.BookRepository;
import sb.rest.soap.api.repository.models.BookBo;
import sb.rest.soap.api.service.dto.Book;
import sb.rest.soap.api.service.enums.Errors;
import sb.rest.soap.api.service.exception.LibraryException;
import sb.rest.soap.api.service.mapper.BookMapper;

@Service
public class BookService{

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookMapper mapper;

	private final Logger LOGGER = LoggerFactory.getLogger(BookService.class);
	
	
	public List<Book> getAll() {
		LOGGER.info("Get all books");
		List<BookBo> books = StreamSupport
				.stream(bookRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return mapper.asBookList(books);
	}

	public Book getById(Integer id) throws LibraryException {
		BookBo book = this.getBookById(id);
		return mapper.asBook(book);
	}
	
	public BookBo getBookById(Integer id) throws LibraryException {
		LOGGER.info("Get book by id {}", id);
		Optional<BookBo> optional = bookRepository.findById(id);
		if (!optional.isPresent()) {
			throw new LibraryException(Errors.BOOK_NOT_EXIST);
		}
		BookBo book = null;
		if (optional.isPresent()) {
			book = optional.get();
		}
		return book;
	}

	public List<Book> create(CreateBookDto dto) {
		LOGGER.info("Create book {}", dto.toString());

		BookBo newBook = mapper.asBo(dto);
		bookRepository.save(newBook);
		return this.getAll();
	}

	public List<Book> updateTitle(Integer id, String newTitle) throws LibraryException {
		LOGGER.info("Update book {} with title {}", id, newTitle);
		Optional<BookBo> optional = bookRepository.findById(id);
		if (!optional.isPresent()) {
			throw new LibraryException(Errors.BOOK_NOT_EXIST);
		}
		
		BookBo book = optional.get();
		book.setTitle(newTitle);
		bookRepository.save(book);
		return this.getAll();
	}
	
	public List<Book> updateTitle3(Integer id, String newTitle) throws LibraryException {
		LOGGER.info("Update book {} with title {}", id, newTitle);
		Optional<BookBo> optional = bookRepository.findById(id);
		BookBo book = this.isExist(optional);
		
		book.setTitle(newTitle);
		bookRepository.save(book);
		return this.getAll();
	}
	
	private BookBo isExist(Optional<BookBo> optional) throws LibraryException {
		BookBo book = null;
		if (optional.isPresent()) {
			book = optional.get();
		} else {
			throw new LibraryException(Errors.BOOK_NOT_EXIST);
		}
		return book;
	}

	public List<Book> delete(Integer id) {
		LOGGER.info("Delete book {}", id);
		bookRepository.deleteById(id);
		return this.getAll();
	}

	
}
