package com.bukkeubook.book.books.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bukkeubook.book.books.model.dto.BookDTO;
import com.bukkeubook.book.books.model.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {
	
	private final BookService bookService;
	
	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping("/lookupList")
	public ModelAndView findBookList(ModelAndView mv) {

		List<BookDTO> bookList = bookService.findBookList();
		System.out.println("dd  " + bookList);
		mv.addObject("bookList", bookList);
		mv.setViewName("books/bookList/lookupList");
		
		return mv;
	}
	
}
