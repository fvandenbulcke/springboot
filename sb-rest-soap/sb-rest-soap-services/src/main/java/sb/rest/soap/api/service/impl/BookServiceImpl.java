package sb.rest.soap.api.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sb.rest.soap.api.repository.BookRepository;
import sb.rest.soap.api.repository.models.BookBo;
import sb.rest.soap.api.service.IBookService;
import sb.rest.soap.api.service.dto.Book;
import sb.rest.soap.api.service.enums.Errors;
import sb.rest.soap.api.service.exception.LibraryException;
import sb.rest.soap.api.service.mapper.BookMapper;

@Service
public class BookServiceImpl implements IBookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookMapper mapper;

	private final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);
	
	
	@Override
	public List<Book> getAll() {
		LOGGER.info("Get all books");
		List<BookBo> books = StreamSupport
				.stream(bookRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return mapper.asBookList(books);
	}

	@Override
	public Book getById(Integer id) {
		LOGGER.info("Get book by id {}", id);
		Optional<BookBo> optional = bookRepository.findById(id);
		BookBo book = null;
		if (optional.isPresent()) {
			book = optional.get();
		}
		return mapper.asBook(book);
	}

	@Override
	public List<Book> create(String title, String author, Integer year, Integer isbn, String editor) {
		StringBuilder sb = new StringBuilder()
								.append(title)
								.append(" - ").append(author)
								.append(" - ").append(year)
								.append(" - ").append(isbn)
								.append(" - ").append(editor);

		LOGGER.info("Create book {}", sb.toString());
		BookBo newBook = BookBo.builder()
				.title(title)
				.author(author)
				.year(year)
				.isbn(isbn)
				.editor(editor)
				.build();
		bookRepository.save(newBook);
		return this.getAll();
	}

	@Override
	public List<Book> updateTitle(Integer id, String newTitle) throws LibraryException {
		LOGGER.info("Update book {} with title {}", id, newTitle);
		Optional<BookBo> optional = bookRepository.findById(id);
		BookBo book = null;
		if (optional.isPresent()) {
			book = optional.get();
		} else {
			throw new LibraryException(Errors.BOOK_NOT_EXIST);
		}
		
		book.setTitle(newTitle);
		bookRepository.save(book);
		return this.getAll();
	}

	@Override
	public List<Book> delete(Integer id) {
		LOGGER.info("Delete book {}", id);
		bookRepository.deleteById(id);
		return this.getAll();
	}

	
}
