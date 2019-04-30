package sb.rest.soap.api.unit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import sb.api.webservice.rest.BookDto;
import sb.rest.soap.api.mapper.BookMapper;
import sb.rest.soap.api.rest.BookController;
import sb.rest.soap.api.service.BookService;
import sb.rest.soap.api.service.dto.Book;
import sb.rest.soap.api.service.exception.LibraryException;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

	@InjectMocks
	private BookController bookController;
	
	@Mock
	private BookService bookService;
	
	@Mock
	private BookMapper bookMapper;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() throws LibraryException {
		mockMvc = standaloneSetup(bookController).build();
		
		Book book = new Book();
		doReturn(book).when(bookService).getById(any(Integer.class));
		
		BookDto dto = new BookDto(1,"title",2019,BigInteger.valueOf(123456789),"author","editor",null);
		List<BookDto> dtoList = new ArrayList<>();
		dtoList.add(dto);

		doReturn(dto).when(bookMapper).asBook(any(Book.class));
		doReturn(dtoList).when(bookMapper).asBookList(anyList());
	}
	
	@Test
	public void getAll() throws Exception {
		mockMvc.perform(get("/api/v1/book")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].author", is("author")))
				.andExpect(jsonPath("$[0].title", is("title")));
	}

	@Test
	public void getById() throws Exception {
		mockMvc.perform(get("/api/v1/book/7")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.author", is("author")))
				.andExpect(jsonPath("$.title", is("title")));
	}

	@Test
	public void create() throws Exception {
		String emptyDto = "{}";
		mockMvc.perform(post("/api/v1/book")
				.contentType(MediaType.APPLICATION_JSON)
				.content(emptyDto))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].author", is("author")))
				.andExpect(jsonPath("$[0].title", is("title")));
	}

	@Test
	public void updateTitle() throws Exception {
		String emptyTitle = "{}";
		mockMvc.perform(put("/api/v1/book/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(emptyTitle))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].author", is("author")))
				.andExpect(jsonPath("$[0].title", is("title")));
	}

	@Test
	public void deleteBook() throws Exception {
		mockMvc.perform(delete("/api/v1/book/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].author", is("author")))
				.andExpect(jsonPath("$[0].title", is("title")));
	}
}
