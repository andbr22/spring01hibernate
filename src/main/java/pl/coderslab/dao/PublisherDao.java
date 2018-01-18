package pl.coderslab.dao;

import org.springframework.stereotype.Component;
import pl.coderslab.model.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class PublisherDao {
    @PersistenceContext
    private EntityManager em;

    public void create(Publisher publisher){
        em.persist(publisher);
    }

    public Publisher readById(long id){
        return em.find(Publisher.class, id);
    }

    public List<Publisher> readAll(){
        Query query = em.createQuery("SELECT p from Publisher p");
        return query.getResultList();
    }

    public void update(Publisher publisher){
        em.merge(publisher);
    }

    public void deleteById(long id){
        em.remove(readById(id));
    }
}
