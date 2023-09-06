package com.chuvakov.bookstore.web;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.chuvakov.bookstore.domen.Book;

@Controller
public class BookController {

	@GetMapping("/index")
	public String index(Model model) {
		
		ArrayList<Book> books = new ArrayList<Book>();
		
		Book book1 = new Book("The Lord of the Rings", "John Ronald Reuel Tolkien", 1954, "0-684-84328-5", 50.70);
		Book book2 = new Book("A Game of Thrones", "George R. R. Martin", 1996, "85-359-0277-5", 30.50);
		Book book3 = new Book("The Da Vinci Code", "Dan Brown", 2003, "	0-385-50420-9", 15.80);
		
		books.add(book1);
		books.add(book2);
		books.add(book3);
		
		model.addAttribute("booklist", books);
		
		return "hello";
	}
}
