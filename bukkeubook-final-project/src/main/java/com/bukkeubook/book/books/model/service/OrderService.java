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

import com.bukkeubook.book.books.model.dto.OrderAndEmpAndBookDTO;
import com.bukkeubook.book.books.model.entity.OrderAndEmpAndBook;
import com.bukkeubook.book.books.model.repository.OrderRepository;
import com.bukkeubook.book.common.paging.SelectCriteria;

@Service
public class OrderService {

	private final OrderRepository orderRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public OrderService(OrderRepository orderRepository, ModelMapper modelMapper) {
		this.orderRepository = orderRepository;
		this.modelMapper = modelMapper;
	}
	
	public int selectTotalCount(String searchCondition, String searchValue) {
		int count = 0;
		if(searchValue != null) {
			count = orderRepository.countByOrderApprYnContaining(searchValue);
		} else {
			count = (int)orderRepository.count();
		}

		return count;
	}

	public List<OrderAndEmpAndBookDTO> searchOrderList(SelectCriteria selectCriteria) {

		int index = selectCriteria.getPageNo() - 1;
		int count = selectCriteria.getLimit();
		String searchValue = selectCriteria.getSearchValue();

		Pageable paging = PageRequest.of(index, count, Sort.by("orderNo").descending());

		List<OrderAndEmpAndBook> orderHistoryList = new ArrayList<OrderAndEmpAndBook>();
		if(searchValue != null) {
			orderHistoryList = orderRepository.findByOrderApprYnContaining(selectCriteria.getSearchValue(), paging);
		} else {
			orderHistoryList = orderRepository.findAll(paging).toList();
		}

		/* 자바의 Stream API와 ModelMapper를 이용하여 entity를 DTO로 변환 후 List<MenuDTO>로 반환 */
		return orderHistoryList.stream().map(OrderList -> modelMapper.map(OrderList, OrderAndEmpAndBookDTO.class)).collect(Collectors.toList());
	}

	public OrderAndEmpAndBookDTO searchOrderDetail(int orderNo) {
		
		OrderAndEmpAndBook order = orderRepository.findById(orderNo).get();
		
		return modelMapper.map(order, OrderAndEmpAndBookDTO.class);
	}

}
