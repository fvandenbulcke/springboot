package sb.integration.java.dsl.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.router.HeaderValueRouter;
import org.springframework.messaging.MessageHandler;

import sb.integration.java.dsl.transformer.StudentTransformer;

@Configuration
@EnableIntegration
@IntegrationComponentScan("sb.integration.java.dsl")
public class AppIntegrationConfiguration {


	@Bean
	public IntegrationFlow getStudentByIdFlow(StudentTransformer transformer) {
		return IntegrationFlows.from("getStudentByIdChannel")
				.transform(transformer,"createGetStudentByIdRequest")
				.channel("studentExecutorChannel")
				.get();
	}
	
	@Bean
	public IntegrationFlow getStudentByIdWithRoutingFlow(StudentTransformer transformer) {
		return IntegrationFlows.from("getStudentByIdChannel")
				.transform(transformer,"createGetStudentByIdRequest")
				.channel("studentExecutorChannel")
				.get();
	}
	
	@Bean
	public IntegrationFlow searchStudentsFlow(StudentTransformer transformer) {
		return IntegrationFlows.from("searchStudentsChannel")
				.transform(transformer,"createSearchStudentRequest")
				.channel("studentExecutorChannel")
				.get();
	}
	
	
	
//	@Bean
//	public IntegrationFlow advancedSearchFlow(StudentTransformer transformer, final Jaxb2Marshaller studentMarshaller) {
//		return IntegrationFlows.from("advancedSearchChannel")
//				.transform(transformer, "createAdvancedSearchRequest")
//				.<SearchStudentMessage, String>transform((SearchStudentMessage message) -> {
//				    StringWriter writer = new StringWriter();
//				    Result result = new StreamResult(writer);
//				    SearchStudentsRequest request = new ObjectFactory().createSearchStudentsRequest();
//				    request.setName(message.getName());
//				    request.setPassportNumber(message.getPassportNumber());
//				    studentMarshaller.marshal(request, result);
//					return writer.toString();
//				})
//				.route(entityRouter())
//				.get();
//	}
	
	@Bean
	public HeaderValueRouter entityRouter() {
		HeaderValueRouter router = new HeaderValueRouter("entity");
		router.setIgnoreSendFailures(false);
		router.setChannelMapping("student", "studentExecutorChannel");
		router.setChannelMapping("book", "bookExecutorChannel");
		return router;
	}
	

	@Bean
	public IntegrationFlow studentExecutorFlow(StudentTransformer transformer, MessageHandler studentWebServicesClient) {
		return IntegrationFlows.from("studentExecutorChannel")
				.handle(studentWebServicesClient)
				.channel("asStudentChannel")
				.get();
	}

//	@Bean
//	public IntegrationFlow bookExecutorFlow(StudentTransformer transformer, MessageHandler studentWebServicesClient) {
//		return IntegrationFlows.from("bookExecutorChannel")
//				.handle(studentWebServicesClient)
//				.channel("asStudentChannel")
//				.get();
//	}
}
