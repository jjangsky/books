package com.bukkeubook.book.books.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bukkeubook.book.books.model.dto.BookDTO;
import com.bukkeubook.book.books.model.service.BookService;
import com.bukkeubook.book.common.paging.Pagenation;
import com.bukkeubook.book.common.paging.SelectCriteria;

@Controller
@RequestMapping("/book")
public class BookController extends HttpServlet{
	
	private final BookService bookService;
	
	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	/*
	 * @GetMapping("/lookupList") public ModelAndView findBookList(ModelAndView mv)
	 * {
	 * 
	 * List<BookDTO> bookList = bookService.findBookList();
	 *  mv.addObject("bookList", bookList); 
	 *  mv.setViewName("books/bookList/lookupList");
	 * 
	 * return mv; }
	 */
	
	
	@GetMapping("/lookupList")
	public ModelAndView searchPage(HttpServletRequest request, ModelAndView mv) {

		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");

		int totalCount = bookService.selectTotalCount(searchCondition, searchValue);

		/* 한 페이지에 보여 줄 게시물 수 */
		int limit = 10;		//얘도 파라미터로 전달받아도 된다.

		/* 한 번에 보여질 페이징 버튼의 갯수 */
		int buttonAmount = 5;

		/* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
		SelectCriteria selectCriteria = null;
		if(searchValue != null && !"".equals(searchValue)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}
		System.out.println(selectCriteria);

		List<BookDTO> bookList = bookService.searchBookList(selectCriteria);

		for(BookDTO book : bookList) {
			System.out.println(book);
		}

		mv.addObject("bookList", bookList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("books/bookList/lookupList");

		return mv;
	}
	
	@GetMapping("/bookInfo")
	public ModelAndView bookInfo(HttpServletRequest request, String no, ModelAndView mv){
		
		no = request.getParameter("no");
		
		List<BookDTO> bookList = bookService.findBookByNo(no);
		
		mv.addObject("bookList", bookList);
		mv.setViewName("books/bookList/bookInfo");
		return mv;
	}
	
	@GetMapping("/bookInfoUpdate")
	public ModelAndView updateBookInfo(BookDTO bookDTO, String no, ModelAndView mv) {
		
		List<BookDTO> bookList = bookService.findBookByNo(no);
		
		mv.addObject("bookList", bookList);
		mv.setViewName("books/bookList/bookInfoUpdate");
		return mv;
	}
	
	@PostMapping("/bookInfoUpdate2")
	public String modifyBookInfo(BookDTO bookDTO) {
		
		bookService.modifyBookInfo(bookDTO);
		
		return "/main";
	};
	
	
}
