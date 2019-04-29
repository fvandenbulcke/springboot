package sb.rest.soap.api.repository.models;

import java.math.BigInteger;
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
	private BigInteger isbn;

	@Column(name="author")
	private String author;

	@Column(name="editor")
	private String editor;
	
	// https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion
	// @JsonIgnoreProperties(value={"book","student"})
	// @JsonBackReference
	@JsonManagedReference
    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
	private List<BorrowBo> borrows;

	public BookBo() {}

	public BookBo(Integer id, String title, Integer year, BigInteger isbn, String author, String editor,List<BorrowBo> borrows) {
		this.id = id;
		this.title = title;
		this.year = year;
		this.isbn = isbn;
		this.author = author;
		this.editor = editor;
		this.borrows = borrows;
	}
	
}
