package sb.rest.soap.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import sb.rest.soap.api.service.IBookService;
import sb.rest.soap.api.service.dto.Book;

@Service
public class BookServiceImpl implements IBookService {

	private List<Book> books;
	
	@PostConstruct
	private void initBooks() {
		books = new ArrayList<Book>();
		books.add(new Book(1,"Marcel Proust","In Search of Lost Time"));
		books.add(new Book(2,"James Joyce","Ulysses"));
		books.add(new Book(3,"Miguel de Cervantes","IDon Quixote"));
	}
	
	
	@Override
	public List<Book> getAll() {
		return books;
	}

	@Override
	public Book getById(Integer id) {
		return books.stream()
				.filter((b) -> id.equals(b.getId()))
				.findAny()
				.orElse(null);
	}

	@Override
	public List<Book> create(String author, String title) {
		Integer newId = books.size()+1;
		Book newBook = new Book(newId,author, title);
		books.add(newBook);
		return books;
	}

	@Override
	public List<Book> updateTitle(Integer id, String newTitle) {
		Book currentBook = books.stream()
							.filter((b) -> id.equals(b.getId()))
							.findAny()
							.orElse(null);
		currentBook.setTitle(newTitle);
		return books;
	}

	@Override
	public List<Book> delete(Integer id) {
		books = books.stream()
				.filter((b) -> b.getId()!=id)
				.map((b) -> {
					return b.getId()<id ? b : new Book(b.getId()-1,b.getAuthor(),b.getTitle());
				})
				.collect(Collectors.toList());
		return books;
	}


	public List<Book> getBooks() {
		return books;
	}


	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
}
