package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDaoInf;
import kr.or.ddit.board.model.BoardVo;

public interface BoardServiceInf {
	
	List<BoardVo> selectBoardList();
	
	// 같은지 확인하려고 만듬
	BoardDaoInf boardRe();

	void setBoardDao(BoardDaoInf boardDao);
	

}
