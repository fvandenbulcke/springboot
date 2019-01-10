package sb.rest.soap.api.service.enums;

public enum Errors {
	
	BOOK_NOT_EXIST("Wanted book doasn't exist"),
	STUDENT_NOT_EXIST("Wanted student doasn't exist");
	
	private final String value;

	private Errors(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
