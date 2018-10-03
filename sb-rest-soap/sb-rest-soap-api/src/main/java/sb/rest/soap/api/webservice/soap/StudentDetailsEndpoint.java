package sb.rest.soap.api.webservice.soap;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import sb.api.webservice.soap.CreateStudentRequest;
import sb.api.webservice.soap.CreateStudentResponse;
import sb.api.webservice.soap.DeleteStudentRequest;
import sb.api.webservice.soap.DeleteStudentResponse;
import sb.api.webservice.soap.GetStudentByIdRequest;
import sb.api.webservice.soap.GetStudentByIdResponse;
import sb.api.webservice.soap.SearchStudentsRequest;
import sb.api.webservice.soap.SearchStudentsResponse;
import sb.api.webservice.soap.StudentDetails;
import sb.api.webservice.soap.UpdateStudentNameRequest;
import sb.api.webservice.soap.UpdateStudentNameResponse;
import sb.rest.soap.api.service.IStudentService;
import sb.rest.soap.api.service.dto.Student;

@Endpoint
public class StudentDetailsEndpoint {
	

	private final Logger LOGGER = LoggerFactory.getLogger(StudentDetailsEndpoint.class);

	@Autowired
	private IStudentService studentService;
	
	@PayloadRoot(namespace = "http://webservice.api.sb/soap", localPart = "GetStudentByIdRequest")
	@ResponsePayload
	public GetStudentByIdResponse getStudentById(@RequestPayload GetStudentByIdRequest request) {
		LOGGER.info("Web service call to get student by id");

		Student student = studentService.getById(request.getId());
		StudentDetails studentDetails = getStudentDetails(student);
		
		GetStudentByIdResponse response = new GetStudentByIdResponse();
		response.setVersion("v1");
		response.setStudentDetails(studentDetails);

		return response;
	}
	
	@PayloadRoot(namespace = "http://webservice.api.sb/soap", localPart = "SearchStudentsRequest")
	@ResponsePayload
	public SearchStudentsResponse search(@RequestPayload SearchStudentsRequest request) {
		LOGGER.info("Web service call to search students");

		List<Student> students = studentService.search(request);
		List<StudentDetails> studentDetailsList = getStudentDetailsList(students);
		
		SearchStudentsResponse response = new SearchStudentsResponse();
		response.getStudentDetailList().addAll(studentDetailsList);

		return response;
	}
	
	@PayloadRoot(namespace = "http://webservice.api.sb/soap", localPart = "CreateStudentRequest")
	@ResponsePayload
	public CreateStudentResponse create(@RequestPayload CreateStudentRequest request) {
		LOGGER.info("Web service call to create student");

		List<Student> students = studentService.create(request.getName(),request.getPassportNumber());
		List<StudentDetails> studentDetailsList = getStudentDetailsList(students);
		
		CreateStudentResponse response = new CreateStudentResponse();
		response.getStudentDetailList().addAll(studentDetailsList);

		return response;
	}
	
	@PayloadRoot(namespace = "http://webservice.api.sb/soap", localPart = "UpdateStudentNameRequest")
	@ResponsePayload
	public UpdateStudentNameResponse updateName(@RequestPayload UpdateStudentNameRequest request) {
		LOGGER.info("Web service call to update student name");

		List<Student> students = studentService.updateName(request.getId(),request.getName());
		List<StudentDetails> studentDetailsList = getStudentDetailsList(students);
		
		UpdateStudentNameResponse response = new UpdateStudentNameResponse();
		response.getStudentDetailList().addAll(studentDetailsList);

		return response;
	}
	
	@PayloadRoot(namespace = "http://webservice.api.sb/soap", localPart = "DeleteStudentRequest")
	@ResponsePayload
	public DeleteStudentResponse delete(@RequestPayload DeleteStudentRequest request) {
		LOGGER.info("Web service call to delete student");

		List<Student> students = studentService.delete(request.getId());
		List<StudentDetails> studentDetailsList = getStudentDetailsList(students);
		
		DeleteStudentResponse response = new DeleteStudentResponse();
		response.getStudentDetailList().addAll(studentDetailsList);

		return response;
	}
	

	private List<StudentDetails> getStudentDetailsList(List<Student> students) {
		return students.stream().map((s) -> getStudentDetails(s)).collect(Collectors.toList());
	}
	
	private StudentDetails getStudentDetails(Student student) {
		StudentDetails studentDetails = new StudentDetails();
		studentDetails.setId(student.getId());
		studentDetails.setName(student.getName());
		studentDetails.setPassportNumber(student.getPassportNumber());
		return studentDetails;
	}

}