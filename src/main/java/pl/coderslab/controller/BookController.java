package pl.coderslab.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import pl.coderslab.dao.AuthorDao;
import pl.coderslab.dao.BookDao;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.model.Author;
import pl.coderslab.model.Book;
import pl.coderslab.model.Publisher;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookDao bookDao;
	@Autowired
	private PublisherDao publisherDao;
	@Autowired
	private AuthorDao authorDao;

	@ModelAttribute("publishers")
	public List<Publisher> getPublishers(){
		return publisherDao.readAll();
	}

	@ModelAttribute("authors")
	public List<Author> getAuthors(){
		return authorDao.readAll();
	}

	@GetMapping("/test")
	@ResponseBody
	public String testBook(){
		return "Test";
	}

	@GetMapping("/save")
	public String validateBookForm(Model model){
		model.addAttribute("book", new Book());
		return "bookValidatorForm";
	}

	@PostMapping("/save")
	public String validateBook(@Valid Book book, BindingResult result){
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
