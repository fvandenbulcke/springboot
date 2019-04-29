package sb.rest.soap.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import sb.api.webservice.rest.BorrowDto;
import sb.rest.soap.api.service.dto.Borrow;

@Mapper
public interface BorrowMapper {
	
	BorrowDto asBorrow(Borrow in);
	List<BorrowDto> asBorrowList(List<Borrow> in);

}
