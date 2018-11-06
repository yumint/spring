package kr.or.ddit.main.web;


import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.ControllerTestConfig;


public class MainControllerTest extends ControllerTestConfig{
	
	  private Logger logger = LoggerFactory.getLogger("HelloControllerTest.class");
	   
	   @Autowired
	   private WebApplicationContext ctx;   //spring ioc컨테이너
	   
	   private MockMvc mockMvc;   //dispatcher servlet(front controller)
	   
	   @Before
	   public void setup() {
	      mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	   }

	/**
	* Method : MainTest
	* 작성자 : PC
	* 변경이력 :
	* Method 설명 : 메인화면 호출테스트 
	 * @throws Exception 
	*/
	@Test
	public void MainViewTest() throws Exception {
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/main")).andReturn();

		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();
		
		/***Then***/
		assertEquals("main", mav.getViewName());
		
	}

}
