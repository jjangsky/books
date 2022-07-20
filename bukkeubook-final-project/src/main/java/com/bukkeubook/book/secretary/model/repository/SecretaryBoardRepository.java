package com.bukkeubook.book.secretary.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bukkeubook.book.secretary.model.entity.BoardAndEmpAndBoardCate;

public interface SecretaryBoardRepository extends JpaRepository<BoardAndEmpAndBoardCate, Integer>{

}
