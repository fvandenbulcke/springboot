package sb.integration.java.dsl.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Header;

import sb.api.webservice.soap.StudentDetails;

@MessagingGateway
public interface CommonGateway {
	

	@Gateway(requestChannel="getStudentByIdChannel")
    public StudentDetails getById(@Header("bearer") String bearer, Integer studentId);

}
