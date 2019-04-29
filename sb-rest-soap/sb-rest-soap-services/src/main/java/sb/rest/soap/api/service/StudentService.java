package sb.rest.soap.api.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sb.rest.soap.api.repository.StudentRepository;
import sb.rest.soap.api.repository.models.StudentBo;
import sb.rest.soap.api.service.dto.SearchStudents;
import sb.rest.soap.api.service.dto.Student;
import sb.rest.soap.api.service.enums.Errors;
import sb.rest.soap.api.service.enums.LibraryConstants;
import sb.rest.soap.api.service.exception.LibraryException;
import sb.rest.soap.api.service.mapper.StudentMapper;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private StudentMapper mapper;

	private final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);
	

	public List<Student> getAll() {
		LOGGER.info("Get all students");
		List<StudentBo> students = StreamSupport
				.stream(studentRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return mapper.asStudentList(students);
	}

	public List<Student> search(SearchStudents request) {
		List<StudentBo> students = null;
		if (LibraryConstants.NAME.name().equals(request.getCriteria())) {
			LOGGER.info("Search students by name {}", request.getValue());
			students = studentRepository.findByName(request.getValue());
		}
		else if (LibraryConstants.FIRST_NAME.name().equals(request.getCriteria())) {
			LOGGER.info("Search students by first name {}", request.getValue());
			students = studentRepository.findByFirstName(request.getValue());
		}
		return mapper.asStudentList(students);
	}

	public Student getById(Integer id) {
		LOGGER.info("Get student by id {}", id);
		Optional<StudentBo> optional = studentRepository.findById(id);
		StudentBo student = null;
		if (optional.isPresent()) {
			student = optional.get();
		}
		return mapper.asStudent(student);
	}

	public List<Student> create(String firstName,String name,String level) {
		LOGGER.info("Create student : {} - {} - {}", firstName, name, level);
		StudentBo newStudent = StudentBo.builder()
										.firstName(firstName)
										.name(name)
										.level(level)
										.creationDate(new Date())
										.build();
		studentRepository.save(newStudent);
		return this.getAll();
	}

	public List<Student> updateName(Integer id, String newName) throws LibraryException {
		LOGGER.info("Update student {} with name {}", id, newName);
		Optional<StudentBo> optional = studentRepository.findById(id);
		StudentBo student = null;
		if (optional.isPresent()) {
			student = optional.get();
		} else {
			throw new LibraryException(Errors.STUDENT_NOT_EXIST);
		}
		student.setName(newName);
		studentRepository.save(student);
		return this.getAll();
	}

	public List<Student> delete(Integer id) {
		LOGGER.info("Delete student {}", id);
		studentRepository.deleteById(id);
		return this.getAll();
	}


}
