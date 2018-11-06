package kr.or.ddit.mvc;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.file.util.FileUtil;
import kr.or.ddit.test.ControllerTestConfig;

@SuppressWarnings("unchecked")
public class MvcControllerTest extends ControllerTestConfig {
	
	private static Logger logger = LoggerFactory.getLogger(MvcController.class);
	
	@Autowired
	private WebApplicationContext ctx; //spring IoC 컨테이너
	
	private MockMvc mockMvc; 		// dispatcher servlet (front controller)
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	

	@Test
	public void mvcControllerTest() throws Exception {
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/mvc/view")).andReturn();
		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName(); 
		
		//model 속성 받아오기  
		List<String>rangers = (List<String>) mav.getModel().get("rangers");
		/***Then***/
		for (String string : rangers) {
			logger.debug("rangerName : {}", string);
		}
		assertEquals("mvc/view", viewName);
		assertEquals(4, rangers.size());//
	}
	
	@Test
	public void mvcControllerTest2() throws Exception {
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/mvc/view2")).andReturn();
		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName(); 
		
		//model 속성 받아오기  
		List<String>rangers = (List<String>) mav.getModel().get("rangers");
		/***Then***/
		logger.debug("rangers.size : {}", rangers.size());
		assertEquals("mvc/view2", viewName);
		assertEquals(3, rangers.size());
	}
	
	@Test
	public void fileuploadViewTest() throws Exception {
		
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/mvc/fileupload")).andReturn();

		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();
		
		/***Then***/
		assertEquals("mvc/fileuploadView", mav.getViewName());
	}
	
	
	// FileUtil.getFileExt(String fileName);
	// 확장자가 있을 경우  .을 포함한 확장자 값을 리턴
	// 확장자가 없을 경우 "" whitespace를 리턴 
	// 테스트 값 : sally.png ==> .png
	// 테스트값 : sally ==> ""
	
	// 테스트 코드를 운영코드보다 먼저 작성하는 방법
	// Test Driven Development(TDD)
	@Test
	public void fileuploadViewTest2() {
		/***Given***/
		String fileName = "minji.png";

		/***When***/
		String ext = FileUtil.getFileExt(fileName);
		
		/***Then***/
		assertEquals(".png", ext);
	}
	

	/**
	* Method : fileuploadViewTest3
	* 작성자 : PC
	* 변경이력 :
	* Method 설명 : 파일며에 확장자가 없는경우 
	*/
	@Test
	public void fileuploadViewTest3() {
		/***Given***/
		String fileName = "minji";

		/***When***/
		String ext = FileUtil.getFileExt(fileName);
		
		/***Then***/
		assertEquals("", ext);
	}
	
	/**
	* Method : fileuploadViewMultiDot
	* 작성자 : PC
	* 변경이력 :
	* Method 설명 : 파일 확장자 추출(확장자가 있는 경우 , 파일명에 점이 들어가는 경우)
	*/
	@Test
	public void fileuploadViewMultiDot() {
		/***Given***/
		String fileName = "minji.brown.png";

		/***When***/
		String ext = FileUtil.getFileExt(fileName);
		
		/***Then***/
		assertEquals(".png", ext);
	}

}
