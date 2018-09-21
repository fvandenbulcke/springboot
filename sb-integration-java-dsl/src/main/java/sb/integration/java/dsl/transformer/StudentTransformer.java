package sb.integration.java.dsl.transformer;

import java.util.List;

import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Service;

import sb.api.webservice.soap.GetStudentByIdRequest;
import sb.api.webservice.soap.GetStudentByIdResponse;
import sb.api.webservice.soap.SearchStudentsRequest;
import sb.api.webservice.soap.SearchStudentsResponse;
import sb.api.webservice.soap.StudentDetails;
import sb.integration.java.dsl.message.SearchStudentMessage;

@Service
public class StudentTransformer {
	
	public GetStudentByIdRequest createGetStudentByIdRequest(Integer studentId) {
		GetStudentByIdRequest request = new GetStudentByIdRequest();
		request.setId(studentId);
        return request;
	}
	
	public SearchStudentsRequest createSearchStudentRequest(SearchStudentMessage message) {
		SearchStudentsRequest request = new SearchStudentsRequest();
		request.setName(message.getName());
		request.setPassportNumber(message.getPassportNumber());
		return request;
	}
	
	@Transformer(inputChannel="asStudentChannel")
	public GetStudentByIdResponse asStudent(GetStudentByIdResponse response) {
        return response;
	}
	
	@Transformer(inputChannel="asStudentChannel")
	public List<StudentDetails> asStudentList(SearchStudentsResponse response) {
        return response.getStudentDetailList();
	}
	
}
