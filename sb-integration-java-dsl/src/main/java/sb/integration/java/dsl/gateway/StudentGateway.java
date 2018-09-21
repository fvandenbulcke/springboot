package sb.integration.java.dsl.gateway;

import java.util.List;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Header;

import sb.api.webservice.soap.GetStudentByIdResponse;
import sb.api.webservice.soap.StudentDetails;
import sb.integration.java.dsl.message.SearchStudentMessage;

@MessagingGateway
public interface StudentGateway {

	@Gateway(requestChannel="getStudentByIdChannel")
    public GetStudentByIdResponse getStudentById(@Header("bearer") String bearer, Integer studentId);

	@Gateway(requestChannel="searchStudentsChannel")
    public List<StudentDetails> searchStudents(@Header("bearer") String bearer, SearchStudentMessage searchStudentMessage);
	
}
