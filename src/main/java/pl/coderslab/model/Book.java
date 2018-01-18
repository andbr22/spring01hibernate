package pl.coderslab.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name	= "books")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	@Size(min = 5)
	@NotNull
	private String title;
	@Column(scale = 2, precision = 4)
	//@Range(min=1,max = 10)
	//@NotNull
    private Double rating;
    @Column(columnDefinition = "TEXT")
	//@Size(max=600)
	//@NotNull
    private String description;
	@ManyToOne
	@JoinColumn(name="publisher_id")
	@NotNull
	private Publisher publisher;
	@ManyToMany
	@NotEmpty
	private List<Author> authors = new ArrayList<>();

	//@Min(value=2)
	private int pages;

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public Book() {
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public void addAuthor(Author author){
    	authors.add(author);
	}

	public void clearAuthors(){
		authors = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "Book{" +
				"id=" + id +
				", title='" + title + '\'' +
				", rating=" + rating +
				", description='" + description + '\'' +
				", publisher=" + publisher +
				", authors=" + authors +
				'}';
	}
}
