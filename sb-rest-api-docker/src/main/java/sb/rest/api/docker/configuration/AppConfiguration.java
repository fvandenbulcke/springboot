package sb.rest.api.docker.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import sb.rest.api.docker.MainClassApplication;

@Configuration
@ComponentScan(basePackageClasses=MainClassApplication.class)
@EnableConfigurationProperties(value = {AppProperties.class})
public class AppConfiguration {
	
}
