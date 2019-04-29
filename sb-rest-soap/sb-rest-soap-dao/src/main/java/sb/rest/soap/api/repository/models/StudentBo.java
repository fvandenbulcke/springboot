package sb.rest.soap.api.repository.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name="students")
public class StudentBo {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="first_name")
	private String firstName;

	@Column(name="name")
	private String name;

	@Column(name="class_level")
	private String level;

	@Column(name="creation_date")
	private Date creationDate;

	@JsonManagedReference
    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
	private List<BorrowBo> borrows;
	
	public StudentBo() {}

	public StudentBo(Integer id, String firstName, String name, String level, Date creationDate, List<BorrowBo> borrows) {
		this.id = id;
		this.firstName = firstName;
		this.name = name;
		this.level = level;
		this.creationDate = creationDate;
		this.borrows = borrows;
	}
	
}
