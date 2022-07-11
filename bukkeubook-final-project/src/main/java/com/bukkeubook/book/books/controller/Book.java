package com.bukkeubook.book.books.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Book {
	
	@GetMapping(value= "/lookupList")
	public String main() {
		return "books/bookList/LookupList";
	}
}
