package com.chuvakov.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.chuvakov.bookstore.domain.Book;
import com.chuvakov.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner studentDemo(BookRepository repository) {
		return (args) -> {
			Book book1 = new Book("The Lord of the Rings", "John Ronald Reuel Tolkien", 1954, "0-684-84328-5", 50.70);
			Book book2 = new Book("A Game of Thrones", "George R. R. Martin", 1996, "85-359-0277-5", 30.50);
			Book book3 = new Book("The Da Vinci Code", "Dan Brown", 2003, "	0-385-50420-9", 15.80);

			log.info("save few books");
			repository.save(book1);
			repository.save(book2);
			repository.save(book3);

			System.out.println("IN COMMAND LINE RUNNER");

			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
