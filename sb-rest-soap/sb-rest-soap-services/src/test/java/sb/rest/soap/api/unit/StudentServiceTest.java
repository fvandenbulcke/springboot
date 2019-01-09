package sb.rest.soap.api.unit;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import sb.rest.soap.api.configuration.ConfigurationTest;
import sb.rest.soap.api.service.IStudentService;
import sb.rest.soap.api.service.dto.SearchStudents;
import sb.rest.soap.api.service.dto.Student;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= {ConfigurationTest.class})
public class StudentServiceTest {

	@Autowired
	protected IStudentService studentService;

	
//	@Test
//	public void create() {
//		List<Student> students = studentService.create("author","title");
//		assertThat(students, hasSize(4));
//	}
	
	@Test
	public void delete() {
		List<Student> students = studentService.delete(3);
		assertThat(students, hasSize(3));
	}
	
	@Test
	public void updateTitle() {
		List<Student> students = studentService.updateName(2,"newName");
		assertThat(students, hasSize(3));
	}

//	@Test
//	public void search() {
//		SearchStudents request = new SearchStudents();
//		request.setName("James");
//		request.setPassportNumber("2345");
//		List<Student> students = studentService.search(request);
//		assertThat(students, hasSize(2));
//	}
	
//	@Test()
//	public void getById() {
//		Student expected = new Student(1,"Marcel","123456");
//		Student book = studentService.getById(1);
//		assertThat(book, samePropertyValuesAs(expected));
//	}
}
