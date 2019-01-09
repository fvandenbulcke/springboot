package sb.rest.soap.api.unit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import sb.rest.soap.api.configuration.ConfigurationTest;
import sb.rest.soap.api.service.IBookService;
import sb.rest.soap.api.service.dto.Book;
import sb.rest.soap.api.service.exception.LibraryException;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= {ConfigurationTest.class})
public class BookServiceTest {

	@Autowired
	protected IBookService bookService;

	
//	@Test
//	public void create() {
//		List<Book> books = bookService.create("author","title");
//		assertThat(books, hasSize(4));
//	}
	
	@Test
	public void delete() {
		List<Book> books = bookService.delete(3);
		assertThat(books, hasSize(3));
	}
	
	@Test
	public void updateTitle() {
		List<Book> books = null;
		try {
			books = bookService.updateTitle(2,"newTitle");
		} catch (LibraryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertThat(books, hasSize(3));
	}

	@Test
	public void getAll() {
		List<Book> books = bookService.getAll();
		assertThat(books, hasSize(3));
	}
	
//	@Test
//	public void getById() {
//		Book expected = new Book(1,"Marcel Proust","In Search of Lost Time");
//		Book book = bookService.getById(1);
//		assertThat(book, samePropertyValuesAs(expected));
//	}
}
