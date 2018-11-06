package kr.or.ddit.board.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.BoardDaoInf;
import kr.or.ddit.board.model.BoardVo;


@Service("boardService")
public class BoardService implements BoardServiceInf {
	
	// boardDao주입해야 오류가 나지 않는다
	@Resource(name="boardDao")
	private BoardDaoInf boardDao;
	
	// 기본생성자 만들기 
	public BoardService() {
		
	}
	
	public BoardService(BoardDaoInf boardDao) {
		this.boardDao = boardDao;
	}
	

	public BoardDaoInf getBoardDao() {
		return boardDao;
	}

	public void setBoardDao(BoardDaoInf boardDao) {
		this.boardDao = boardDao;
	}


	@Override
	public List<BoardVo> selectBoardList() {
		return boardDao.selectBoardList();
	}

	@Override
	public BoardDaoInf boardRe() {
		return boardDao;
	}
	
	

}
