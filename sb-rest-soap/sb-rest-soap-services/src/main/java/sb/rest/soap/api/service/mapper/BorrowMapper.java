package sb.rest.soap.api.service.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import sb.rest.soap.api.repository.models.BorrowBo;
import sb.rest.soap.api.service.dto.Borrow;

@Mapper
public interface BorrowMapper {

	@IterableMapping(qualifiedByName="defaultBorrowMapper")
	List<Borrow> asBorrowList(List<BorrowBo> in);

	@Named("defaultBorrowMapper")
	@Mapping(target="book.borrows", ignore=true)
	@Mapping(target="student.borrows", ignore=true)
	Borrow asBorrow(BorrowBo in);
	
}
