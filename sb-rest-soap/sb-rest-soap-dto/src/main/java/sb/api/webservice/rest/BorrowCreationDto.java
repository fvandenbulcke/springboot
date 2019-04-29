package sb.api.webservice.rest;

import lombok.Data;

@Data
public class BorrowCreationDto {
	
	private Integer bookId;
	private Integer studentId;

}
