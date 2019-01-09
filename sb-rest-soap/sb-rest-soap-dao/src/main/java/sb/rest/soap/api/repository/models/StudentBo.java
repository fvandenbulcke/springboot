package sb.rest.soap.api.repository.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

	public StudentBo() {}

	public StudentBo(Integer id, String firstName, String name, String level, Date creationDate) {
		this.id = id;
		this.firstName = firstName;
		this.name = name;
		this.level = level;
		this.creationDate = creationDate;
	}
	
}
