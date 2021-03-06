package sb.rest.soap.api.service.enums;

public enum Errors {
	
	BOOK_NOT_EXIST("Wanted book doasn't exist"),
	BOOK_RETURN_WITH_DELAY("Book is return with too long delay"),
	BORROW_NOT_EXIST("Wanted borrow doasn't exist"),
	STUDENT_NOT_EXIST("Wanted student doasn't exist");
	
	private final String value;

	private Errors(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
