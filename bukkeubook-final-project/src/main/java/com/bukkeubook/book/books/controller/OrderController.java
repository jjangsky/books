package com.bukkeubook.book.books.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bukkeubook.book.books.model.dto.BookDTO;
import com.bukkeubook.book.books.model.dto.OrderAndEmpAndBookDTO;
import com.bukkeubook.book.books.model.dto.OrderListDTO;
import com.bukkeubook.book.books.model.service.BookService;
import com.bukkeubook.book.books.model.service.OrderService;
import com.bukkeubook.book.common.paging.Pagenation;
import com.bukkeubook.book.common.paging.SelectCriteria;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	private final OrderService orderService;
	private final BookService bookService;
	
	@Autowired
	public OrderController(OrderService orderService, BookService bookService) {
		this.orderService = orderService;
		this.bookService = bookService;
	}
	
	@GetMapping("/selectHistory")
	public ModelAndView searchPage(HttpServletRequest request, ModelAndView mv) {

		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");

		int totalCount = orderService.selectTotalCount(searchCondition, searchValue);

		/* 한 페이지에 보여 줄 게시물 수 */
		int limit = 8;		//얘도 파라미터로 전달받아도 된다.

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

		List<OrderAndEmpAndBookDTO> orderList = orderService.searchOrderList(selectCriteria);

		for(OrderAndEmpAndBookDTO order : orderList) {
			System.out.println(order);
		}

		mv.addObject("orderList", orderList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("books/order/orderHistorySelect");

		return mv;
	}
	
	@GetMapping("/detail")
	public ModelAndView selectOrderDetail(HttpServletRequest request, ModelAndView mv) {
		
		String no = request.getParameter("orderNo");
		int orderNo = Integer.valueOf(no);
		
		OrderAndEmpAndBookDTO order = orderService.searchOrderDetail(orderNo);
		
		mv.addObject("order", order);
		mv.setViewName("books/order/orderDetail");
		
		return mv;
	}
	
	@GetMapping("/regist")
	public ModelAndView searchBook(HttpServletRequest request, ModelAndView mv) {
		
		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");

		int totalCount = bookService.selectTotalCount(searchCondition, searchValue);

		/* 한 페이지에 보여 줄 게시물 수 */
		int limit = 5;		//얘도 파라미터로 전달받아도 된다.

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
		mv.setViewName("books/order/orderRegist");

		return mv;
	}
	
	@PostMapping("/regist")
	public ModelAndView registOrder(HttpServletRequest request, ModelAndView mv, RedirectAttributes rttr) {
		
		int rownum = Integer.valueOf(request.getParameter("rownum"));
		
		for(int i = 1; i <= rownum; i++) {
			OrderListDTO order = new OrderListDTO();
			
			String no = request.getParameter("no" + i);
			int amount = Integer.valueOf(request.getParameter("amount" + i));
			
//			java.sql.Date currentDate = new SimpleDateFormat("yyMMddhhmmss").format(new java.sql.Date(System.currentTimeMillis())); 
			
			order.setOrderDate(new java.sql.Date(System.currentTimeMillis()));
			order.setOrderApprYn("대기");
			order.setOrderAmount(amount);
			order.setCntNo(3);
			order.setBkNo(no);
			order.setEmpNo(4);

			orderService.registOrder(order);
		}
		
		rttr.addFlashAttribute("registSuccessMessage", "발주 등록에 성공하셨습니다");
		mv.setViewName("redirect:/order/selectHistory");
		
		return mv;
	}
	
}
