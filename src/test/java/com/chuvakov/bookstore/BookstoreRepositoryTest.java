
package com.chuvakov.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.chuvakov.bookstore.domain.Book;
import com.chuvakov.bookstore.domain.BookRepository;
import com.chuvakov.bookstore.domain.CategoryRepository;

import org.junit.jupiter.api.Test;

@DataJpaTest
public class BookstoreRepositoryTest {

	@Autowired
	private BookRepository repository;

	@Autowired
	private CategoryRepository catrepository;

	@Test
	public void findBookByTitle() {
		List<Book> books = repository.findByTitle("The Lord of the Rings");

		assertThat(books).hasSize(1);
		assertThat(books.get(0).getTitle()).isEqualTo("The Lord of the Rings");
	}

	@Test
	public void createNewBook() {
		Book book = new Book("War and Peace", "Leo Tolstoy", 1869, "0-321-45-3", 37.50,
				catrepository.findByName("Fiction").get(0));
		repository.save(book);
		assertThat(book.getId()).isNotNull();
	}

	@Test
	public void deleteNewBook() {
		List<Book> books = repository.findByTitle("The Lord of the Rings");
		Book book = books.get(0);
		repository.delete(book);
		List<Book> newStudents = repository.findByTitle("The Lord of the Rings");
		assertThat(newStudents).hasSize(0);
	}

}
