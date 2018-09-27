package sb.rest.soap.api.service;

import java.util.List;

import sb.api.webservice.soap.SearchStudentsRequest;
import sb.rest.soap.api.service.dto.Student;

public interface IStudentService {
	
	public List<Student> search(SearchStudentsRequest request);
	
	public Student getById(Integer id);
	
	public List<Student> create(String name, String passportNumber);
	
	public List<Student> updateName(Integer id, String newName);
	
	public List<Student> delete(Integer id);
}
