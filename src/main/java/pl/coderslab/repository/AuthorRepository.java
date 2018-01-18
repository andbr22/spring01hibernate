package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.model.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author,Long>{
    Author findAuthorByPesel(String pesel);
    Author findAuthorByEmail(String email);
    List<Author> findAuthorsByLastName(String lastName);
}
