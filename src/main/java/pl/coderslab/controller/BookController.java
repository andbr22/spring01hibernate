package pl.coderslab.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import pl.coderslab.dao.AuthorDao;
import pl.coderslab.dao.BookDao;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.model.Author;
import pl.coderslab.model.Book;
import pl.coderslab.model.Publisher;
import pl.coderslab.repository.AuthorRepository;
import pl.coderslab.repository.BookRepository;
import pl.coderslab.repository.PublisherRepository;
import pl.coderslab.validator.PropositionGroup;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookDao bookDao;
	@Autowired
	private PublisherDao publisherDao;
	@Autowired
	private AuthorDao authorDao;
	@Autowired
	Validator validator;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private PublisherRepository publisherRepository;
	@Autowired
	private AuthorRepository authorRepository;

	@ModelAttribute("publishers")
	public List<Publisher> getPublishers(){
		return publisherDao.readAll();
	}

	@ModelAttribute("authors")
	public List<Author> getAuthors(){
		return authorDao.readAll();
	}

	@GetMapping("/test")
	public String test(Model model){
		Author author = authorDao.readById(1);
		//List<Book> books = bookRepository.findAll();
		//List<Book> books = bookRepository.findBooksByPublisherName("pub 2");
		//List<Book> books = bookRepository.findBooksByAuthorsContaining(author);
		List<Book> books = bookRepository.findAllByPublisherNameOrderByTitleDesc("pub 2");
		model.addAttribute("books",books);
		return "bookAll";
	}

	@GetMapping("/saveProposition")
	public String ValidatePropositionForm(Model model){
		model.addAttribute("book", new Book());
		return "bookValidatorForm";
	}
	@PostMapping("/saveProposition")
	public String validateProposition(@Validated({PropositionGroup.class}) Book book, BindingResult result) {
		if(result.hasErrors()){
			return "bookValidatorForm";
		}

		bookDao.create(book);

		return "redirect:/book/read";
	}

	@GetMapping("/save")
	public String validateBookForm(Model model){
		model.addAttribute("book", new Book());
		return "bookValidatorForm";
	}

	@PostMapping("/save")
	public String validateBook(@Valid Book book, BindingResult result){

		Set<ConstraintViolation<Book>> violations = validator.validate(book, PropositionGroup.class);
		if(book.isProposition() && (!violations.isEmpty())){
			return "bookValidatorForm";
		}

		if(result.hasErrors()){
			return "bookValidatorForm";
		}
		bookDao.create(book);
		return "redirect:/book/read";
	}

	@GetMapping("/edit/{id}")
	public String validateEditForm(@PathVariable("id") long id, Model model){
		model.addAttribute("book", bookDao.readById(id));
		return "bookValidatorForm";
	}

	@PostMapping("/edit/{id}")
	public String validateEdit(@Valid Book book, BindingResult result){
		if(result.hasErrors()){
			return "bookValidatorForm";
		}
		bookDao.update(book);
		return "redirect:/book/read";
	}


	//STARE METODY
	@PostMapping("/create")
	public String createBook(HttpServletRequest request) {
		String title = request.getParameter("title");
		String[] ids = request.getParameterValues("author");
		String description = request.getParameter("description");
		String publisherId = request.getParameter("publisher");
		
		Book book = new Book();
		book.setTitle(title);
		book.setPublisher(publisherDao.readById(Long.valueOf(publisherId)));
		book.setDescription(description);

		for(String id:ids){
			Author author = authorDao.readById(Long.valueOf(id));
			book.addAuthor(author);
		}

		bookDao.create(book);
		
		return "redirect:/book/read";
	}
	
	@GetMapping("/create")
	public String createBookForm(Model model) {
		List<Publisher> publishers = publisherDao.readAll();
		List<Author> authors = authorDao.readAll();
		model.addAttribute("publishers",publishers);
		model.addAttribute("authors",authors);
		return "bookFormBind";
	}
	
	@GetMapping("/read/{id}")
	@ResponseBody
	public String viewBookById(@PathVariable("id") long id) {
		Book book = bookDao.readById(id);
		if (book == null) return "Brak książki";
 		return book.toString();
	}

	@GetMapping("/read")
	public String viewAllBooks(Model model){
		List<Book> books = bookDao.readAll();
		model.addAttribute("books",books);
		return "bookAll";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteBookById(@PathVariable("id") long id, Model model) {
		//bookDao.deleteById(id);
		model.addAttribute("book", bookDao.readById(id));
		return "confirmDelete";
	}

	@PostMapping("/delete/{id}")
	public String deleteConfirmed(@PathVariable("id") long id){
		bookDao.deleteById(id);
		return "redirect:/book/read";
	}
	
	@GetMapping("/update/{id}")
	public String updateBookForm(@PathVariable("id") long id, Model model) {
		Book book = bookDao.readById(id);
		model.addAttribute("book", book);

		List<Publisher> publishers = publisherDao.readAll();
		List<Author> authors = authorDao.readAll();
		model.addAttribute("publishers",publishers);
		model.addAttribute("authors",authors);

		return "bookFormBind";
	}
	
	@PostMapping("/update/{id}")
	public String updateBookForm(@PathVariable("id") long id, HttpServletRequest request) {
		Book book = bookDao.readById(id);
		
		String title = request.getParameter("title");
		String[] ids = request.getParameterValues("authors");
		String description = request.getParameter("description");
		String publisherId = request.getParameter("publisher");

		book.setTitle(title);
		book.setDescription(description);
		book.setPublisher(publisherDao.readById(Long.valueOf(publisherId)));

		book.clearAuthors();

		for(String aId:ids){
			Author author = authorDao.readById(Long.valueOf(aId));
			book.addAuthor(author);
		}

		bookDao.update(book);
		return "redirect:/book/read";
	}
}
