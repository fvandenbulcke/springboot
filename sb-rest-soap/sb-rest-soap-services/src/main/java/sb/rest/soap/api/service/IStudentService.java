package sb.rest.soap.api.service;

import java.util.List;

import sb.rest.soap.api.service.dto.SearchStudents;
import sb.rest.soap.api.service.dto.Student;
import sb.rest.soap.api.service.exception.LibraryException;

public interface IStudentService {

	public List<Student> getAll();
	
	public List<Student> search(SearchStudents request);
	
	public Student getById(Integer id);
	
	public List<Student> create(String firstName,String name,String level);
	
	public List<Student> updateName(Integer id, String newName) throws LibraryException;
	
	public List<Student> delete(Integer id);
}
