package sb.integration.java.dsl.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;


public class BookExecutor {
	
	@Autowired
	private RestTemplate bookWebServicesClient;
	

}
