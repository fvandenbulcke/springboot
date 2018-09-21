package sb.rest.soap.api.service;

import java.util.List;

import sb.rest.soap.api.service.dto.Book;

public interface IBookService {
	
	public List<Book> getAll();
	
	public Book getById(Integer id);
	
	public List<Book> create(String author, String title);
	
	public List<Book> updateTitle(Integer id, String newTitle);
	
	public List<Book> delete(Integer id);
}
