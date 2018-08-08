package sb.rest.api.docker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sb.rest.api.docker.configuration.AppProperties;

@Service
public class MessageService {

	private final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);
	
	@Autowired
	private AppProperties properties;
	
	
	public String getMessage() {
		LOGGER.info("Service call to get message : {}", properties.getMessage());
		return properties.getMessage();
	}
	
}
