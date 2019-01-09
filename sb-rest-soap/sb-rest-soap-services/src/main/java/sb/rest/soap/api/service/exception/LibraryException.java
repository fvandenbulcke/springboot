package sb.rest.soap.api.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import sb.rest.soap.api.service.enums.Errors;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LibraryException extends Exception {
	private static final long serialVersionUID = 1L;
	
	
	public LibraryException(String message) {
		super(message);
	}
	
	public LibraryException(Errors error) {
		super(error.getValue());
	}
	
}
