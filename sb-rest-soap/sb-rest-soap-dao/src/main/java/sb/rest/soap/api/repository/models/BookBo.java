package sb.rest.soap.api.repository.models;

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
@Table(name="books")
public class BookBo {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="title")
	private String title;

	@Column(name="year")
	private Integer year;

	@Column(name="isbn")
	private Integer isbn;

	@Column(name="author")
	private String author;

	@Column(name="editor")
	private String editor;

	public BookBo() {}

	public BookBo(Integer id, String title, Integer year, Integer isbn, String author, String editor) {
		this.id = id;
		this.title = title;
		this.year = year;
		this.isbn = isbn;
		this.author = author;
		this.editor = editor;
	}
	
}
