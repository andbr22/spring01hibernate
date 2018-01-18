package pl.coderslab.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import pl.coderslab.model.Person;
import pl.coderslab.model.PersonDetails;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
@Transactional
public class PersonDetailDao {
    @PersistenceContext
    EntityManager em;

    public void create(PersonDetails personDetails){
        em.persist(personDetails);
    }

    public  PersonDetails readPersonDetailsById(long id){
        return em.find(PersonDetails.class, id);
    }

    public void updatePersonDatails(PersonDetails personDetails){
        em.merge(personDetails);
    }

    public void deletePersonDetailsById(long id){
        em.remove(readPersonDetailsById(id));
    }
}
