package pl.coderslab.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import pl.coderslab.dao.BookDao;
import pl.coderslab.model.Book;

public class SpringDiApplication {

	public static void main(String[] args) {
		
		//zad1();
		zad2();
	}

	public static void zad1() {
		ApplicationContext context  = new AnnotationConfigApplicationContext(AppConfig.class);
		BookDao bookDao = context.getBean(BookDao.class);
		Book book = new Book();
		book.setTitle("tytul");
		//book.setAuthor("autor");
		//book.setPublisher("wydawnictwo");
		
		bookDao.create(book);
	}
	
	public static void zad2() {
		ApplicationContext context  = new AnnotationConfigApplicationContext(AppConfig.class);
		BookDao bookDao = context.getBean(BookDao.class);
		Book book = bookDao.readById(1);
		book.setDescription("opis");
		System.out.println(book);
		bookDao.update(book);
		
		Book book2 = bookDao.readById(1);
		System.out.println(book2);
		
		bookDao.deleteById(1);
		
		Book book3 = bookDao.readById(1);
		System.out.println(book3);
	}
}
