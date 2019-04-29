package sb.rest.soap.api.configuration;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import sb.rest.soap.api.mapper.BookMapper;
import sb.rest.soap.api.mapper.BookMapperImpl;
import sb.rest.soap.api.mapper.BorrowMapper;
import sb.rest.soap.api.mapper.BorrowMapperImpl;
import sb.rest.soap.api.mapper.StudentMapper;
import sb.rest.soap.api.mapper.StudentMapperImpl;

@EnableWs
@Configuration
public class WebServiceConfiguration extends WsConfigurerAdapter {
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/ws/*");
	}

	@Bean(name = "students")
	public DefaultWsdl11Definition defaultWsdlDefinition(XsdSchema studentsSchema) {
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		definition.setPortTypeName("StudentPort");
		definition.setTargetNamespace("http://api.soap.sb/students");
		definition.setLocationUri("/ws");
		definition.setSchema(studentsSchema);
		return definition;
	}

	@Bean
	public XsdSchema studentsSchema() {
		return new SimpleXsdSchema(new ClassPathResource("student-details.xsd"));
	}
	
	@Bean(name="apiBookMapper")
	public BookMapper bookMapper() {
		return new BookMapperImpl();
	}

	@Bean(name="apiStudentMapper")
	public StudentMapper studentMapper() {
		return new StudentMapperImpl();
	}

	@Bean(name="apiBorrowMapper")
	public BorrowMapper borrowMapper() {
		return new BorrowMapperImpl();
	}
}