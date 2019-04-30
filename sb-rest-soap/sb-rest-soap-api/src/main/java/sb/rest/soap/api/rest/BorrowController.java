package sb.rest.soap.api.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import sb.api.webservice.rest.BorrowCreationDto;
import sb.rest.soap.api.service.BorrowService;
import sb.rest.soap.api.service.dto.Borrow;

@RestController(value="borrowController")
@RequestMapping(path="/api/v1")
public class BorrowController {

	private final Logger LOGGER = LoggerFactory.getLogger(BorrowController.class);
	
	@Autowired
	private BorrowService borrowService;
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET,path="/borrow", produces = "application/json")
	public @ResponseBody List<Borrow> getAll() {
		LOGGER.info("Web service call to get all borrows");
		return borrowService.getAll();
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST,path="/borrow", produces = "application/json")
	public @ResponseBody Borrow create(
			@RequestBody(required = true) BorrowCreationDto borrowCreationDto) {
		LOGGER.info("Web service call to create borrow");
		return borrowService.create(borrowCreationDto.getBookId(), borrowCreationDto.getStudentId());
	}
	
	
}
