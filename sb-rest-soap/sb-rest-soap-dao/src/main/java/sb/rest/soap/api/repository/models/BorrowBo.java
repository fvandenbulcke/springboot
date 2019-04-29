package sb.rest.soap.api.repository.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name="borrows")
public class BorrowBo {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	

	@ManyToOne
    @JoinColumn(name = "student_id")
	@JsonBackReference
	private StudentBo student;

    @ManyToOne
    @JoinColumn(name = "book_id")
	@JsonBackReference
	private BookBo book;

	@Column(name="start_date")
	private Date startDate;

	@Column(name="end_date")
	private Date endDate;

	public BorrowBo() {}
	

	public BorrowBo(Integer id, StudentBo student, BookBo book, Date startDate, Date endDate) {
		this.id = id;
		this.student = student;
		this.book = book;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
}
