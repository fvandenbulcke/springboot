package sb.rest.soap.api.webservice.soap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.codenotfound.types.helloworld.Greeting;
import com.codenotfound.types.helloworld.ObjectFactory;
import com.codenotfound.types.helloworld.Person;

import sb.api.webservice.soap.GetStudentByIdRequest;
import sb.api.webservice.soap.StudentDetails;
import sb.rest.soap.api.service.IStudentService;
import sb.rest.soap.api.service.dto.Student;

@Endpoint
public class HelloWorldEndpoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldEndpoint.class);

	@Autowired
	private IStudentService studentService;

	@PayloadRoot(namespace = "http://codenotfound.com/types/helloworld", localPart = "person")
	@ResponsePayload
	public Greeting sayHello(@RequestPayload Person request) {
		LOGGER.info("Endpoint received person[firstName={},lastName={}]", request.getFirstName(),
				request.getLastName());

		String greeting = "Hello " + request.getFirstName() + " " + request.getLastName() + "!";

		ObjectFactory factory = new ObjectFactory();
		Greeting response = factory.createGreeting();
		response.setGreeting(greeting);

		LOGGER.info("Endpoint sending greeting='{}'", response.getGreeting());
		return response;
	}
	
//	@PayloadRoot(namespace = "http://webservice.api.sb/soap/", localPart = "GetStudentByIdRequest")
//	@ResponsePayload
//	public StudentDetails getStudentById(@RequestPayload GetStudentByIdRequest request) {
//		LOGGER.info("Web service call to get student by id");
//
//		Student student = studentService.getById(request.getId());
//		StudentDetails studentDetails = getStudentDetails(student);
//		
//		return studentDetails;
//	}
	
	
	private StudentDetails getStudentDetails(Student student) {
		StudentDetails studentDetails = new StudentDetails();
		studentDetails.setId(student.getId());
		studentDetails.setName(student.getName());
		studentDetails.setPassportNumber(student.getPassportNumber());
		return studentDetails;
	}

}