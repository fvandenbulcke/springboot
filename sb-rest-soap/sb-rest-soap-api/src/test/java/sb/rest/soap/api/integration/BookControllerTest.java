package sb.rest.soap.api.integration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import sb.rest.soap.api.service.dto.Book;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {

	@LocalServerPort
	private int port;

	private TestRestTemplate restTemplate = new TestRestTemplate();
	private HttpHeaders headers = new HttpHeaders();

	@Test
	@Description("Should return all the books")
	public void getAll() {

		HttpEntity<List<Book>> entity = new HttpEntity<List<Book>>(null, headers);

		ResponseEntity<List> response = restTemplate.exchange(getURLWithPort("/api/v1/book"),HttpMethod.GET, entity, List.class);
		assertThat(response.getBody().size(), equalTo(0));
	}

	private String getURLWithPort(String servicePath) {
		return "http://localhost:".concat(String.valueOf(port)).concat(servicePath);
	}
	
}
