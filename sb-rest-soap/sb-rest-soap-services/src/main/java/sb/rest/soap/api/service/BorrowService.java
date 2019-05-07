package sb.rest.soap.api.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sb.rest.soap.api.repository.BorrowRepository;
import sb.rest.soap.api.repository.models.BookBo;
import sb.rest.soap.api.repository.models.BorrowBo;
import sb.rest.soap.api.repository.models.StudentBo;
import sb.rest.soap.api.service.dto.Borrow;
import sb.rest.soap.api.service.enums.Errors;
import sb.rest.soap.api.service.exception.LibraryException;
import sb.rest.soap.api.service.mapper.BorrowMapper;

@Service
public class BorrowService {

	@Autowired
	private BorrowRepository borrowRepository;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private BorrowMapper mapper;

	private final Logger LOGGER = LoggerFactory.getLogger(BorrowService.class);

	public List<Borrow> getAll() {
		List<BorrowBo> borrows = StreamSupport
								.stream(borrowRepository.findAll().spliterator(), false)
								.collect(Collectors.toList());
		return mapper.asBorrowList(borrows);
	}
	
	public Borrow getById(Integer id) throws LibraryException {
		BorrowBo borrow = this.getBorrowBoById(id);
		return mapper.asBorrow(borrow);
	}
	
	public BorrowBo getBorrowBoById(Integer id) throws LibraryException {
		LOGGER.info("Get book by id {}", id);
		Optional<BorrowBo> optional = borrowRepository.findById(id);
		if (!optional.isPresent()) {
			throw new LibraryException(Errors.BOOK_NOT_EXIST);
		}
		return optional.get();
	}
	
	public Borrow create(Integer bookId, Integer studentId) throws LibraryException {
		StudentBo student = studentService.getStudentById(studentId);
		BookBo book = bookService.getBookById(bookId);
		
		BorrowBo borrow = BorrowBo.builder()
				.book(book)
				.student(student)
				.startDate(new Date())
				.build();
		borrow = borrowRepository.save(borrow);
		
		return mapper.asBorrow(borrow);
	}
	
	public Borrow returnBorrow(Integer borrowId) throws LibraryException {
		Date today = new Date();
		
		BorrowBo borrow = this.getBorrowBoById(borrowId);
		
        Calendar expectedEndDate = Calendar.getInstance();
        expectedEndDate.setTime(borrow.getStartDate());
		expectedEndDate.add(Calendar.WEEK_OF_YEAR, 2);
		
		borrow.setEndDate(today);
		borrow = borrowRepository.save(borrow);
		
		if (today.after(expectedEndDate.getTime())) {
			throw new LibraryException(Errors.BOOK_RETURN_WITH_DELAY);
		}
		return mapper.asBorrow(borrow);
	}



}
