package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.model.Book;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;


@Controller
@RequestMapping("/validator")
public class ValidatorController {
    @Autowired
    private Validator validator;

    @GetMapping("/validate")
    public String validate(Model model){
        Book book = new Book();

        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        model.addAttribute("violations", violations);

        return "validateErrors";
    }
}
