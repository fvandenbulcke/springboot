package sb.rest.soap.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import sb.rest.soap.api.repository.models.StudentBo;

@Transactional
public interface StudentRepository extends CrudRepository<StudentBo, Integer> {
	
	List<StudentBo> findByName(String name);
	
	List<StudentBo> findByFirstName(String firstName);

}
