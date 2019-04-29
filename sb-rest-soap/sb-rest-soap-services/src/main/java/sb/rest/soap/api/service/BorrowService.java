package sb.rest.soap.api.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sb.rest.soap.api.repository.BookRepository;
import sb.rest.soap.api.repository.BorrowRepository;
import sb.rest.soap.api.repository.StudentRepository;
import sb.rest.soap.api.repository.models.BookBo;
import sb.rest.soap.api.repository.models.BorrowBo;
import sb.rest.soap.api.repository.models.StudentBo;
import sb.rest.soap.api.service.dto.Borrow;
import sb.rest.soap.api.service.mapper.BorrowMapper;

@Service
public class BorrowService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BorrowRepository borrowRepository;
	
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private BorrowMapper mapper;
	

	public List<Borrow> getAll() {
		List<BorrowBo> borrows = StreamSupport
								.stream(borrowRepository.findAll().spliterator(), false)
								.collect(Collectors.toList());
		return mapper.asBorrowList(borrows);
	}
	
	public Borrow create(Integer bookId, Integer studentId) {
		StudentBo student = studentRepository.findById(studentId).get();
		BookBo book = bookRepository.findById(bookId).get();

//		BorrowBo borrow = BorrowBo.builder()
//							.book(book)
//							.student(student)
//							.startDate(new Date())
//							.endDate(new Date())
//							.build();
		BorrowBo borrow = BorrowBo.builder()
				.book(book)
				.startDate(new Date())
				.endDate(new Date())
				.build();
		borrow = borrowRepository.save(borrow);
		
		return mapper.asBorrow(borrow);
	}



}
