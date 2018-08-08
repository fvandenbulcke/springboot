package sb.rest.api.docker.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {AppProperties.class})
public class AppConfiguration {
	
}
