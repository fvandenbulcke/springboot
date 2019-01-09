package sb.rest.soap.api.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import sb.rest.soap.api.repository.models.StudentBo;
import sb.rest.soap.api.service.dto.Student;

@Mapper
public interface StudentMapper {

	Student asStudent(StudentBo in);
	List<Student> asStudentList(List<StudentBo> in);

}
