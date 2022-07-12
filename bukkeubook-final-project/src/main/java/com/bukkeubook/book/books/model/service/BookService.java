package com.bukkeubook.book.books.model.service;

import java.awt.Menu;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bukkeubook.book.books.model.dto.BookDTO;
import com.bukkeubook.book.books.model.entity.Book;
import com.bukkeubook.book.books.model.repository.BookRepository;
import com.bukkeubook.book.common.paging.SelectCriteria;

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

	public int selectTotalCount(String searchCondition, String searchValue) {

		int count = 0;
		if(searchValue != null) {
			if("name".equals(searchCondition)) {
				count = bookRepository.countByNameContaining(searchValue);
			}

			if("author".equals(searchCondition)) {
				count = bookRepository.countByAuthorContaining(searchValue);
			}
			
			if("no".equals(searchCondition)) {
				count = bookRepository.countByNoContaining(searchValue);
			}
		} else {
			count = (int)bookRepository.count();
		}

		return count;
	}

	public List<BookDTO> searchBookList(SelectCriteria selectCriteria) {

		int index = selectCriteria.getPageNo() - 1;
		int count = selectCriteria.getLimit();
		String searchValue = selectCriteria.getSearchValue();

		Pageable paging = PageRequest.of(index, count, Sort.by("no"));

		List<Book> bookList = new ArrayList<Book>();
		if(searchValue != null) {

			if("name".equals(selectCriteria.getSearchCondition())) {
				bookList = bookRepository.findByNameContaining(selectCriteria.getSearchValue(), paging);
			}

			if("author".equals(selectCriteria.getSearchCondition())) {
				bookList = bookRepository.findByAuthorContaining(selectCriteria.getSearchValue(), paging);
			}
			
			if("no".equals(selectCriteria.getSearchCondition())) {
				bookList = bookRepository.findByNoContaining(selectCriteria.getSearchValue(), paging);
			}
		} else {
			bookList = bookRepository.findAll(paging).toList();
		}

		/* 자바의 Stream API와 ModelMapper를 이용하여 entity를 DTO로 변환 후 List<MenuDTO>로 반환 */
		return bookList.stream().map(book -> modelMapper.map(book, BookDTO.class)).collect(Collectors.toList());
	}

	public List<BookDTO> findBookByNo(String no) {
		List<Book> bookList = bookRepository.findBookByNo(no);
		return bookList.stream().map(book -> modelMapper.map(book, BookDTO.class)).collect(Collectors.toList());
	}
	
	
}
