package sb.rest.soap.api.integration;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.noFault;
import static org.springframework.ws.test.server.ResponseMatchers.payload;
import static org.springframework.ws.test.server.ResponseMatchers.validPayload;

import java.io.IOException;

import javax.xml.transform.Source;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

import sb.rest.soap.api.configuration.WebServiceConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebServiceConfig.class)
public class StudentDetailsEndpointTest extends StudentDetailsEndpointTestUtils {
	
    @Autowired
    private ApplicationContext applicationContext;

    private MockWebServiceClient mockClient;
    private Resource xsdSchema = new ClassPathResource("students.xsd");

    @Before
    public void init(){
        mockClient = MockWebServiceClient.createClient(applicationContext);
    }

    
    @Test
    public void create() throws IOException {
        StringBuilder request = new StringBuilder()
									.append("<CreateStudentRequest xmlns=\"http://webservice.api.sb/soap\">")
									.append("<name>Loic</name>")
									.append("<passportNumber>987654</passportNumber>")
									.append("</CreateStudentRequest>");
    	
    	Source requestPayload = new StringSource(request.toString());

        StringBuilder response = new StringBuilder()
									.append("<ns2:CreateStudentResponse xmlns:ns2=\"http://webservice.api.sb/soap\">")
									.append(this.getStudentDetailsList(1,"Marcel", 123456))
									.append(this.getStudentDetailsList(2,"James", 456789))
									.append(this.getStudentDetailsList(3,"Miguel", 789123))
									.append(this.getStudentDetailsList(4,"Loic", 987654))
									.append("</ns2:CreateStudentResponse>");
    	
        Source responsePayload = new StringSource(response.toString());
        validTestResults(requestPayload, responsePayload);
    }
    
    @Test
    public void delete() throws IOException {
        StringBuilder request = new StringBuilder()
									.append("<DeleteStudentRequest xmlns=\"http://webservice.api.sb/soap\">")
									.append("<id>4</id>")
									.append("</DeleteStudentRequest>");
    	
    	Source requestPayload = new StringSource(request.toString());

        StringBuilder response = new StringBuilder()
									.append("<ns2:DeleteStudentResponse xmlns:ns2=\"http://webservice.api.sb/soap\">")
									.append(this.getStudentDetailsList(1,"Marcel", 123456))
									.append(this.getStudentDetailsList(2,"James", 456789))
									.append(this.getStudentDetailsList(3,"Miguel", 789123))
									.append("</ns2:DeleteStudentResponse>");
    	
        Source responsePayload = new StringSource(response.toString());
        validTestResults(requestPayload, responsePayload);
    }
    
    @Test
    public void getById() throws IOException {
        StringBuilder request = new StringBuilder()
									.append("<GetStudentByIdRequest xmlns=\"http://webservice.api.sb/soap\">")
									.append("<id>1</id>")
									.append("</GetStudentByIdRequest>");
    	
    	Source requestPayload = new StringSource(request.toString());

        StringBuilder response = new StringBuilder()
									.append("<ns2:GetStudentByIdResponse xmlns:ns2=\"http://webservice.api.sb/soap\">")
									.append("<ns2:version>v1</ns2:version>")
									.append(this.getStudentDetails(1,"Marcel", 123456))
									.append("</ns2:GetStudentByIdResponse>");
    	
        Source responsePayload = new StringSource(response.toString());
        validTestResults(requestPayload, responsePayload);
    }

    @Test
    public void searchByName() throws IOException {
        StringBuilder request = new StringBuilder()
									.append("<SearchStudentsRequest xmlns=\"http://webservice.api.sb/soap\">")
									.append("<name>Miguel</name>")
									.append("</SearchStudentsRequest>");
    	
    	Source requestPayload = new StringSource(request.toString());

        StringBuilder response = new StringBuilder()
									.append("<ns2:SearchStudentsResponse xmlns:ns2=\"http://webservice.api.sb/soap\">")
									.append(this.getStudentDetailsList(3,"Miguel", 789123))
									.append("</ns2:SearchStudentsResponse>");
    	
        Source responsePayload = new StringSource(response.toString());
        validTestResults(requestPayload, responsePayload);
    }

    @Test
    public void searchByPassportNumber() throws IOException {
        StringBuilder request = new StringBuilder()
									.append("<SearchStudentsRequest xmlns=\"http://webservice.api.sb/soap\">")
									.append("<passportNumber>123</passportNumber>")
									.append("</SearchStudentsRequest>");
    	
    	Source requestPayload = new StringSource(request.toString());

        StringBuilder response = new StringBuilder()
									.append("<ns2:SearchStudentsResponse xmlns:ns2=\"http://webservice.api.sb/soap\">")
									.append(this.getStudentDetailsList(1,"Marcel", 123456))
									.append(this.getStudentDetailsList(3,"Miguel", 789123))
									.append("</ns2:SearchStudentsResponse>");
    	
        Source responsePayload = new StringSource(response.toString());
        validTestResults(requestPayload, responsePayload);
    }

    @Test
    public void updateName() throws IOException {
        StringBuilder request = new StringBuilder()
									.append("<UpdateStudentNameRequest xmlns=\"http://webservice.api.sb/soap\">")
									.append("<id>2</id>")
									.append("<name>Paul</name>")
									.append("</UpdateStudentNameRequest>");
    	
    	Source requestPayload = new StringSource(request.toString());

        StringBuilder response = new StringBuilder()
									.append("<ns2:UpdateStudentNameResponse xmlns:ns2=\"http://webservice.api.sb/soap\">")
									.append(this.getStudentDetailsList(1,"Marcel", 123456))
									.append(this.getStudentDetailsList(2,"Paul", 456789))
									.append(this.getStudentDetailsList(3,"Miguel", 789123))
									.append("</ns2:UpdateStudentNameResponse>");
    	
        Source responsePayload = new StringSource(response.toString());
        validTestResults(requestPayload, responsePayload);
    }
    
    private void validTestResults(Source requestPayload, Source responsePayload) throws IOException {
        mockClient.sendRequest(withPayload(requestPayload))
			        .andExpect(noFault())
			        .andExpect(payload(responsePayload))
			        .andExpect(validPayload(xsdSchema));
    }
    
}
