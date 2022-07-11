package com.bukkeubook.book.books.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bukkeubook.book.books.model.dto.BookDTO;
import com.bukkeubook.book.books.model.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
	/*
	 * @Query(value =
	 * "SELECT A.CATEGORY_CODE, A.CATEGORY_NAME, A.REF_CATEGORY_CODE FROM TBL_CATEGORY A ORDER BY A.CATEGORY_CODE ASC"
	 * , jpql)
	 */
//	List<BookDTO> selectBookList();
	
}
