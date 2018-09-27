package sb.rest.soap.api.integration;

import org.stringtemplate.v4.ST;

public class StudentDetailsEndpointTestUtils {
	
    protected String getStudentDetails(Integer id, String name, Integer passportNumber) {
        return this.getStudent("<ns2:StudentDetails>","</ns2:StudentDetails>",id,name, passportNumber);
    }

    protected String getStudentDetailsList(Integer id, String name, Integer passportNumber) {
        return this.getStudent("<ns2:StudentDetailList>","</ns2:StudentDetailList>",id,name, passportNumber);
    }
    
    private String getStudent(String openingTag, String closingTag, Integer id, String name, Integer passportNumber) {
        StringBuilder templateBuilder = new StringBuilder()
											.append(openingTag)
											.append("<ns2:id>$id$</ns2:id>")
											.append("<ns2:name>$name$</ns2:name>")
											.append("<ns2:passportNumber>$passportNumber$</ns2:passportNumber>")
											.append(closingTag);
    	ST template = new ST(templateBuilder.toString(),'$','$')
							.add("id", id)
							.add("name", name)
							.add("passportNumber", passportNumber);
        return template.render();
    }
	
}
