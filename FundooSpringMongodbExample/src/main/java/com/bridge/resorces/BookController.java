package com.bridge.resorces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridge.model.Book;
import com.bridge.reposetory.BookReposetory;

@RestController
public class BookController {
	@Autowired
	BookReposetory reposetory;
	
	@PostMapping("/addBook")
	public String saveBook(@RequestBody Book book) {
		reposetory.save(book);
		return "Added book id :: "+book.getId();
	}
	@PostMapping("/getAll")
	public List<Book> getAll(){
		
		return reposetory.findAll();
	}
}
