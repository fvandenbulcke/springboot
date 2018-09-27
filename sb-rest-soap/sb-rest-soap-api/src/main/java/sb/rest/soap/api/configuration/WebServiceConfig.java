package sb.rest.soap.api.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;

import sb.rest.soap.api.MainClassApplication;

@EnableWs
@Configuration
@ComponentScan(basePackageClasses = MainClassApplication.class)
@EnableConfigurationProperties(value = { AppProperties.class })
public class WebServiceConfig extends WsConfigurerAdapter {
//	@Bean
//	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
//		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
//		servlet.setApplicationContext(applicationContext);
//		servlet.setTransformWsdlLocations(true);
//		return new ServletRegistrationBean(servlet, "/ws/*");
//	}
//
//	@Bean(name = "students")
//	public DefaultWsdl11Definition defaultWsdlDefinition(XsdSchema studentsSchema) {
//		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
//		definition.setPortTypeName("StudentPort");
//		definition.setTargetNamespace("http://api.soap.sb/students");
//		definition.setLocationUri("/ws");
//		definition.setSchema(studentsSchema);
//		return definition;
//	}
//
//	@Bean
//	public XsdSchema studentsSchema() {
//		return new SimpleXsdSchema(new ClassPathResource("student-details.xsd"));
//	}

//	@Bean
//	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
//		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
//		servlet.setApplicationContext(applicationContext);
//		return new ServletRegistrationBean(servlet, "/codenotfound/ws/*");
//	}
//
	@Bean(name = "helloworld")
	public Wsdl11Definition defaultWsdl11Definition() {
		SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
		wsdl11Definition.setWsdl(new ClassPathResource("hello-world.wsdl"));
		return wsdl11Definition;
	}

	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		return new ServletRegistrationBean(servlet, "/ws/*");
	}

	@Bean(name = "students")
	public Wsdl11Definition studentsWsdl11Definition() {
		SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
		wsdl11Definition.setWsdl(new ClassPathResource("students.wsdl"));
		return wsdl11Definition;
	}

}