package com.bukkeubook.book.books.model.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bukkeubook.book.books.model.entity.StockListAndEmp;

public interface InputRepository extends JpaRepository<StockListAndEmp, Integer>  {

	List<StockListAndEmp> findAllByEmp_EmpNameContaining(String searchValue, Pageable paging);

	List<StockListAndEmp> findAllByStDateContaining(String searchValue, Pageable paging);
	
}
