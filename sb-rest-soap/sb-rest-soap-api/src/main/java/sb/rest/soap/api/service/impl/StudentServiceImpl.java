package sb.rest.soap.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import sb.rest.soap.api.service.IStudentService;
import sb.rest.soap.api.service.dto.Student;

@Service
public class StudentServiceImpl implements IStudentService {

	private List<Student> students;
	
	@PostConstruct
	private void initBooks() {
		students = new ArrayList<Student>();
		students.add(new Student(1,"Marcel","123456"));
		students.add(new Student(2,"James","456789"));
		students.add(new Student(3,"Miguel","789123"));
	}

//	@Override
//	public List<Student> search(SearchStudentsRequest request) {
//		return students.stream()
//				.filter((s) -> 
//					s.getName().equals(request.getName())
//					|| request.getPassportNumber()!=null && s.getPassportNumber().contains(request.getPassportNumber()))
//				.collect(Collectors.toList());
//	}

	@Override
	public Student getById(Integer id) {
		return students.stream()
				.filter((s) -> id.equals(s.getId()))
				.findAny()
				.orElse(null);
	}

	@Override
	public List<Student> create(String name, String passportNumber) {
		Integer newId = students.size()+1;
		Student newStudent = new Student(newId,name, passportNumber);
		students.add(newStudent);
		return students;
	}

	@Override
	public List<Student> updateName(Integer id, String newName) {
		Student currentStudent = students.stream()
				.filter((s) -> id.equals(s.getId()))
				.findAny()
				.orElse(null);
		currentStudent.setName(newName);
		return students;
	}

	@Override
	public List<Student> delete(Integer id) {
		students = students.stream()
				.filter((s) -> s.getId()!=id)
				.map((s) -> {
					return s.getId()<id ? s : new Student(s.getId()-1,s.getName(),s.getPassportNumber());
				})
				.collect(Collectors.toList());
		return students;
	}

}
