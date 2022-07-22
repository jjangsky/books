package com.bukkeubook.book.secretary.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bukkeubook.book.secretary.model.entity.BoardAndEmpAndBoardCate;

public interface SecretaryBoardRepository extends JpaRepository<BoardAndEmpAndBoardCate, Integer>{

	List<BoardAndEmpAndBoardCate> findByBoardYn(String string);

}
