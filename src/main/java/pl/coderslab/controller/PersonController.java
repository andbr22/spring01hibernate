package pl.coderslab.controller;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dao.PersonDao;
import pl.coderslab.dao.PersonDetailDao;
import pl.coderslab.model.Person;
import pl.coderslab.model.PersonDetails;

@Controller
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonDao personDao;
    @Autowired
    PersonDetailDao personDetailDao;

//    @InitBinder("Person")
//    public void customizeBinding (WebDataBinder binder) {
//
//    }

    @ModelAttribute("countries")
    public String[] countries(){
        return new String[] {"Polska", "Szwecja", "Zimbabwe"};
    }

    @GetMapping("/create")
    public String createPersonForm(Model model){
        model.addAttribute("person", new Person());
        return "personForm";
    }

    @PostMapping("/create")
    @ResponseBody
    public String createPerson(@ModelAttribute Person person){
        personDao.create(person);
        return person.toString();
    }

    @GetMapping("/update/{id}")
    public String updatePersonForm(@PathVariable("id") long id, Model model){
        model.addAttribute("person", personDao.readById(id));
        return "personForm";
    }

    @PostMapping("/update/{id}")
    @ResponseBody
    public String updatePerson(@PathVariable("id") long id, @ModelAttribute Person person){
        personDao.update(person);
        return person.toString();
    }
}
