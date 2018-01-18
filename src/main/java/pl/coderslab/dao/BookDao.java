package pl.coderslab.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import pl.coderslab.model.Book;

import java.util.List;

@Component
@Transactional
public class BookDao {
    @PersistenceContext
    private EntityManager em;

    public void create(Book book) {
        em.persist(book);
    }

    public void update(Book book) {
        em.merge(book);
    }

    public Book readById(long id) {
        return em.find(Book.class, id);
    }

    public List<Book> readAll(){
        Query query = em.createQuery("SELECT b from Book b order by title");
        return query.getResultList();
    }

    public void deleteById(long id) {
        em.remove(readById(id));
    }
}
