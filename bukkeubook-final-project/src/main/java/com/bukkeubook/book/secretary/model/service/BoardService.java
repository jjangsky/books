package com.bukkeubook.book.secretary.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bukkeubook.book.secretary.model.dto.join.BoardAndEmpAndBoardCateDTO;
import com.bukkeubook.book.secretary.model.entity.Board;
import com.bukkeubook.book.secretary.model.entity.BoardAndEmpAndBoardCate;
import com.bukkeubook.book.secretary.model.repository.BasicBoardRepository;
import com.bukkeubook.book.secretary.model.repository.SecretaryBoardRepository;

@Service
public class BoardService {
	
	private final SecretaryBoardRepository secretaryBoardRepository;
	private final BasicBoardRepository basicBoardRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public BoardService(SecretaryBoardRepository secretaryBoardRepository, ModelMapper modelMapper, BasicBoardRepository basicBoardRepository) {
		this.secretaryBoardRepository = secretaryBoardRepository;
		this.basicBoardRepository = basicBoardRepository;
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

	/* 총무부 전사게시판 수정하기 */
	@Transactional
	public void modifyBoardContent(BoardAndEmpAndBoardCateDTO board) {
		
		Board boardUpdate = basicBoardRepository.findById(board.getNo()).get();
		boardUpdate.setCateNo(board.getCateNo());
		boardUpdate.setTitle(board.getTitle());
		boardUpdate.setContent(board.getContent());
		
	}

}
