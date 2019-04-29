package sb.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class JsonTest {
	
	@Test
	public void getAll() throws JsonProcessingException {
		
	    User user = new User(1, "John");
	    Item item = new Item(2, "book", user);
	    user.addItem(item);
	 
	    System.out.println(new ObjectMapper().writeValueAsString(item));
		
	}

}
