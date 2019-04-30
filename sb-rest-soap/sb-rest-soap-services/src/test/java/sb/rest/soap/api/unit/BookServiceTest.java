package sb.rest.soap.api.unit;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doReturn;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import sb.rest.soap.api.repository.BookRepository;
import sb.rest.soap.api.repository.models.BookBo;
import sb.rest.soap.api.service.BookService;
import sb.rest.soap.api.service.dto.Book;
import sb.rest.soap.api.service.enums.Errors;
import sb.rest.soap.api.service.exception.LibraryException;
import sb.rest.soap.api.service.mapper.BookMapper;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

	@InjectMocks
	protected BookService bookService;

	@Mock
	private BookRepository bookRepository;
	
	@Mock
	private BookMapper bookMapper;
	
	@Rule
	public ExpectedException thrownException = ExpectedException.none();
	
	private List<Book> books;
	private Book book;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		book = new Book(1,"title",2019,BigInteger.valueOf(123456789),"author","editor");
		books = new ArrayList<>();
		books.add(book);
	}
	

	@Test
	public void getAll() {
		doReturn(books).when(bookMapper).asBookList(anyList());
		List<Book> books = bookService.getAll();
		assertThat(books, hasSize(1));
	}
	
	@Test
	public void getByIdWithNullResponse() throws LibraryException {
		Book book = bookService.getById(1);
		assertThat(book, nullValue());
	}
	
	@Test
	public void getByIdWithNotNullResponse() throws LibraryException {
		doReturn(book).when(bookMapper).asBook(null);
		Book expected = new Book(1,"title",2019,BigInteger.valueOf(123456789),"author","editor");
		Book book = bookService.getById(1);
		assertThat(book, samePropertyValuesAs(expected));
	}
	
	@Test
	public void create() {
		doReturn(books).when(bookMapper).asBookList(anyList());
		List<Book> books = bookService.create("title","author",2019,BigInteger.valueOf(123456789),"editor");
		assertThat(books, hasSize(1));
	}

	@Test
	public void updateExistingBookTitle() {
		Optional<BookBo> optional = Optional.of(new BookBo());
		doReturn(optional).when(bookRepository).findById(any(Integer.class));
		doReturn(books).when(bookMapper).asBookList(anyList());
		
		List<Book> books = null;
		try {
			books = bookService.updateTitle(2,"newTitle");
		} catch (LibraryException e) {
		}
		assertThat(books, hasSize(1));
	}

	@Test(expected = LibraryException.class)
	public void updateNotExistingBookTitle() throws Exception {
		Optional<BookBo> optional = Optional.empty();
		doReturn(optional).when(bookRepository).findById(any(Integer.class));
		bookService.updateTitle(2,"newTitle");
	}

	@Test
	public void updateNotExistingBookTitle_2() throws Exception {
		thrownException.expect(LibraryException.class);
		thrownException.expectMessage(containsString(Errors.BOOK_NOT_EXIST.getValue()));
		Optional<BookBo> optional = Optional.empty();
		doReturn(optional).when(bookRepository).findById(any(Integer.class));
		bookService.updateTitle(2,"newTitle");
	}

	@Test
	public void delete() {
		doReturn(books).when(bookMapper).asBookList(anyList());
		List<Book> books = bookService.delete(3);
		assertThat(books, hasSize(1));
	}

}
