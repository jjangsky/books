package com.bukkeubook.book.secretary.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bukkeubook.book.secretary.model.dto.join.BoardAndEmpAndBoardCateDTO;
import com.bukkeubook.book.secretary.model.entity.BoardAndEmpAndBoardCate;
import com.bukkeubook.book.secretary.model.repository.SecretaryBoardRepository;

@Service
public class BoardService {
	
	private final SecretaryBoardRepository secretaryBoardRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public BoardService(SecretaryBoardRepository secretaryBoardRepository, ModelMapper modelMapper) {
		this.secretaryBoardRepository = secretaryBoardRepository;
		this.modelMapper = modelMapper;
	}

	/* 총무부 전사게시판 조회*/
	public List<BoardAndEmpAndBoardCateDTO> findBoardList() {
		
		List<BoardAndEmpAndBoardCate> boardList = secretaryBoardRepository.findAll();
		
		return boardList.stream().map(board -> modelMapper.map(board, BoardAndEmpAndBoardCateDTO.class)).collect(Collectors.toList());
	}
	
	/* 총무부 전사게시판 상세조회 */
	public BoardAndEmpAndBoardCateDTO findBoardDetail(int boardNo) {
		
		BoardAndEmpAndBoardCate board = secretaryBoardRepository.findById(boardNo).get();
		
		return modelMapper.map(board, BoardAndEmpAndBoardCateDTO.class);
	}

}
