package kr.or.ddit.ioc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import kr.or.ddit.board.dao.BoardDaoInf;
import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.service.BoardServiceInf;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:kr/or/ddit/ioc/application-context-scan.xml"})
public class ComponentScanTest {
	
	// 괄호에는 해당 클래스 이름을 입력하면 된다
	private Logger logger = LoggerFactory.getLogger(ComponentScanTest.class);
	
	// 주입하기
	@Resource(name="boardDao")
	private BoardDaoInf boardDao;
	

	/**
	* Method : componentScanTest
	* 작성자 : PC
	* 변경이력 :
	* Method 설명 : sparing annotation scan을 통해 스프링 빈 생성이 정상적으로 되었는지 테스트
	*/
	@Test
	public void componentScanTest() {
		
		/***Given***/
		

		/***When***/
		
		List<BoardVo> boardList = boardDao.selectBoardList();
		for(BoardVo boardVo : boardList)
			logger.debug("{}" , boardVo);

		/***Then***/
		assertNotNull(boardDao);
		
	}
	
	
	// 주입하기
	@Resource(name="boardService")
	private BoardServiceInf boardService;
	
	/**
	* Method : componentScanTest1
	* 작성자 : PC
	* 변경이력 :
	* Method 설명 :sparing annotation scan을 통해 스프링 빈 생성이 정상적으로 되었는지 테스트
	*/
	@Test
	public void componentScanTest1() {
		
		/***Given***/
		

		/***When***/
		
		List<BoardVo> boardList = boardService.selectBoardList();
		for(BoardVo boardVo : boardList)
			logger.debug("{}" , boardVo);

		/***Then***/
		assertNotNull(boardService);
		
	}

	/**
	* Method : componentScanTest2
	* 작성자 : PC
	* 변경이력 :
	* Method 설명 : boardService.getBoardDao()를 통해 리턴받은 boardDao와 
	ComponentScanTest의 boardDao 객체가 동일한 객체 인지 확인하는 테스트 코드 작성해보기
	*/
	@Test
	public void componentScanTest2() {
		
		/***Given***/
		

		/***When***/
	
		/***Then***/
		assertEquals(boardDao, boardService.boardRe());
		
	}
	
	
	
	

}
