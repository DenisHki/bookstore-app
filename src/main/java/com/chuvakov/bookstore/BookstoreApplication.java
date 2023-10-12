package com.chuvakov.bookstore;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.chuvakov.bookstore.domain.Book;
import com.chuvakov.bookstore.domain.BookRepository;
import com.chuvakov.bookstore.domain.Category;
import com.chuvakov.bookstore.domain.CategoryRepository;
import com.chuvakov.bookstore.domain.AppUser;
import com.chuvakov.bookstore.domain.AppUserRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner studentDemo(BookRepository repository, CategoryRepository catrepository, AppUserRepository urepository) {
		return (args) -> {

			catrepository.save(new Category("Fantasy"));
			catrepository.save(new Category("Fiction"));
			catrepository.save(new Category("Non-Fiction"));
			catrepository.save(new Category("Drama"));

			log.info("save few books");
			repository.save(new Book("The Lord of the Rings", "John Ronald Reuel Tolkien", 1954, "0-684-84328-5", 50.70,
					catrepository.findByName("Fantasy").get(0)));
			repository.save(new Book("A Game of Thrones", "George R. R. Martin", 1996, "85-359-0277-5", 30.50,
					catrepository.findByName("Fantasy").get(0)));
			repository.save(new Book("The Da Vinci Code", "Dan Brown", 2003, "	0-385-50420-9", 15.80,
					catrepository.findByName("Fiction").get(0)));

			
			log.info("Create users: admin/admin user/user");
			AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			
			AppUser user3 = new AppUser("denis", "$2a$12$nhMRwdPAFf2NdB3XMUSmWuDPzXkyIko5g8YWU//vRvIOpBua.efnO", "ADMIN");
			
			urepository.save(user1);
			urepository.save(user2);
			urepository.save(user3);
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
			
			log.info("fetch all users");
			for (AppUser user : urepository.findAll()) {
				log.info(user.toString());
			}

		};
	}
}