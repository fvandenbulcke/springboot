package sb.rest.soap.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import sb.rest.soap.api.repository.models.BookBo;

@Transactional
public interface BookRepository extends CrudRepository<BookBo, Integer> {

}
