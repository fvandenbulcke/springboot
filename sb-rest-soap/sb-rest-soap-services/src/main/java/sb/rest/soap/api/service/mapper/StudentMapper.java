package sb.rest.soap.api.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import sb.rest.soap.api.repository.models.BorrowBo;
import sb.rest.soap.api.repository.models.StudentBo;
import sb.rest.soap.api.service.dto.Borrow;
import sb.rest.soap.api.service.dto.Student;

@Mapper
public interface StudentMapper {

	List<Student> asStudentList(List<StudentBo> in);

	@Mapping(target="borrows", qualifiedByName = "noStudent")
	Student asStudent(StudentBo in);
	
	@Named("noStudent")
	@Mapping(target="book.borrows", ignore=true)
	@Mapping(target="student", ignore=true)
	Borrow asBorrowWithoutStudent(BorrowBo in);

}
