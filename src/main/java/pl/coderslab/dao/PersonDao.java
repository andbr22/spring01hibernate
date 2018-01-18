package pl.coderslab.dao;

import org.springframework.stereotype.Component;
import pl.coderslab.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
@Transactional
public class PersonDao {
    @PersistenceContext
    EntityManager em;

    public void create(Person person){
        em.persist(person);
    }

    public Person readById(long id){
        return em.find(Person.class, id);
    }

    public void update(Person person){
        em.merge(person);
    }

    public void deleteById(long id){
        em.remove(readById(id));
    }
}
