package sb.rest.api.docker.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path="/api")
public class RestApiController {

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET,path="/hello", produces = "application/json")
	public @ResponseBody String getMessage() {
		return "Hello!! springboot rest api docker";
	}
	
}
