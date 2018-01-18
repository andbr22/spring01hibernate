package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.model.Author;
import pl.coderslab.model.Book;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorDao authorDao;
    @Autowired
    Validator validator;

    @GetMapping("/read/{id}")
    @ResponseBody
    public String viewAuthorById(@PathVariable("id") long id){
        Author author = authorDao.readById(id);
        if(author == null){
            return "brak elementu";
        }
        return author.toString();
    }

    @GetMapping("/read")
    public String readAuthorAll(Model model){
        List<Author> authors = authorDao.readAll();
        model.addAttribute("authors",authors);
        return "authorAll";
    }

    @GetMapping("/create")
    public String createAuthorForm(){
        return "authorForm";
    }

    @PostMapping("/create")
    public String createAuthor(HttpServletRequest request, Model model){
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String pesel = request.getParameter("pesel");
        String yearOfBirth = request.getParameter("yearOfBirth");
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setPesel(pesel);
        author.setEmail(email);
        author.setYearOfBirth(yearOfBirth);
        Set<ConstraintViolation<Author>> violations = validator.validate(author);
        model.addAttribute("author",author);
        if(!violations.isEmpty()) {
            model.addAttribute("violations", violations);
            return "authorForm";
        }

        authorDao.create(author);
        return "redirect:/author/read";
    }

    @GetMapping("/update/{id}")
    public String updateAuthorForm(@PathVariable("id") long id, Model model){
        Author author = authorDao.readById(id);
        model.addAttribute("author",author);
        return "authorForm";
    }

    @PostMapping("/update/{id}")
    @ResponseBody
    public String updateAuthor(@PathVariable("id") long id, HttpServletRequest request){
        Author author = authorDao.readById(id);
        author.setFirstName(request.getParameter("firstName"));
        author.setLastName(request.getParameter("lastName"));

        authorDao.update(author);
        return author.toString();
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public String deleteAuthor(@PathVariable("id") long id){
        authorDao.deleteById(id);
        return "usunieto";
    }
}
