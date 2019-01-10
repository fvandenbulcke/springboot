package sb.rest.soap.api.unit;


import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import sb.rest.soap.api.repository.StudentRepository;
import sb.rest.soap.api.repository.models.StudentBo;
import sb.rest.soap.api.service.dto.SearchStudents;
import sb.rest.soap.api.service.dto.Student;
import sb.rest.soap.api.service.enums.Errors;
import sb.rest.soap.api.service.enums.LibraryConstants;
import sb.rest.soap.api.service.exception.LibraryException;
import sb.rest.soap.api.service.impl.StudentServiceImpl;
import sb.rest.soap.api.service.mapper.StudentMapper;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {

	@InjectMocks
	private StudentServiceImpl studentService;

	@Mock
	private StudentRepository studentRepository;

	@Mock
	private StudentMapper studentMapper;
	
	@Rule
	public ExpectedException thrownException = ExpectedException.none();
	
	private List<Student> students;
	private Student student;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		student = new Student(1,"firstName","name","level",null);
		students = new ArrayList<>();
		students.add(student);
	}
	
	@Test
	public void getAll() {
		doReturn(students).when(studentMapper).asStudentList(anyList());
		List<Student> students = studentService.getAll();
		assertThat(students, hasSize(1));
	}
	
	@Test
	public void searchByName() {
		doReturn(students).when(studentMapper).asStudentList(anyList());
		SearchStudents request = new SearchStudents();
		request.setValue("James");
		request.setCriteria(LibraryConstants.NAME.name());
		List<Student> students = studentService.search(request);
		assertThat(students, hasSize(1));
	}
	
	@Test
	public void searchByFirstName() {
		doReturn(students).when(studentMapper).asStudentList(anyList());
		SearchStudents request = new SearchStudents();
		request.setValue("James");
		request.setCriteria(LibraryConstants.FIRST_NAME.name());
		List<Student> students = studentService.search(request);
		assertThat(students, hasSize(1));
	}

	@Test
	public void getByIdWithNullResponse() {
		Student student = studentService.getById(1);
		assertThat(student, nullValue());
	}
	
	@Test
	public void getByIdWithNotNullResponse() {
		doReturn(student).when(studentMapper).asStudent(null);
		Student expected = new Student(1,"firstName","name","level",null);
		Student student = studentService.getById(1);
		assertThat(student, samePropertyValuesAs(expected));
	}
	
	@Test
	public void create() {
		doReturn(students).when(studentMapper).asStudentList(anyList());
		List<Student> students = studentService.create("firstName","name","level");
		assertThat(students, hasSize(1));
	}
	
	@Test
	public void updateExistingStudentName() {
		Optional<StudentBo> optional = Optional.of(new StudentBo());
		doReturn(optional).when(studentRepository).findById(any(Integer.class));
		doReturn(students).when(studentMapper).asStudentList(anyList());
		
		List<Student> students = null;
		try {
			students = studentService.updateName(2,"newName");
		} catch (LibraryException e) {
		}
		assertThat(students, hasSize(1));
	}

	@Test(expected = LibraryException.class)
	public void updateNotExistingStudentName() throws Exception {
		Optional<StudentBo> optional = Optional.empty();
		doReturn(optional).when(studentRepository).findById(any(Integer.class));
		studentService.updateName(2,"newName");
	}

	@Test
	public void updateNotExistingBookTitle_2() throws Exception {
		thrownException.expect(LibraryException.class);
		thrownException.expectMessage(containsString(Errors.STUDENT_NOT_EXIST.getValue()));
		Optional<StudentBo> optional = Optional.empty();
		doReturn(optional).when(studentRepository).findById(any(Integer.class));
		studentService.updateName(2,"newName");
	}
	
	@Test
	public void delete() {
		doReturn(students).when(studentMapper).asStudentList(anyList());
		List<Student> students = studentService.delete(3);
		assertThat(students, hasSize(1));
	}
	
}
