package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.model.Author;
import pl.coderslab.model.Publisher;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Controller
@RequestMapping("/publisher")
public class PublisherController {
    @Autowired
    PublisherDao publisherDao;
    @Autowired
    Validator validator;

    @GetMapping("/create")
    public String createPublisherForm(){
        return "publisherForm";
    }

    @PostMapping("/create")
    public String createPublisher(HttpServletRequest request){
        Publisher publisher = new Publisher();
        publisher.setName(request.getParameter("name"));

        Set<ConstraintViolation<Publisher>> violations = validator.validate(publisher);
        if(!violations.isEmpty()){

            return"";
        }
        publisherDao.create(publisher);
        return publisher.toString();
    }

    @GetMapping("/read/{id}")
    @ResponseBody
    public String readPublisherById(@PathVariable("id") long id){
        Publisher publisher = publisherDao.readById(id);
        if(publisher==null){
            return "brak elementu";
        }
        return publisher.toString();
    }

    @GetMapping("/update/{id}")
    public String updatePublisherForm(@PathVariable("id") long id, Model model){
        Publisher publisher = publisherDao.readById(id);
        model.addAttribute("publisher", publisher);
        return "publisherForm";
    }

    @PostMapping("/update/{id}")
    @ResponseBody
    public String updatePublisher(@PathVariable("id") long id, HttpServletRequest request) {
        Publisher publisher = publisherDao.readById(id);
        publisher.setName(request.getParameter("name"));
        publisherDao.update(publisher);
        return publisher.toString();
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public String deletePublisherById(@PathVariable("id")long id){
        publisherDao.deleteById(id);
        return "usunieto";
    }
}
