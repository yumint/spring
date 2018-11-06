package kr.or.ddit.file.dao;


import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.file.model.FileVo;
import kr.or.ddit.test.ServiceDaoTestConfig;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations= {"classpath:kr/or/ddit/config/spring/root-context.xml"})
public class FileDaoTest extends ServiceDaoTestConfig {

	@Resource(name="fileDao")
	private FileDaoInf fileDao;
	
	/**
	* Method : insertFileTest
	* 작성자 : PC
	* 변경이력 :
	* Method 설명 : 파일 정보입력 테스트
	*/
	@Test
	public void insertFileTest() {
		/***Given***/
		FileVo fileVo = new FileVo();
		fileVo.setFile_name("9d81df27-4f59-4c19-a920-81ba4f75444c.png");
		fileVo.setOrg_file_name("minji.png");
		fileVo.setFile_ext(".png");
		fileVo.setFile_path("C:\\Users\\PC\\D_Other\\upload");
		
		/***When***/
		
		int insertCnt = fileDao.insertFile(fileVo);

		/***Then***/
		assertEquals(1, insertCnt);
		
	}

}
