package sb.integration.java.dsl.integeration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import sb.api.webservice.soap.GetStudentByIdResponse;
import sb.api.webservice.soap.StudentDetails;
import sb.integration.java.dsl.MainClassApplication;
import sb.integration.java.dsl.gateway.StudentGateway;
import sb.integration.java.dsl.message.SearchStudentMessage;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=MainClassApplication.class)
public class StudentChannels {
	
	@Autowired
	private StudentGateway studentGateway;
	
	private String falseBearer = "123456";
	
	@Test
	public void getStudentById() {
		GetStudentByIdResponse student = studentGateway.getStudentById(falseBearer,1);
		StudentDetails details = new StudentDetails();
		details.setId(1);
		details.setName("Marcel");
		details.setPassportNumber("123456");
		assertThat(student.getVersion(), equalTo("v1"));
		assertThat(student.getStudentDetails(), samePropertyValuesAs(details));
	}
	
	
	
	@Test
	public void searchStudents() {
		String name = "Marcel";
		String passportNumber = "456";
		
		SearchStudentMessage message = new SearchStudentMessage();
		message.setName(name);
		message.setPassportNumber(passportNumber);
		
		List<StudentDetails> students = studentGateway.searchStudents(falseBearer, message);
		assertThat(students, hasSize(2));
	}
	
}
