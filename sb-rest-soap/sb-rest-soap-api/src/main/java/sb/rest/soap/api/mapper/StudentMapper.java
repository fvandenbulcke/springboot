package sb.rest.soap.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import sb.api.webservice.soap.SearchStudentsRequest;
import sb.api.webservice.soap.StudentDetails;
import sb.rest.soap.api.service.dto.SearchStudents;
import sb.rest.soap.api.service.dto.Student;

@Mapper
public interface StudentMapper {
	
	SearchStudents asSearchStudents(SearchStudentsRequest in);

	StudentDetails asStudentDetails(Student in);
	
	List<StudentDetails> asStudentDetailsList(List<Student> in);

}
