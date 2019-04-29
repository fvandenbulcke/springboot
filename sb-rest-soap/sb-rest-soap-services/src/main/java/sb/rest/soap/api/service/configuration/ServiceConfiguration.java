package sb.rest.soap.api.service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sb.rest.soap.api.service.mapper.BookMapper;
import sb.rest.soap.api.service.mapper.BookMapperImpl;
import sb.rest.soap.api.service.mapper.BorrowMapper;
import sb.rest.soap.api.service.mapper.BorrowMapperImpl;
import sb.rest.soap.api.service.mapper.StudentMapper;
import sb.rest.soap.api.service.mapper.StudentMapperImpl;

@Configuration
public class ServiceConfiguration {
	
	@Bean(name="serviceBookMapper")
	public BookMapper bookMapper() {
		return new BookMapperImpl();
	}
	
	@Bean(name="serviceStudentMapper")
	public StudentMapper studentMapper() {
		return new StudentMapperImpl();
	}
	
	@Bean(name="serviceBorrowMapper")
	public BorrowMapper borrowMapper() {
		return new BorrowMapperImpl();
	}

}
