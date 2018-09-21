package sb.integration.java.dsl.configuration;

import java.io.IOException;

import javax.xml.transform.TransformerException;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.ws.MarshallingWebServiceOutboundGateway;
import org.springframework.integration.ws.SimpleWebServiceOutboundGateway;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.transport.context.TransportContext;
import org.springframework.ws.transport.context.TransportContextHolder;
import org.springframework.ws.transport.http.HttpUrlConnection;

@Configuration
@EnableConfigurationProperties(value = {AppProperties.class})
public class AppConfiguration {
	

    @Bean
    public Jaxb2Marshaller studentMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("sb.api.webservice.soap");
        return marshaller;
    }
    
    @Bean
    public RestTemplate bookWebServicesClient(AppProperties appProperties) {
    	RestTemplate template = new RestTemplate();
    	return template;
    }
	
	@Bean
	public MessageHandler studentWebServicesClient(AppProperties appProperties, Marshaller studentMarshaller) {
		return createMessageHandler(appProperties.getWebServiceUrl()+"/ws/v1/student", studentMarshaller);
	}
	
	private MessageHandler createMessageHandler(String urlService) {
        return new SimpleWebServiceOutboundGateway(urlService) {
        	@Override
        	protected Object doHandle(String uri, final Message<?> requestMessage, WebServiceMessageCallback requestCallback) {
        		return super.doHandle(uri, requestMessage, new WebServiceMessageCallback() {
					@Override
					public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException {
						TransportContext context = TransportContextHolder.getTransportContext();
						HttpUrlConnection connection = (HttpUrlConnection) context.getConnection();
						connection.addRequestHeader("Authorization",requestMessage.getHeaders().get("bearer").toString());
					}
				});
        	}
        };
	}

	private MessageHandler createMessageHandler(String urlService, Marshaller marshaller) {
		MarshallingWebServiceOutboundGateway gw = new MarshallingWebServiceOutboundGateway(urlService , marshaller) {
			@Override
			protected Object doHandle(String uri,final Message<?> requestMessage,WebServiceMessageCallback requestCallback) {
				return super.doHandle(uri, requestMessage, new WebServiceMessageCallback() {
					@Override
					public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException {
						TransportContext context = TransportContextHolder.getTransportContext();
						HttpUrlConnection connection = (HttpUrlConnection) context.getConnection();
						connection.addRequestHeader("Authorization",requestMessage.getHeaders().get("bearer").toString());
					}
				});
			}
		};
		return gw;
	}
}
