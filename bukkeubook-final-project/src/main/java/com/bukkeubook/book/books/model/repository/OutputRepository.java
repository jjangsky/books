package com.bukkeubook.book.books.model.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bukkeubook.book.books.model.dto.RelBkListAndRelListDTO;
import com.bukkeubook.book.books.model.entity.RelBkListAndRelList;

public interface OutputRepository extends JpaRepository<RelBkListAndRelList, Integer> {
	
//	List<RelBkListAndRelList> findAllByREL_NO(String searchValue, Pageable paging);

//	List<RelBkListAndRelList> findBybookNoContaining(String searchValue, Pageable paging);
}
