package com.bukkeubook.book.books.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bukkeubook.book.books.controller.NativeRepository;
import com.bukkeubook.book.books.model.dto.BookDTO;
import com.bukkeubook.book.books.model.dto.RelBkListAndBookAndRelListDTO;
import com.bukkeubook.book.books.model.dto.RelListAndEmpDTO;
import com.bukkeubook.book.books.model.dto.StockBookListAndBookAndStockListAndEmpDTO;
import com.bukkeubook.book.books.model.dto.StockListAndEmpDTO;
import com.bukkeubook.book.books.model.entity.Book;
import com.bukkeubook.book.books.model.entity.RelBkListAndBookAndRelList;
import com.bukkeubook.book.books.model.entity.RelListAndEmp;
import com.bukkeubook.book.books.model.entity.StockBookListAndBookAndStockListAndEmp;
import com.bukkeubook.book.books.model.entity.StockListAndEmp;
import com.bukkeubook.book.books.model.repository.BookRepository;
import com.bukkeubook.book.books.model.repository.InputRepository;
import com.bukkeubook.book.books.model.repository.OutputRepository;
import com.bukkeubook.book.books.model.repository.RelBkListAndBookAndRelListRepository;
import com.bukkeubook.book.books.model.repository.StockBookListAndBookAndStockListAndEmpRepository;
import com.bukkeubook.book.common.paging.SelectCriteria;

@Service
public class BookService {
	
	private final BookRepository bookRepository;
	private final NativeRepository nativeRepository; 
	private final OutputRepository outputRepository;
	private final RelBkListAndBookAndRelListRepository relBkListAndBookAndRelListRepository;
	private final StockBookListAndBookAndStockListAndEmpRepository stockBookListAndBookAndStockListAndEmpRepository;
	private final InputRepository inputRepository;
	private final ModelMapper modelMapper;			// modelMapper 빈을 선언
	
