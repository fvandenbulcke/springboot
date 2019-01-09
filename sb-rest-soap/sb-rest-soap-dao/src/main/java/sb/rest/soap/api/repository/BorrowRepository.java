package sb.rest.soap.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import sb.rest.soap.api.repository.models.BorrowBo;

@Transactional
public interface BorrowRepository extends CrudRepository<BorrowBo, Integer> {

}
