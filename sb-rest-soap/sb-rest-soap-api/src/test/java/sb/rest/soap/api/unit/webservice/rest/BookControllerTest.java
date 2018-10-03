package sb.rest.soap.api.unit.webservice.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import sb.api.webservice.rest.BookDto;
import sb.rest.soap.api.service.IBookService;
import sb.rest.soap.api.service.dto.Book;
import sb.rest.soap.api.webservice.rest.BookController;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {
	
	private ObjectMapper objectMapper = new ObjectMapper();

	@InjectMocks
	private BookController bookController;
	
	@Mock
	private IBookService bookService;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		Book book = new Book(1,"Marcel Proust","In Search of Lost Time");
		List<Book> books = new ArrayList<Book>();
		books.add(book);
		mockMvc = standaloneSetup(bookController).build();
		doReturn(books).when(bookService).getAll();
		doReturn(book).when(bookService).getById(any(Integer.class));
		doReturn(books).when(bookService).create(any(String.class),any(String.class));
		doReturn(books).when(bookService).updateTitle(any(Integer.class),any(String.class));
		doReturn(books).when(bookService).delete(any(Integer.class));
	}
	
	@Test
	public void getAll() throws Exception {
		mockMvc.perform(get("/api/v1/book")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].author", is("Marcel Proust")))
				.andExpect(jsonPath("$[0].title", is("In Search of Lost Time")));
	}

	@Test
	public void getById() throws Exception {
		mockMvc.perform(get("/api/v1/book/7")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.author", is("Marcel Proust")))
				.andExpect(jsonPath("$.title", is("In Search of Lost Time")));
	}

	@Test
	public void create() throws Exception {
		BookDto bookDto = new BookDto();
		bookDto.setAuthor("author");
		bookDto.setTitle("title");
		mockMvc.perform(post("/api/v1/book").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(bookDto)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].author", is("Marcel Proust")))
				.andExpect(jsonPath("$[0].title", is("In Search of Lost Time")));
	}

	@Test
	public void updateTitle() throws Exception {
		mockMvc.perform(put("/api/v1/book/1").contentType(MediaType.APPLICATION_JSON).content("newTitle"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].author", is("Marcel Proust")))
				.andExpect(jsonPath("$[0].title", is("In Search of Lost Time")));
	}

	@Test
	public void deleteBook() throws Exception {
		mockMvc.perform(delete("/api/v1/book/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].author", is("Marcel Proust")))
				.andExpect(jsonPath("$[0].title", is("In Search of Lost Time")));
	}
	
	
}
