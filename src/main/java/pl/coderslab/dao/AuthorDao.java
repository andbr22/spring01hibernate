package pl.coderslab.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import pl.coderslab.model.Author;

import java.util.List;

@Component
@Transactional
public class AuthorDao {

	@PersistenceContext
	private EntityManager em;
	
	public void create(Author author){
		em.persist(author);
	}

	public Author readById(long id){
		return em.find(Author.class, id);
	}

	public List<Author> readAll(){
		Query query = em.createQuery("SELECT a from Author a");
		return query.getResultList();
	}

	public void update(Author author){
		em.merge(author);
	}

	public void deleteById(long id){
		em.remove(readById(id));
	}
}
