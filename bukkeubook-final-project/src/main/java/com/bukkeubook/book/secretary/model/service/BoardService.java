package com.bukkeubook.book.secretary.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bukkeubook.book.common.paging.SelectCriteria;
import com.bukkeubook.book.secretary.model.dto.BoardDTO;
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
	
	/* 전사게시판 등록하기 */
	@Transactional
	public void registBoardContent(BoardDTO board) {
		
		basicBoardRepository.save(modelMapper.map(board, Board.class));
		
	}
	
	/* 전사게시판 삭제하기 */
	@Transactional
	public void deleteBoardContent(int boardNo, String boardYn) {
		
		Board deleteBoard = basicBoardRepository.findById(boardNo).get();
		deleteBoard.setBoardYn(boardYn);
		
	}

	/* 총무부 전사게시판 검색 값 갯수 구하기 */
	public int selectTotalCount(String searchCondition, String searchValue) {
		
		int count = 0;
		if(searchValue != null) {
			if("empName".equals(searchCondition)) {
				count = secretaryBoardRepository.countByBoardYnAndEmp_EmpNameContaining("N",searchValue);
			}
			if("title".equals(searchCondition)) {
				count = secretaryBoardRepository.countByBoardYnAndTitleContaining("N", searchValue);
			}
			if("cateName".equals(searchCondition)) {
				count = secretaryBoardRepository.countByBoardYnAndCate_CateNameContaining("N", searchValue);
			}
			
		} else {
			count = (int) secretaryBoardRepository.count();
		}
		
		return count;
	}
	
	/* 페이징 처리 */
	public List<BoardAndEmpAndBoardCateDTO> findSearchBoardList(SelectCriteria selectCriteria) {
		
		int index = selectCriteria.getPageNo() - 1;
		int count = selectCriteria.getLimit();
		String searchValue = selectCriteria.getSearchValue();
		
		Pageable paging = PageRequest.of(index, count, Sort.by("no").descending());
		
		List<BoardAndEmpAndBoardCate> boardList = new ArrayList<BoardAndEmpAndBoardCate>();
		
		if(searchValue != null) {
			if("empName".equals(selectCriteria.getSearchCondition())) {
				boardList = secretaryBoardRepository.findByBoardYnAndEmp_EmpNameContaining("N", searchValue, paging);
			}
			if("title".equals(selectCriteria.getSearchCondition())) {
				boardList = secretaryBoardRepository.findByBoardYnAndTitleContaining("N", searchValue, paging);
			}
			if("cateName".equals(selectCriteria.getSearchCondition())) {
				boardList = secretaryBoardRepository.findByBoardYnAndCate_CateNameContaining("N", searchValue, paging);
			}
		}else {
			boardList = secretaryBoardRepository.findAll(paging).toList();
		}
		
		return boardList.stream().map(list -> modelMapper.map(list, BoardAndEmpAndBoardCateDTO.class)).collect(Collectors.toList());
	}
	
	
	/* 총무부 전사게시판 조회*/
	public List<BoardAndEmpAndBoardCateDTO> findBoardList() {
		
		List<BoardAndEmpAndBoardCate> boardList = secretaryBoardRepository.findByBoardYn("N");
		
		return boardList.stream().map(board -> modelMapper.map(board, BoardAndEmpAndBoardCateDTO.class)).collect(Collectors.toList());
	}


	

}
