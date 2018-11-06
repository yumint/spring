package kr.or.ddit.board.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.board.model.BoardVo;


// 어노테이션을 통한 빈 등록 
// 어노테이션은 주석이라고 생각하면 된다. 
// been 어노테이션은 크게 4가지 이다. 
// @Controller , @Service , @Repository , @Component

// @Controller : controller (많이 사용하는것)
// @Service : service (많이 사용하는것)
// @Repository : dao (많이 사용하는것)
// @Component : 일반적인 객체 
// @Controller , @Service , @Repository , @Compoment 어노테이션은 @Component 어노테이션을 기반으로 확장
// 어노테이션 자체가 특별한 기능을 담고 있는 것은 아님 
// 개발에 자주 사용되는 모듈을 분리하여 명명한 것에 불과 


/**
* BoardDao.java
*
* @author PC
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
* 수정자 수정내용
* ------ ------------------------
* PC 최초 생성
*
* </pre>
*/

// 이름을 생략하면 클래스 첫글자를 소문자로 한 이름이 spring bean 이름이 됨
@Repository("boardDao")
public class BoardDao implements BoardDaoInf {

	@Override
	public List<BoardVo> selectBoardList() {
		// db에서 조회 해온 데이터라고 가정하자 
		BoardVo boardVo = new BoardVo(1,"자유게시판","brown");
		BoardVo boardVo2 = new BoardVo(2,"공지사항","cony");
		BoardVo boardVo3 = new BoardVo(3,"FAQ","sally");
		
		List<BoardVo> boardList = new ArrayList<BoardVo>();
		
		boardList.add(boardVo);
		boardList.add(boardVo2);
		boardList.add(boardVo3);
		
		return boardList;
	}

}
