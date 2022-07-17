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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bukkeubook.book.books.model.dto.BookDTO;
import com.bukkeubook.book.books.model.dto.RelBkListAndBookAndRelListDTO;
import com.bukkeubook.book.books.model.dto.RelBkListDTO;
import com.bukkeubook.book.books.model.dto.RelListAndEmpDTO;
import com.bukkeubook.book.books.model.dto.RelListDTO;
import com.bukkeubook.book.books.model.dto.StockBookListAndBookAndStockListAndEmpDTO;
import com.bukkeubook.book.books.model.dto.StockBookListDTO;
import com.bukkeubook.book.books.model.dto.StockListAndEmpDTO;
import com.bukkeubook.book.books.model.dto.StockListDTO;
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
	public ModelAndView modifyBookInfo(BookDTO bookDTO, ModelAndView mv) {
		
		bookService.modifyBookInfo(bookDTO);
		
		mv.setViewName("redirect:/book/lookupList");
		return mv;
	};
	
	@GetMapping("/newBook")
	public ModelAndView newBookCode(ModelAndView mv) {
		String bookCode = bookService.newBookCode();
		mv.addObject("bookCode", bookCode);
		mv.setViewName("books/bookList/newBook");
		return mv;
	}
	
	@PostMapping("/newBook2")
	public ModelAndView insertBook(BookDTO bookDTO, ModelAndView mv, RedirectAttributes rttr) {
		bookService.insertBook(bookDTO);
		rttr.addFlashAttribute("insertSuccessMessage", "성공");
		mv.setViewName("redirect:/book/lookupList");
		return mv;
	};
	
	@GetMapping("/outputList")
	public ModelAndView outputList(HttpServletRequest request, ModelAndView mv) {
		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");

		int totalCount = bookService.selectTotalCount(searchCondition, searchValue);

		int limit = 10;		

		int buttonAmount = 5;

		SelectCriteria selectCriteria = null;
		if(searchValue != null && !"".equals(searchValue)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}
		System.out.println(selectCriteria);

		List<RelListAndEmpDTO> relListAndEmpDTO = bookService.searchBookList2(selectCriteria);

		for(RelListAndEmpDTO book : relListAndEmpDTO) {
			System.out.println(book);
		}
		
		mv.addObject("outputList", relListAndEmpDTO);
		
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("books/bookList/outputList");
		return mv;
	}
	
	@GetMapping("/outputDetail")
	public ModelAndView outputDetail(HttpServletRequest request, String no, ModelAndView mv){
		int no2 = Integer.valueOf(request.getParameter("no"));
		
		List<RelBkListAndBookAndRelListDTO> outputList = bookService.outputDetail(no2);
		System.out.println("여기다여기다여기다여기다여기다여기다여기다여기다여기다여기다여기다여기다여기다여기다여기다여기다여기다여기다여기다여기다여기다여기다여기다여기다여기다여기다" + outputList);
		mv.addObject("outputList", outputList);
		mv.setViewName("books/bookList/outputListDetail");
		return mv;
	}
	
	@GetMapping("/inputList")
	public ModelAndView inputList(HttpServletRequest request, ModelAndView mv) {
		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");

		int totalCount = bookService.selectTotalCount(searchCondition, searchValue);

		int limit = 10;		

		int buttonAmount = 5;

		SelectCriteria selectCriteria = null;
		if(searchValue != null && !"".equals(searchValue)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}
		System.out.println(selectCriteria);

		List<StockListAndEmpDTO> stockListEmp = bookService.searchBookList3(selectCriteria);

		for(StockListAndEmpDTO book : stockListEmp) {
			System.out.println(book);
		}
		
		mv.addObject("inputList", stockListEmp);
		
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("books/bookList/inputList");
		return mv;
	}
	
	@GetMapping("/inputDetail")
	public ModelAndView inputDetail(HttpServletRequest request, String no, ModelAndView mv){
		int no2 = Integer.valueOf(request.getParameter("no"));
		
		List<StockBookListAndBookAndStockListAndEmpDTO> inputList = bookService.inputDetail(no2);
		System.out.println("여기다여기다여기다여기다여기다여기다여기다여기다여기다여기다여기다여기다여기다여기다여기다여기다여기다여기다여기다여기다여기다여기다여기다여기다여기다여기다");
		mv.addObject("inputList", inputList);
		mv.setViewName("books/bookList/inputListDetail");
		return mv;
	}
	
	@GetMapping("/output")
	public ModelAndView output(HttpServletRequest request, ModelAndView mv){
		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");

		int totalCount = bookService.selectTotalCount(searchCondition, searchValue);

		int limit = 3;		

		int buttonAmount = 5;

		SelectCriteria selectCriteria = null;
		if(searchValue != null && !"".equals(searchValue)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}
		System.out.println(selectCriteria);

		List<BookDTO> bookList = bookService.searchBookList4(selectCriteria);

		for(BookDTO book : bookList) {
			System.out.println(book);
		}
		
		mv.addObject("bookList", bookList);
		
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("books/bookList/output");
		return mv;
	}
	
	@GetMapping("/input")
	public ModelAndView input(HttpServletRequest request, ModelAndView mv){
		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");

		int totalCount = bookService.selectTotalCount(searchCondition, searchValue);

		int limit = 3;		

		int buttonAmount = 5;

		SelectCriteria selectCriteria = null;
		if(searchValue != null && !"".equals(searchValue)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}
		System.out.println(selectCriteria);

		List<BookDTO> bookList = bookService.searchBookList5(selectCriteria);

		for(BookDTO book : bookList) {
			System.out.println(book);
		}
		
		mv.addObject("bookList", bookList);
		
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("books/bookList/input");
		return mv;
	}
	
	@PostMapping("/outputReceipt")
	public ModelAndView outputReceipt(HttpServletRequest request, ModelAndView mv, RedirectAttributes rttr) {
		int rownum = Integer.valueOf(request.getParameter("rownum"));
		
		for(int i = 1; i <= 1; i++) {
			
			RelListDTO relList = new RelListDTO();
			RelBkListDTO relBkList = new RelBkListDTO();
			
			relList.setRelDate(new java.sql.Date(System.currentTimeMillis()));
			relList.setEmpNo(20);	// 사번은 로그인 구현후 변경하기
			
			int relNo = bookService.outputReceipt(relList);
			
			for(int j = 1; j <= rownum; j++) {
				String no = request.getParameter("no"+ j);
				int amount = Integer.valueOf(request.getParameter("amount"+ j));
				relBkList.setRelNo(relNo);
				relBkList.setBkNo(no);
				relBkList.setRelBkAmount(amount);
				
				bookService.outputReceipt2(relBkList);
				
				BookDTO bookDTO = new BookDTO();
				bookDTO.setNo(no);
				bookDTO.setWhSt(amount);
				bookService.outputReceipt3(bookDTO, amount);
				
				
			}
		}
		
		rttr.addFlashAttribute("outputSuccessMessage", "성공");
		mv.setViewName("redirect:/book/outputList");
		return mv;
		
	}
	
	@PostMapping("/inputReceipt")
	public ModelAndView inputReceipt(HttpServletRequest request, ModelAndView mv, RedirectAttributes rttr) {
		
		int rownum = Integer.valueOf(request.getParameter("rownum"));
		String selectInput = request.getParameter("selectInput");
		for(int i = 1; i <= 1; i++) {
			
			StockListDTO stockList = new StockListDTO();
			StockBookListDTO stockBookList = new StockBookListDTO();
			
			stockList.setStDate(new java.sql.Date(System.currentTimeMillis()));
			stockList.setStType(selectInput);
			stockList.setEmpNo(21);	// 사번은 로그인 구현후 변경하기
			
			int stCode = bookService.inputReceipt(stockList);
			
			for(int j = 1; j <= rownum; j++) {
				String no = request.getParameter("no"+ j);
				int amount = Integer.valueOf(request.getParameter("amount"+ j));
				stockBookList.setBkNo(no);
				stockBookList.setStockBkAmount(amount);
				stockBookList.setStCode(stCode);
				
				bookService.inputReceipt2(stockBookList);
				
				BookDTO bookDTO = new BookDTO();
				bookDTO.setNo(no);
				bookDTO.setWhSt(amount);
				bookService.inputReceipt3(bookDTO, amount, selectInput);
			}
		}
		rttr.addFlashAttribute("inputSuccessMessage", "성공");
		mv.setViewName("redirect:/book/inputList");
		return mv;
	}
}