	@Autowired
	public BookService(StockBookListAndBookAndStockListAndEmpRepository stockBookListAndBookAndStockListAndEmpRepository, InputRepository inputRepository, RelBkListAndBookAndRelListRepository relBkListAndBookAndRelListRepository, BookRepository bookRepository, ModelMapper modelMapper, NativeRepository nativeRepository, OutputRepository outputRepository) {
		this.bookRepository = bookRepository;
		this.outputRepository = outputRepository;
		this.nativeRepository = nativeRepository;
		this.relBkListAndBookAndRelListRepository = relBkListAndBookAndRelListRepository;
		this.stockBookListAndBookAndStockListAndEmpRepository = stockBookListAndBookAndStockListAndEmpRepository;
		this.inputRepository = inputRepository;
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

	@Transactional
	public List<BookDTO> findBookByNo(String no) {
		List<Book> bookList = bookRepository.findBookByNo(no);
		return bookList.stream().map(book -> modelMapper.map(book, BookDTO.class)).collect(Collectors.toList());
	}
	
	@Transactional	
	public void modifyBookInfo(BookDTO bookDTO) {
		
		Book book = bookRepository.findByNo(bookDTO.getNo());
		book.setNo(bookDTO.getNo());
		book.setPrice(bookDTO.getPrice());
		book.setStoreSt(bookDTO.getStoreSt());
		book.setWhSt(bookDTO.getWhSt());
		book.setSellYn(bookDTO.getSellYn());

	}

	public String newBookCode() {
		String bookCode = nativeRepository.newBookCode();
		return bookCode;
	}
	
	@Transactional
	public void insertBook(BookDTO bookDTO) {
		bookRepository.save(modelMapper.map(bookDTO, Book.class));
	}

	public List<RelListAndEmpDTO> searchBookList2(SelectCriteria selectCriteria) {

		int index = selectCriteria.getPageNo() - 1;
		int count = selectCriteria.getLimit();
		String searchValue = selectCriteria.getSearchValue();

		Pageable paging = PageRequest.of(index, count, Sort.by("relDate").descending());

		List<RelListAndEmp> bookList = new ArrayList<RelListAndEmp>();
		if(searchValue != null) {

			if("name".equals(selectCriteria.getSearchCondition())) {
				bookList = outputRepository.findAllByEmp_EmpNameContaining(selectCriteria.getSearchValue(), paging);
			}

			if("date".equals(selectCriteria.getSearchCondition())) {
				bookList = outputRepository.findAllByRelDateContaining(selectCriteria.getSearchValue(), paging);
			}
		} else {
			bookList = outputRepository.findAll(paging).toList();
		}
		System.out.println(bookList);
		return bookList.stream().map(book -> modelMapper.map(book, RelListAndEmpDTO.class)).collect(Collectors.toList());
	}

	public List<RelBkListAndBookAndRelListDTO> outputDetail(int no2) {
		List<RelBkListAndBookAndRelList> bookList = relBkListAndBookAndRelListRepository.findByrelListEmp_relNo(no2);
		System.out.println(bookList);
		return bookList.stream().map(book -> modelMapper.map(book, RelBkListAndBookAndRelListDTO.class)).toList();
	}

	public List<StockListAndEmpDTO> searchBookList3(SelectCriteria selectCriteria) {
		
		int index = selectCriteria.getPageNo() - 1;
		int count = selectCriteria.getLimit();
		String searchValue = selectCriteria.getSearchValue();

		Pageable paging = PageRequest.of(index, count, Sort.by("stDate").descending());

		List<StockListAndEmp> stockListEmp = new ArrayList<StockListAndEmp>();
		if(searchValue != null) {

			if("name".equals(selectCriteria.getSearchCondition())) {
				stockListEmp = inputRepository.findAllByEmp_EmpNameContaining(selectCriteria.getSearchValue(), paging);
			}

			if("date".equals(selectCriteria.getSearchCondition())) {
				stockListEmp = inputRepository.findAllByStDateContaining(selectCriteria.getSearchValue(), paging);
			}
		} else {
			stockListEmp = inputRepository.findAll(paging).toList();
		}
		System.out.println(stockListEmp);
		return stockListEmp.stream().map(book -> modelMapper.map(book, StockListAndEmpDTO.class)).collect(Collectors.toList());
	}

	public List<StockBookListAndBookAndStockListAndEmpDTO> inputDetail(int no2) {
		List<StockBookListAndBookAndStockListAndEmp> bookList = stockBookListAndBookAndStockListAndEmpRepository.findBystockListEmp_stCode(no2);
		System.out.println(bookList);
		return bookList.stream().map(book -> modelMapper.map(book, StockBookListAndBookAndStockListAndEmpDTO.class)).toList();
	}

	public List<BookDTO> searchBookList4(SelectCriteria selectCriteria) {
		int index = selectCriteria.getPageNo() - 1;
		int count = selectCriteria.getLimit();
		String searchValue = selectCriteria.getSearchValue();

		Pageable paging = PageRequest.of(index, count, Sort.by("no"));

		List<Book> outputList = new ArrayList<Book>();
		if(searchValue != null) {

			if("no".equals(selectCriteria.getSearchCondition())) {
				outputList = bookRepository.findAllByNoContaining(selectCriteria.getSearchValue(), paging);
			}

			if("name".equals(selectCriteria.getSearchCondition())) {
				outputList = bookRepository.findAllByNameContaining(selectCriteria.getSearchValue(), paging);
			}
			
			if("author".equals(selectCriteria.getSearchCondition())) {
				outputList = bookRepository.findAllByAuthorContaining(selectCriteria.getSearchValue(), paging);
			}
		} /*
			 * else { stockListEmp = bookRepository.findAll(paging).toList(); }
			 */
		System.out.println(outputList);
		return outputList.stream().map(book -> modelMapper.map(book, BookDTO.class)).collect(Collectors.toList());
	}

	public List<BookDTO> searchBookList5(SelectCriteria selectCriteria) {
		int index = selectCriteria.getPageNo() - 1;
		int count = selectCriteria.getLimit();
		String searchValue = selectCriteria.getSearchValue();

		Pageable paging = PageRequest.of(index, count, Sort.by("no"));

		List<Book> inputList = new ArrayList<Book>();
		if(searchValue != null) {

			if("no".equals(selectCriteria.getSearchCondition())) {
				inputList = bookRepository.findAllByNoContaining(selectCriteria.getSearchValue(), paging);
			}

			if("name".equals(selectCriteria.getSearchCondition())) {
				inputList = bookRepository.findAllByNameContaining(selectCriteria.getSearchValue(), paging);
			}
			
			if("author".equals(selectCriteria.getSearchCondition())) {
				inputList = bookRepository.findAllByAuthorContaining(selectCriteria.getSearchValue(), paging);
			}
		} /*
			 * else { stockListEmp = bookRepository.findAll(paging).toList(); }
			 */
		System.out.println(inputList);
		return inputList.stream().map(book -> modelMapper.map(book, BookDTO.class)).collect(Collectors.toList());
	}
	
	
	
	
}
