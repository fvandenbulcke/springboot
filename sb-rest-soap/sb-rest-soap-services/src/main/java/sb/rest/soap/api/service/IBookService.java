package sb.rest.soap.api.service;

import java.util.List;

import sb.rest.soap.api.service.dto.Book;
import sb.rest.soap.api.service.exception.LibraryException;

public interface IBookService {
	
	public List<Book> getAll();
	
	public Book getById(Integer id);
	
	public List<Book> create(String title, String author, Integer year, Integer isbn, String editor);
	
	public List<Book> updateTitle(Integer id, String newTitle) throws LibraryException;
	
	public List<Book> delete(Integer id);
}
