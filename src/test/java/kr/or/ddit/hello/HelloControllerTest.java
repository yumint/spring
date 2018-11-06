package kr.or.ddit.hello;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

import kr.or.ddit.test.ControllerTestConfig;
import kr.or.ddit.user.model.UserVo;


public class HelloControllerTest extends ControllerTestConfig{
	private static Logger logger = LoggerFactory.getLogger(HelloController.class);
	private static Logger loggerVo = LoggerFactory.getLogger(UserVo.class);
	
	@Autowired
	private WebApplicationContext ctx; //spring IoC 컨테이너
	
	private MockMvc mockMvc; 		// dispatcher servlet (front controller)
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void helloTest() throws Throwable {
		/***Given***/
		
		/***When***/
		//dispatcher servlet 으로 요청 전송
		MvcResult mvcResult = mockMvc.perform(get("/hello/hello.do")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		/***Then***/
		//viewName
		assertEquals("hello", mav.getViewName());
	}
	
	@Test
	public void modelTest() throws Throwable {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/hello/model")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<String> rangers =   (List<String>) mav.getModel().get("rangers");
//		List<String> rangersMap =   (List<String>) mav.getModelMap().get("rangers");
		/***Then***/
		// 1. view 이름
		for (String string : rangers) {
			logger.debug("{}", string );
		}
		// 2. rangers 속성(model)
		assertEquals(3, rangers.size());
	}
	
	@Test
	public void requestTest() throws Throwable {
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/hello/request").param("userId", "brown").param("pass", "brownpass")).andReturn();
		/***When***/
		String user = (String) mvcResult.getRequest().getAttribute("userId");
		ModelAndView mav = mvcResult.getModelAndView();
		logger.debug("user : {}",user);
		//view name 
		String viewName = mav.getViewName();
		
		//userId, pass 속성
		String userId = (String)mav.getModel().get("userId");
		String pass = (String)mav.getModel().get("pass");
		
		
		/***Then***/
		assertEquals(user, userId);
	}
	
	@Test
	public void voTest() throws Exception {
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/hello/vo").param("userId", "brown").param("pass", "brownpass")).andReturn();
		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();
		UserVo userVo = (UserVo) mav.getModel().get("userVo");
		/*String pass   = (String) mav.getModel().get("pass");*/
		/***Then***/
		loggerVo.debug("userId : {}" , userVo.getUserId());
		assertNotNull(userVo);
	}


}
