package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.model.Author;
import pl.coderslab.model.Book;
import pl.coderslab.model.Publisher;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long>{

    List<Book> findBooksByTitle(String title);
    List<Book> findBooksByRatingGreaterThanEqual(Double d);
    List<Book> findBooksByPublisherName(String publisherName);

    List<Book> findBooksByPublisher(Publisher publisher);
    List<Book> findBooksByAuthorsContaining(Author author);
    List<Book> findAllByPublisherNameOrderByTitleDesc(String publisherName);
}
