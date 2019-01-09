package sb.rest.soap.api.repository.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="borrows")
public class BorrowBo {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
	private StudentBo student;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
	private BookBo book;

	@Column(name="start_date")
	private Date startDate;

	@Column(name="end_date")
	private Date endDate;
	
}
