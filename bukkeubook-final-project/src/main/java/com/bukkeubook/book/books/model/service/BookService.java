package com.bukkeubook.book.books.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bukkeubook.book.books.model.dto.BookDTO;
import com.bukkeubook.book.books.model.entity.Book;
import com.bukkeubook.book.books.model.repository.BookRepository;

@Service
public class BookService {
	
	private final BookRepository bookRepository;
	private final ModelMapper modelMapper;			// modelMapper 빈을 선언
	
	@Autowired
	public BookService(BookRepository bookRepository, ModelMapper modelMapper) {
		this.bookRepository = bookRepository;
		this.modelMapper = modelMapper;
	}
	
	public List<BookDTO> findBookList() {
		
		List<Book> bookList = bookRepository.findAll(Sort.by("no"));
		return bookList.stream().map(book -> modelMapper.map(book, BookDTO.class)).toList();
	}
}
